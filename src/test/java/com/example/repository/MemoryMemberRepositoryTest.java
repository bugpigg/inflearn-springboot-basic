package com.example.repository;

import static org.assertj.core.api.Assertions.*;

import com.example.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void saveTest() {
        Member member1 =  new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member result = memberRepository.findById(member2.getId()).get();

        assertThat(member2).isEqualTo(result);
    }

    @Test
    void findByNameTest() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    void findAllTest() {
        Member m1 = new Member();
        m1.setName("spring1");
        memberRepository.save(m1);


        Member m2 = new Member();
        m2.setName("spring2");
        memberRepository.save(m2);

        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
