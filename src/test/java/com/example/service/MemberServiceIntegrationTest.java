package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.domain.Member;
import com.example.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 요거를 테스트에 달면 db 에 쿼리 다 날리고, 나중에 롤백 해준다. // 각 테스트에 모두 적용된다.
class MemberServiceIntegrationTest {

    // testcase 는 그냥 autowired 를 쓰자!
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

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
}
