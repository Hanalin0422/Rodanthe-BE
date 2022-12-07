package com.Webdrama.Rodanthe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JjimDto {
    private Long workId;
    private String coverImg;
    private String title;

    public JjimDto (Long workId, String coverImg, String title){
        this.workId = workId;
        this.coverImg = coverImg;
        this.title = title;
    }
}
