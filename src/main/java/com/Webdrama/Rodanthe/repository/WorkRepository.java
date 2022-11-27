package com.Webdrama.Rodanthe.repository;

import com.Webdrama.Rodanthe.entity.Work;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface WorkRepository extends JpaRepository<Work, Long> {
    Optional<Work> findById(Long id);
    Optional<Work> findByWorkId(Long workId);



    @Transactional
    @Modifying
    @Query("UPDATE Work w SET w.coverImg=:coverImgUrl WHERE w.workId=:workId")
    void saveCoverImgUrl(@Param("workId") Long workId, @Param("coverImgUrl") String coverImgUrl);

    List<Work> findAllById(Long id);

}
