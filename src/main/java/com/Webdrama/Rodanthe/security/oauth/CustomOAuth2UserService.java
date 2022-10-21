package com.Webdrama.Rodanthe.security.oauth;

import com.Webdrama.Rodanthe.common.AuthProvider;
import com.Webdrama.Rodanthe.common.MemberRole;
import com.Webdrama.Rodanthe.entity.Member;
import com.Webdrama.Rodanthe.exception.OAuthProcessingException;
import com.Webdrama.Rodanthe.repository.MemberRepository;
import com.Webdrama.Rodanthe.security.CustomUserDetails;
import com.Webdrama.Rodanthe.security.oauth.user.OAuth2UserInfo;
import com.Webdrama.Rodanthe.security.oauth.user.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

// loadUser()를 오버라이드해서 OAuth2UserRequest에 있는 AccessToken으로 유저 정보를 얻을 거임
// 획득한 유저 정보를 process()에서 Java model이랑 mapping 하고 가입되지 않은 경우, 이미 가입한 경우를 구분해 프로세스 진행
// 결과로 OAuth2User를 구현한 CustomUserDetails 객체 생성
@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    // OAuth2UserRequest에 있는 Access Token으로 유저 정보를 얻음
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException{
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        return process(oAuth2UserRequest, oAuth2User);
    }

    // 획득한 유저 정보를 Java model이랑 mapping하고 프로세스 진행함
    private OAuth2User process(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User){
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());

        if(userInfo.getEmail().isEmpty()){
            throw new OAuthProcessingException("OAuth2에서 제공하는 이메일을 찾을 수가 없습니다.");
        }

        Optional<Member> memberOptional = memberRepository.findByEmail(userInfo.getEmail());
        Member member;

        // 이미 가입이 된 경우
        if(memberOptional.isPresent()){
            member = memberOptional.get();
            if(authProvider != member.getAuthProvider()){
                throw new OAuthProcessingException("잘못된 OAuth2 제공자 입니다.");
            }
        }else{
            member = createMember(userInfo, authProvider);
        }
        return CustomUserDetails.create(member, oAuth2User.getAttributes());
    }

    private Member createMember(OAuth2UserInfo userInfo, AuthProvider authProvider){
        Member member = Member.builder()
                .email(userInfo.getEmail())
                .name(userInfo.getName())
                .img(userInfo.getImageUrl())
                .role(MemberRole.USER)
                .authProvider(authProvider)
                .build();
        return memberRepository.save(member);
    }
}
