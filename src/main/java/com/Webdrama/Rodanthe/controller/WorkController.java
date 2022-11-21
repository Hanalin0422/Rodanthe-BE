package com.Webdrama.Rodanthe.controller;

import com.Webdrama.Rodanthe.entity.Work;
import com.Webdrama.Rodanthe.repository.WorkRepository;
import com.Webdrama.Rodanthe.service.WorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
