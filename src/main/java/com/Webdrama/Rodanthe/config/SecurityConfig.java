package com.Webdrama.Rodanthe.config;

import com.Webdrama.Rodanthe.security.jwt.JwtAccessDeniedHandler;
import com.Webdrama.Rodanthe.security.jwt.JwtAuthenticationEntryPoint;
import com.Webdrama.Rodanthe.security.jwt.JwtAuthenticationFilter;
import com.Webdrama.Rodanthe.security.oauth.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //설정 파일을 만들기 위한 어노테이션
@EnableWebSecurity //자동으로 SpringSecurityFilterChain이 포함됨
@Log4j2 // 기존 properties 파일 형식의 환경 설정 지원 x, XML or JSON 파일 형식의 환경 설정만 가능함
@RequiredArgsConstructor // 필드의 생성자를 자동으로 생성해주는 롬복 어노테이션
// @Secured 어노테이션을 사용하여 인가 처리하고 싶을때 & @PreAuthorize, @PostAuthorize 어노테이션을 사용하여 인가처리를 하고 싶을 때 사용하는 옵션
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Override
    public void configure(WebSecurity webSecurity) throws Exception{
        webSecurity.ignoring().antMatchers("/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http
                .cors()
                .and()
                .csrf().disable()
                .httpBasic().disable() // Basic Auth off
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .formLogin().disable()
                .oauth2Login()
                .authorizationEndpoint() // /oauth2/authorization/{provider}가 기본
                .authorizationRequestRepository(cookieAuthorizationRequestRepository)
                .and()
                .redirectionEndpoint() // /login/oauth2/code/{provider}가 기본
                .and()
                .userInfoEndpoint()// provider로부터 획득한 유저정보를 다룰 service class 지정함
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //401, 인증 과정에서 생길 exception 처리
                .accessDeniedHandler(jwtAccessDeniedHandler); //403, 인가 과정에서 생길 exception 처리

//        http
//                .logout() // 로그아웃 처리
//                .logoutUrl("/user/logout") // 로그아웃 처리 URL
//                .logoutSuccessUrl("/");

        // 모든 request에서 JWT를 검사할때 filter를 추가함, UsernamePasswordAuthenticationFilter에서 클라이언트가 요청한 리소스의 접근 권한이 없을때 막는 역할을 하여 토큰을 먼저 검사
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
