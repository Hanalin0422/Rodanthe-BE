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

    @Builder
    public WorkDto(Long workId, Long id, String title, String dayOfWeek, String genre, String description){
        this.workId = workId;
        this.id = id;
        this.title = title;
        this.dayOfWeek = dayOfWeek;
        this.genre = genre;
        this.description = description;
    }

    public Work toWork(){
        return Work.builder()
                .workId(workId)
                .id(id)
                .title(title)
                .dayOfWeek(dayOfWeek)
                .genre(genre)
                .description(description)
                .build();
    }
}
