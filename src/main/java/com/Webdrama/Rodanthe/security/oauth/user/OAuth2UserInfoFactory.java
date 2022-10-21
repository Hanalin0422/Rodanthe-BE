package com.Webdrama.Rodanthe.security.oauth.user;

import com.Webdrama.Rodanthe.common.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes){
        switch (authProvider){
            case GOOGLE : return new GoogleOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("유효하지 않은 유형입니다.");
        }
    }
}
