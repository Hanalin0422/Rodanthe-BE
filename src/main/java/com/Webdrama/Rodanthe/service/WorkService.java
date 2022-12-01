package com.Webdrama.Rodanthe.service;

import com.Webdrama.Rodanthe.dto.SearchWorkDto;
import com.Webdrama.Rodanthe.dto.WorkDto;
import com.Webdrama.Rodanthe.dto.request.WorkRequestDto;
import com.Webdrama.Rodanthe.entity.Work;
import com.Webdrama.Rodanthe.repository.WorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
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

    @Transactional
    public List<SearchWorkDto> giveSearchWork(String searchWord) {
        if(searchWord.trim() == "" ){
            return null;
        }else{
            List<Work> workList = this.workRepository.searchWork(searchWord);
            List<SearchWorkDto> searchWorkDtoList = new ArrayList<>();

            for(int i=0; i<workList.size(); i++){
                Work work = workList.get(i);
                SearchWorkDto searchWorkDto = SearchWorkDto.builder()
                        .workId(work.getWorkId())
                        .coverImg(work.getCoverImg())
                        .title(work.getTitle())
                        .build();
                searchWorkDtoList.add(searchWorkDto);
            }
            return searchWorkDtoList;
        }
    }

    public void deleteWork(Long workId){
        Optional<Work> workOptional = workRepository.findByWorkId(workId);
        if(workOptional.isPresent()){
            Work work = workOptional.get();
            workRepository.delete(work);
        }
    }
}
