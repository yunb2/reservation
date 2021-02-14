package me.minho.reservation.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @OneToOne(mappedBy = "owner")
    private Shop shop;

    @Builder
    public Member(String email, String password, String name, MemberType memberType, Shop shop) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.memberType = memberType;
        this.shop = shop;
    }

    public boolean isAdmin() {
        return memberType == MemberType.ADMIN;
    }
}
