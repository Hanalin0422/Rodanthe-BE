package com.Webdrama.Rodanthe.entity;

import com.Webdrama.Rodanthe.common.AuthProvider;
import com.Webdrama.Rodanthe.common.MemberRole;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성해줌
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자를 만들어줌
@Builder
@Entity
@Table(name="member")
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String name;

    private String img;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    private String refreshToken;
}
