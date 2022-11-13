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
    private final S3UploadService s3UploadService;
    public WorkService(WorkRepository workRepository, S3UploadService s3UploadService){
        this.workRepository = workRepository;
        this.s3UploadService = s3UploadService;
    }

    public void updateCoverImg(Long workId){
        String path = s3UploadService.getCoverImgPath(workId);
        workRepository.saveCoverImgUrl(workId, path);
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
                    .title(work.getTitle())
                    .coverImg(work.getCoverImg())
                    .build();
            return workDto;
        }
        return null;
    }
}
