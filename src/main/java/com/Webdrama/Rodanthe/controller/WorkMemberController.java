package com.Webdrama.Rodanthe.controller;

import com.Webdrama.Rodanthe.dto.WorkDto;
import com.Webdrama.Rodanthe.entity.Work;
import com.Webdrama.Rodanthe.repository.WorkRepository;
import com.Webdrama.Rodanthe.service.WorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class WorkMemberController {

    @Autowired
    private final WorkRepository workRepository;

    @Autowired
    private final WorkService workService;
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
}
