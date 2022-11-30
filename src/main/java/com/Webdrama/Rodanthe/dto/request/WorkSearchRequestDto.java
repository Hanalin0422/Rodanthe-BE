package com.Webdrama.Rodanthe.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkSearchRequestDto {

    private String searchWord;

    @Builder
    public WorkSearchRequestDto(String searchWord){
        this.searchWord = searchWord;
    }
}
