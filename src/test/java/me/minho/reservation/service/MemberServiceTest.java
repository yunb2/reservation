package me.minho.reservation.service;

import me.minho.reservation.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintDeclarationException;

import static me.minho.reservation.domain.MemberType.NORMAL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("일반 사용자는 이메일, 비밀번호, 이름을 받아 생성한다.")
    public void joinTest() {
        Member testMember = Member.builder()
                .name("테스트")
                .email("mail@test.com")
                .password("test1234")
                .memberType(NORMAL)
                .build();

        Member saveMember = memberService.save(testMember);
        assertThat(testMember).isEqualTo(saveMember);
    }

    @Test
    @DisplayName("이메일 필드는 이메일 형식이어야 한다.")
    public void emailValidationTest() {
        Member testMember = Member.builder()
                .name("테스트")
                .email("mail")
                .password("test1234")
                .memberType(NORMAL)
                .build();

        assertThatThrownBy(() -> memberService.save(testMember), "이메일 필드는 이메일 형식이어야 한다.");
    }

    @Test
    @DisplayName("이름은 최소한 하나 이상의 문자가 들어가 있어야 한다")
    public void nameValidationTest() {
        Member testMember = Member.builder()
                .name(" ")
                .email("mail@test.com")
                .password("test1235")
                .memberType(NORMAL)
                .build();

        assertThatThrownBy(() -> memberService.save(testMember), "이름은 최소한 하나 이상의 문자가 들어가 있어야 한다");
    }

    @Test
    @DisplayName("비밀번호는 최소한 8자 이상이어야 한다.")
    public void passwordValidationTest() {
        Member testMember = Member.builder()
                .name("테스트")
                .email("mail@test.com")
                .password("test123")
                .memberType(NORMAL)
                .build();

        assertThatThrownBy(() -> memberService.save(testMember), "이름은 최소한 하나 이상의 문자가 들어가 있어야 한다");
    }
}