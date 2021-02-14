package me.minho.reservation.service;

import me.minho.reservation.domain.Member;
import me.minho.reservation.domain.MemberType;
import me.minho.reservation.repository.MemberRepository;
import me.minho.reservation.util.HashUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    private MemberService mockMemberService;

    @Mock
    private MemberRepository mockMemberRepository;

    private Member member;

    @BeforeEach
    public void setUp () throws Exception {
        mockMemberService = new MemberService(mockMemberRepository);
        member = new Member("test@test.com", HashUtil.hashPassword("testtest"), "test", MemberType.NORMAL);
    }

    @Test
    public void 존재하는_이메일로_가입하면_예외가_발생한다 () {
        // when
        when(mockMemberRepository.findByEmail("test@test.com")).thenReturn(member);

        // then
        assertThrows(IllegalStateException.class, () -> mockMemberService.signup(member));
    }

    @Test
    public void 존재하지_않는_이메일로_로그인을_시도하면_예외가_발생한다 () {
        // given
        String email = "test@test.com";
        String password = "testtest";

        // when
        when(mockMemberRepository.findByEmail(email)).thenReturn(null);

        // then
        assertThrows(IllegalArgumentException.class, () -> mockMemberService.login(email, password));
    }

    @Test
    public void 비밀번호가_틀린_경우_예외가_발생한다 () throws Exception {
        // given
        String email = "test@test.com";
        String password = "test";

        // when
        when(mockMemberRepository.findByEmail(email)).thenReturn(member);
        System.out.println(member.getHashedPassword());
        System.out.println(HashUtil.hashPassword("testtest"));

        // then
        assertThrows(IllegalArgumentException.class, () -> mockMemberService.login(email, password));
    }

}