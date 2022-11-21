package com.Webdrama.Rodanthe.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="work")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;

    @Column
    private Long id; //userId ex)1, 2, 3 ,,,

    @Column
    private String title;

    @Column
    private String dayOfWeek;

    @Column
    private String genre;

    @Column
    private String description;

    @Column
    private String coverImg;
}
