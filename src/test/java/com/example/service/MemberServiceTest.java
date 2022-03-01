package com.example.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.Member;
import com.example.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void getMemberService() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // 이런거를 MemberService 입장에서 보면 직접 new 하지 않고 주입 받는다.
        // 이를 DI 라고 한다.
    }
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member m1 = new Member();
        m1.setName("spring");
        Member m2 = new Member();
        m2.setName("spring");

        // when
        memberService.join(m1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.join(m2));

        // 추가적인 예외 메세지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
