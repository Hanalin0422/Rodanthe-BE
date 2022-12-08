package com.Webdrama.Rodanthe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NumMainPageDto {

    private Long workCount; //작품 수
    private Long userCount; //유저 수
    private Long videoCount; // 동영상 수

    public NumMainPageDto(Long workCount, Long userCount, Long videoCount){
        this.workCount = workCount;
        this.userCount = userCount;
        this.videoCount = videoCount;
    }

}
