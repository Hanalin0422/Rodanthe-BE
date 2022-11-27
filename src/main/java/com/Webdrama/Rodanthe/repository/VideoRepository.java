package com.Webdrama.Rodanthe.repository;

import com.Webdrama.Rodanthe.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long>{

    List<Video> findByWorkId(Long workId);
}
