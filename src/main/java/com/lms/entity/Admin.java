package com.lms.entity;

import com.lms.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "admin")
@Getter
@Setter
@ToString
public class Admin {

    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.AUTO) /*자동증가*/
    private Long id;

    /* 로그인 아이디 */
    @Column(nullable = false, length = 50, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    /* 사용자 이름 */
    @Column(nullable = false, name = "name")
    private String name;

    /* 휴대폰 번호 */
    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String position; //주요업무

    @Column(nullable = false)
    private LocalDateTime regDate;

    @Enumerated(EnumType.STRING) //
    private Role role; //권한
}
