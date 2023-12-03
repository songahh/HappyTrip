package com.happytrip.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="MEMBER")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(length=12)
    private String memberId;
    @Column(length=15, nullable = false)
    private String memberName;
    @Column(length=15, nullable = false)
    private String memberPwd;
    @Column(length=15, nullable = false)
    private String emailId;
    @Column(length=15, nullable = false)
    private String emailDomain;
    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp joinDate;
    private String refreshToken;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType memberType;


}
