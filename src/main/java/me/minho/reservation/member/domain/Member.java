package me.minho.reservation.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.minho.reservation.reservation.domain.Shop;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private long id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_TYPE", nullable = false)
    private MemberType memberType;

    @Setter
    @OneToOne(mappedBy = "owner")
    private Shop shop;

    @Builder
    public Member(String email, String password, String name, MemberType memberType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.memberType = memberType;
    }

    public boolean isAdmin() {
        return memberType == MemberType.ADMIN;
    }
}
