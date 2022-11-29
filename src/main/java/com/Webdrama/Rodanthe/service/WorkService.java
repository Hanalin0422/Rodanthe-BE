package com.Webdrama.Rodanthe.service;

import com.Webdrama.Rodanthe.dto.WorkDto;
import com.Webdrama.Rodanthe.dto.request.WorkRequestDto;
import com.Webdrama.Rodanthe.entity.Work;
import com.Webdrama.Rodanthe.repository.WorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Work updateWorkInfo(Long workId, WorkRequestDto workRequestDto){
        Optional<Work> workOptional = workRepository.findByWorkId(workId);
        if(workOptional.isPresent()){
            Work work = workOptional.get();

            WorkDto workDto = WorkDto.builder()
                    .workId(work.getWorkId())
                    .id(work.getId())
                    .description(workRequestDto.getDescription())
                    .genre(workRequestDto.getGenre())
                    .dayOfWeek(workRequestDto.getDayOfWeek())
                    .title(workRequestDto.getTitle())
                    .coverImg(work.getCoverImg())
                    .build();

            
            Work workEntity = workDto.toWork();
            return workEntity;

        }
        return null;
    }


/*    @Transactional
    public List<Work> getWork(Long userId){
        List<Work> workList = 0;
        Optional<Work> workOptional = workRepository.findById(userId);
        if(workOptional.isPresent()){
            Work work = workOptional.get();

            WorkDto workDto = WorkDto.builder()
                    .workId(work.getWorkId())
                    .id(work.getId())
                    .description(work.getDescription())
                    .genre(work.getGenre())
                    .dayOfWeek(work.getDayOfWeek())
                    .title(work.getTitle())
                    .coverImg(work.getCoverImg())
                    .build();
            workList.add(workDto);
            return workDto;
        }
        return null;
    }*/
}
