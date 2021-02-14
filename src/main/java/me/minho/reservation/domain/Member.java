package me.minho.reservation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member {

    @Getter
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private long id;

    @Getter
    @Column(name = "EMAIL", unique = true, nullable = false, columnDefinition = "char(")
    private String email;

    @Getter
    @Column(name = "HASHED_PASSWORD", nullable = false)
    private String hashedPassword;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_TYPE", nullable = false)
    private MemberType memberType;

    public Member(String email, String hashedPassword, String name, MemberType memberType) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.memberType = memberType;
    }

    public boolean isAdmin() {
        return memberType == MemberType.ADMIN;
    }
}
