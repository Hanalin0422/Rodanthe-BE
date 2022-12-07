package com.Webdrama.Rodanthe.repository;

import com.Webdrama.Rodanthe.entity.Jjim;
import com.Webdrama.Rodanthe.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JjimRepository extends JpaRepository<Jjim, Long> {
    List<Jjim> findAllById(Long id);

    @Query("SELECT j.workId, w.coverImg, w.title FROM Jjim j LEFT JOIN Work w ON j.workId = w.workId WHERE w.id=:id")
    List<List<Object>> getJjimInfo(@Param("id") Long id);

}
