package com.Webdrama.Rodanthe.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @Column
    private Long workId;

    @Column
    private String videoUrl;

    @Column
    private String episodeTitle;

    @Column
    private Long episode; // 몇화인지 표시하기 위한거


}
