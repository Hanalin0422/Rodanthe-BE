package com.Webdrama.Rodanthe.controller;

import com.Webdrama.Rodanthe.dto.WorkDto;
import com.Webdrama.Rodanthe.entity.Work;
import com.Webdrama.Rodanthe.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class WorkController {

    @Autowired
    private final WorkRepository workRepository;

    @GetMapping("/workAll")
    public List<Work> getWorkAllInfo(){
        List<Work> workDtoList = workRepository.findAll();

        return workDtoList;
    }
}
