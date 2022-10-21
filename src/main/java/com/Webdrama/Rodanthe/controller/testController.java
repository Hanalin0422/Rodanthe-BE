package com.Webdrama.Rodanthe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class testController {

    @GetMapping("/test")
    public String test(){
        return "이건 인증 없어도 되지롱";
    }
}
