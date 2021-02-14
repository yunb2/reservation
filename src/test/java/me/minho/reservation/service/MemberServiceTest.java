package me.minho.reservation.service;

import me.minho.reservation.domain.Member;
import me.minho.reservation.domain.MemberType;
import me.minho.reservation.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    private MemberService mockMemberService;

    @Mock
    private MemberRepository mockMemberRepository;

    @BeforeEach
    public void setUp () {
        mockMemberService = new MemberService(mockMemberRepository);
    }

    @Test
    public void 존재하는_이메일로_가입하면_예외가_발생한다 () {
        // given
        String email = "test@test.com";
        Member member = new Member(email, "testtest", "test", MemberType.NORMAL);
        List<Member> memberList = List.of(member);

        // when
        when(mockMemberRepository.findByEmail(email)).thenReturn(memberList);

        // then
        assertThrows(IllegalStateException.class, () -> mockMemberService.signup(member));
    }

}