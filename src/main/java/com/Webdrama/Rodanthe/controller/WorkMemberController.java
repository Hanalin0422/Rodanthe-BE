package com.Webdrama.Rodanthe.controller;

import com.Webdrama.Rodanthe.dto.JjimDto;
import com.Webdrama.Rodanthe.dto.WorkDto;
import com.Webdrama.Rodanthe.dto.request.JjimRequestDto;
import com.Webdrama.Rodanthe.dto.request.WorkRequestDto;
import com.Webdrama.Rodanthe.entity.Jjim;
import com.Webdrama.Rodanthe.entity.Work;
import com.Webdrama.Rodanthe.repository.JjimRepository;
import com.Webdrama.Rodanthe.repository.WorkRepository;
import com.Webdrama.Rodanthe.service.VideoService;
import com.Webdrama.Rodanthe.service.WorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class WorkMemberController {

    @Autowired
    private final WorkRepository workRepository;

    @Autowired
    private final JjimRepository jjimRepository;

    @Autowired
    private final WorkService workService;
    @Autowired
    private final VideoService videoService;



    @PostMapping("/work/{userId}")
    public Long create(@PathVariable Long userId, @RequestBody WorkDto workPostDto) {
        workPostDto.setId(userId);

        Work work = workPostDto.toWork();
        Work saved = workRepository.save(work);
        return saved.getWorkId(); // 저장된 작품의 pk Id 반환
    }


    @GetMapping("/work/{userId}")
    public List<Work> giveInfo(@PathVariable Long userId){
        List<Work> workUserList = workRepository.findAllById(userId);

        return workUserList;
    }

    @PutMapping("/work/update/{workId}")
    public String updateWorkInfo(@PathVariable Long workId, @RequestBody WorkRequestDto workRequestDto){
        Work workInfo = workService.updateWorkInfo(workId, workRequestDto);
        workRepository.save(workInfo);
        return "작품번호 " +  workId + "의 수정이 완료되었습니다.";
    }

    @DeleteMapping("/work/delete/{workId}")
    public String deleteWork(@PathVariable Long workId){
        workService.deleteWork(workId);
        videoService.deleteVideoList(workId);
        return "작품 " + workId + " 삭제 완료.";
    }

    // 찜하기 요청
    @PostMapping("/jjim/work")
    public String jjimWork(@RequestBody JjimRequestDto jjimRequestDto){
        Jjim jjim = jjimRequestDto.toJjim();
        jjimRepository.save(jjim);
        return "찜하기가 완료 되었습니다.";
    }

    // 찜된거 확인 요청
    @GetMapping("/jjim/work/get/{userId}/{workId}")
    public boolean jjimWorkGiveInfo(@PathVariable Long userId, @PathVariable Long workId){
        return workService.jjimGiveInfo(userId, workId);
    }

    // 찜 취소 요청
    @PostMapping("/jjim/work/delete")
    public String jjimWorkDelete(@RequestBody JjimRequestDto jjimRequestDto){
        Long userId = jjimRequestDto.getId();
        Long workId = jjimRequestDto.getWorkId();
        workService.deleteJjim(userId, workId);
        return "찜하기가 취소되었습니다.";
    }

    @GetMapping("/jjim/view/{userId}")
    public List<JjimDto> jjimGetInfo(@PathVariable Long userId){
        return workService.jjimView(userId);
    }

}
