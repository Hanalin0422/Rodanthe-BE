package com.Webdrama.Rodanthe.dto.request;

import com.Webdrama.Rodanthe.entity.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VideoRequestDto {
    private Long workId;
    private Long videoId;
    private String videoUrl;
    private Long episode;

    @Builder
    public VideoRequestDto(Long videoId, Long workId, String videoUrl, Long episode){
        this.videoId = videoId;
        this.workId = workId;
        this.videoUrl = videoUrl;
        this.episode = episode;
    }

    public Video toVideo(){
        return Video.builder()
                .videoId(videoId)
                .workId(workId)
                .videoUrl(videoUrl)
                .episode(episode)
                .build();
    }
}

