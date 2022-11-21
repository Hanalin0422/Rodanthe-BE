package com.Webdrama.Rodanthe.service;

import com.Webdrama.Rodanthe.entity.Video;
import com.Webdrama.Rodanthe.repository.VideoRepository;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final S3UploadService s3UploadService;

    public VideoService(VideoRepository videoRepository, S3UploadService s3UploadService){
        this.videoRepository = videoRepository;
        this.s3UploadService = s3UploadService;
    }

    public String createVideoEpisode(Long workId, Long episode){
        String path = s3UploadService.getVideoPath(workId, episode);
        return path;
    }

}
