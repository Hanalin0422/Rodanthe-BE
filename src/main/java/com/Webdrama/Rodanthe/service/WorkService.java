package com.Webdrama.Rodanthe.service;

import com.Webdrama.Rodanthe.dto.WorkDto;
import com.Webdrama.Rodanthe.entity.Work;
import com.Webdrama.Rodanthe.repository.WorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WorkService {

    private WorkRepository workRepository;
    public WorkService(WorkRepository workRepository){
        this.workRepository = workRepository;
    }

    @Transactional
    public WorkDto getWork(Long userId){
        Optional<Work> workOptional = workRepository.findById(userId);
        if(workOptional.isPresent()){
            Work work = workOptional.get();

            WorkDto workDto = WorkDto.builder()
                    .id(work.getId())
                    .description(work.getDescription())
                    .genre(work.getGenre())
                    .dayOfWeek(work.getDayOfWeek())
                    .thumbnailImg(work.getThumbnailImg())
                    .title(work.getTitle())
                    .build();
            return workDto;
        }
        return null;
    }
}
