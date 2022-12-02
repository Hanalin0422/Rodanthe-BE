package com.Webdrama.Rodanthe.dto.request;

import com.Webdrama.Rodanthe.entity.Jjim;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class JjimRequestDto {

    private Long jjimId;
    private Long id; // userId
    private Long workId;

    @Builder
    public JjimRequestDto(Long jjimId, Long id, Long workId){
        this.jjimId = jjimId;
        this.id = id;
        this.workId = workId;
    }

    public Jjim toJjim(){
        return Jjim.builder()
                .jjimId(jjimId)
                .id(id)
                .workId(workId)
                .build();
    }

}
