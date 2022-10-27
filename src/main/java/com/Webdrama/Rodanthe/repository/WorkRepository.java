package com.Webdrama.Rodanthe.repository;

import com.Webdrama.Rodanthe.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface WorkRepository extends JpaRepository<Work, Long> {
    Optional<Work> findById(Long id);
}
