package com.Webdrama.Rodanthe.service;

import com.Webdrama.Rodanthe.dto.JjimDto;
import com.Webdrama.Rodanthe.dto.SearchWorkDto;
import com.Webdrama.Rodanthe.dto.WorkDto;
import com.Webdrama.Rodanthe.dto.request.WorkRequestDto;
import com.Webdrama.Rodanthe.entity.Jjim;
import com.Webdrama.Rodanthe.entity.Work;
import com.Webdrama.Rodanthe.repository.JjimRepository;
import com.Webdrama.Rodanthe.repository.WorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class WorkService {

    private WorkRepository workRepository;
    private JjimRepository jjimRepository;
    private final S3UploadService s3UploadService;
    public WorkService(WorkRepository workRepository, S3UploadService s3UploadService, JjimRepository jjimRepository){
        this.workRepository = workRepository;
        this.s3UploadService = s3UploadService;
        this.jjimRepository = jjimRepository;
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

    public boolean jjimGiveInfo(Long userId, Long workId){
        List<Jjim> jjimList = jjimRepository.findAllById(userId);
        boolean flag = false;
        for(int i=0; i<jjimList.size(); i++){
            Jjim jjim = jjimList.get(i);
            if(jjim.getWorkId() == workId){
                flag = true;
                break;
            }else{
                flag = false;
            }
        }
        return flag;
    }

    public void deleteJjim(Long userId, Long workId){
        List<Jjim> jjimList = jjimRepository.findAllById(userId);
        for(int i=0; i<jjimList.size(); i++){
            Jjim jjim = jjimList.get(i);
            if(jjim.getWorkId() == workId){
                jjimRepository.delete(jjim);
                break;
            }
        }
    }

/*
    public List<JjimDto> jjimView(Long id){
        List<List<Object>> listJjim = jjimRepository.getJjimInfo(id);
        List<JjimDto> jjimDtoList = new ArrayList<>();

       for(int i=0; i<listJjim.size(); i++){
           Long workId = Long.valueOf(String.valueOf(listJjim.get(i).get(0)));
           String coverImg = String.valueOf(listJjim.get(i).get(1));
           String title = String.valueOf(listJjim.get(i).get(2));

           JjimDto jjimDto = new JjimDto(workId,coverImg, title );
           jjimDtoList.add(jjimDto);
       }

        return jjimDtoList;
    }
*/

    public List<JjimDto> jjimView(Long userId){
        List<Jjim> jjimList = jjimRepository.findAllById(userId);
        if(!jjimList.isEmpty()){
            List<JjimDto> jjimDtoList = new ArrayList<>();
            for(int i=0; i< jjimList.size(); i++){
                Jjim jjim = jjimList.get(i);
                Long workId = jjim.getWorkId();
                Optional<Work> work = workRepository.findByWorkId(workId);

                JjimDto jjimDto = new JjimDto(work.get().getWorkId(), work.get().getCoverImg(), work.get().getTitle());
                jjimDtoList.add(jjimDto);
            }
            return jjimDtoList;
        }
        return null;
    }
}
