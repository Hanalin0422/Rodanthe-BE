package com.Webdrama.Rodanthe.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성해줌
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자를 만들어줌
@Builder
@Entity
@Table(name="jjim")
public class Jjim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jjimId;

    @Column
    private Long id; //userId ex)1, 2, 3 ,,,

    @Column
    private Long workId;

}
