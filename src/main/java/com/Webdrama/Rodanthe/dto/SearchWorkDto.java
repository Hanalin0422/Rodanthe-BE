package com.Webdrama.Rodanthe.dto;

import com.Webdrama.Rodanthe.entity.Work;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchWorkDto {
    private Long workId;
    private String coverImg;
    private String title;


    @Builder
    public SearchWorkDto(Long workId,String coverImg, String title){
        this.workId = workId;
        this.coverImg = coverImg;
        this.title = title;
    }

}
