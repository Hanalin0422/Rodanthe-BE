package com.Webdrama.Rodanthe.repository;

import com.Webdrama.Rodanthe.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long>{

    List<Video> findByWorkId(Long workId);

    @Query("SELECT COUNT(videoId) as cnt FROM Video")
    Long countVideo();

}
