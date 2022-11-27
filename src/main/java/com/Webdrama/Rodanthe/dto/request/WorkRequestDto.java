package com.Webdrama.Rodanthe.dto.request;

import com.Webdrama.Rodanthe.entity.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkRequestDto {
    private String title;
    private String dayOfWeek;
    private String genre;
    private String description;

    @Builder
    public WorkRequestDto(String title, String dayOfWeek, String genre, String description){
        this.title = title;
        this.dayOfWeek = dayOfWeek;
        this.genre = genre;
        this.description = description;
    }

}
