package me.minho.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.minho.reservation.member.controller.MemberController;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.member.domain.MemberType;
import me.minho.reservation.member.controller.dto.SignupRequest;
import me.minho.reservation.common.ResultCode;
import me.minho.reservation.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MemberControllerTest {

    @Autowired
    MemberController mockMemberController;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MemberService mockMemberService;

    private MockMvc mockMvc;

    private SignupRequest signupRequest;

    @BeforeEach
    public void setUp () {
        mockMemberController = new MemberController(mockMemberService);
        mockMvc = MockMvcBuilders.standaloneSetup(mockMemberController).build();
        signupRequest = new SignupRequest();
        signupRequest.setEmail("test@test.com");
        signupRequest.setName("test");
        signupRequest.setPassword("test");
        signupRequest.setMemberType(MemberType.NORMAL);
    }

    @Test
    public void 회원가입_성공시_200_SUCCESS () throws Exception {
        doNothing().when(mockMemberService).signup(any(Member.class));

        ResultActions actions = mockMvc.perform(post("/members")
                .content(mapper.writeValueAsString(signupRequest))
                .contentType(MediaType.APPLICATION_JSON));

        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultCode").value(ResultCode.SUCCESS.name()));
    }

    @Test
    public void 회원가입_이메일이_존재하는_경우_200_ALREDY_EXISTS () throws Exception {
        doThrow(IllegalStateException.class).when(mockMemberService).signup(any(Member.class));

        ResultActions actions = mockMvc.perform(post("/members")
                .content(mapper.writeValueAsString(signupRequest))
                .contentType(MediaType.APPLICATION_JSON));

        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultCode").value(ResultCode.ALREADY_EXISTS.name()));
    }

}