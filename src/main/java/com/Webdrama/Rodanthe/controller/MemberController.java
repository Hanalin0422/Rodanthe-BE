package com.Webdrama.Rodanthe.controller;

import com.Webdrama.Rodanthe.entity.Member;
import com.Webdrama.Rodanthe.repository.MemberRepository;
import com.Webdrama.Rodanthe.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public Member getCurrentMember(@AuthenticationPrincipal CustomUserDetails member){
        return memberRepository.findById(member.getId()).orElseThrow(() -> new IllegalStateException("가입한 사용자를 확인할 수 없습니다."));
    }
}
