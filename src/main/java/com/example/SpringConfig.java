package com.example;

import com.example.aop.TimeTraceAop;
import com.example.repository.MemberRepository;
import com.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired // spring 이 application.properties 보고 미리 datasource 를 빈으로 만들어 둔다. 그래서 이렇게 주입 가능하다.
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//---------------------------------------------------------
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository; // SPRING JPA 가 SpringDataJpaMemberRepository 의 구현체를 알아서 만들고 빈으로 등록해놓는다. 우리는 인젝션 받기만 하면 된다.

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
