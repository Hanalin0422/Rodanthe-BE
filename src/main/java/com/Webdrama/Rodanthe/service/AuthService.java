package com.Webdrama.Rodanthe.service;

import com.Webdrama.Rodanthe.repository.MemberRepository;
import com.Webdrama.Rodanthe.security.CustomUserDetails;
import com.Webdrama.Rodanthe.security.jwt.JwtTokenProvider;
import com.Webdrama.Rodanthe.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${app.auth.token.refresh-cookie-key}")
    private String cookieKey;

    private final MemberRepository memberRepository;
    private final JwtTokenProvider tokenProvider;

    public String refreshToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken){
        // refreshToken이 유효한지 확인하기
        String oldRefreshToken = CookieUtil.getCookie(request, cookieKey)
                .map(Cookie::getValue).orElseThrow(()-> new RuntimeException("Refresh Token Cookie가 없습니다."));

        if(!tokenProvider.validateToken(oldRefreshToken)){
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        // 유저정보 얻기
        Authentication authentication = tokenProvider.getAuthentication(oldAccessToken);
        CustomUserDetails member = (CustomUserDetails) authentication.getPrincipal();

        Long id = Long.valueOf(member.getName());

        // 저장해둔 Refresh Token이랑 같은지 맞춰보기
        String savedToken = memberRepository.getRefreshTokenById(id);

        if (!savedToken.equals(oldRefreshToken)) {
            throw new RuntimeException("Refresh Token이 올바르지 않습니다.");
        }

        // JWT 갱신
        String accessToken = tokenProvider.createAccessToken(authentication);
        tokenProvider.createRefreshToken(authentication, response);

        return accessToken;
    }
}
