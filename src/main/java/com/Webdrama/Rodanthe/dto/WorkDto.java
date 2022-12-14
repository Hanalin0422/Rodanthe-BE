package com.Webdrama.Rodanthe.dto;

import com.Webdrama.Rodanthe.entity.Work;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkDto {
    private Long workId;
    private Long id;
    private String title;
    private String dayOfWeek;
    private String genre;
    private String description;
    private String coverImg;

    @Builder
    public WorkDto(Long workId, Long id, String title, String dayOfWeek, String genre, String description, String coverImg){
        this.workId = workId;
        this.id = id;
        this.title = title;
        this.dayOfWeek = dayOfWeek;
        this.genre = genre;
        this.description = description;
        this.coverImg = coverImg;
    }

    public Work toWork(){
        return Work.builder()
                .workId(workId)
                .id(id)
                .title(title)
                .dayOfWeek(dayOfWeek)
                .genre(genre)
                .description(description)
                .coverImg(coverImg)
                .build();
    }
}
