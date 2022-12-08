package com.Webdrama.Rodanthe.controller;

import com.Webdrama.Rodanthe.dto.NumMainPageDto;
import com.Webdrama.Rodanthe.dto.SearchWorkDto;
import com.Webdrama.Rodanthe.dto.request.WorkSearchRequestDto;
import com.Webdrama.Rodanthe.entity.Video;
import com.Webdrama.Rodanthe.entity.Work;
import com.Webdrama.Rodanthe.repository.VideoRepository;
import com.Webdrama.Rodanthe.repository.WorkRepository;
import com.Webdrama.Rodanthe.service.WorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Log4j2
@RestController
@RequiredArgsConstructor
public class WorkController {

    @Autowired
    private final WorkRepository workRepository;

    @Autowired
    private final WorkService workService;

    @GetMapping("/workAll")
    public List<Work> getWorkAllInfo(){

        List<Work> workDtoList = workRepository.findAll();

        return workDtoList;
    }

    @PostMapping("/work/get/workId/{workId}")
    public void updateCoverImgUrl(@PathVariable Long workId){
        workService.updateCoverImg(workId);
    }

    @GetMapping("/work/{workId}")
    public Optional<Work> getWorkInfo(@PathVariable Long workId){
        Optional<Work> work = workRepository.findByWorkId(workId);
        return work;
    }

    @PostMapping("/work/search")
    public List<SearchWorkDto> giveSearchInfo(@RequestBody WorkSearchRequestDto workSearchDto){
        String searchWord = workSearchDto.getSearchWord();
        List<SearchWorkDto> works = workService.giveSearchWork(searchWord);

        return works;
    }

    @GetMapping("/main")
    public NumMainPageDto giveCountThings(){
        NumMainPageDto numMainPageDto = workService.giveCountThings();

        return numMainPageDto;
    }

}
