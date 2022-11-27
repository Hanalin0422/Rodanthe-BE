package com.Webdrama.Rodanthe.controller;

import com.Webdrama.Rodanthe.dto.request.VideoRequestDto;
import com.Webdrama.Rodanthe.entity.Video;
import com.Webdrama.Rodanthe.repository.VideoRepository;
import com.Webdrama.Rodanthe.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class VideoController {

    @Autowired
    private final VideoRepository videoRepository;

    @Autowired
    private final VideoService videoService;

    @PostMapping("/video/getInfo")
    public void getVideoWorkId(@RequestBody VideoRequestDto videoRequestDto){
        Long workId = videoRequestDto.getWorkId();
        Long episode = videoRequestDto.getEpisode();
        String videoUrl = videoService.createVideoEpisode(workId, episode);

        videoRequestDto.setVideoUrl(videoUrl);
        Video video = videoRequestDto.toVideo();
        videoRepository.save(video);
    }

    @GetMapping("/work/videoList/{workId}")
    public List<Video> getVideoInfo(@PathVariable Long workId){
        List<Video> videos = videoRepository.findByWorkId(workId);
        return videos;
    }

}
