package com.Webdrama.Rodanthe.repository;

import com.Webdrama.Rodanthe.entity.Jjim;
import com.Webdrama.Rodanthe.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JjimRepository extends JpaRepository<Jjim, Long> {
    List<Jjim> findAllById(Long id);
}
