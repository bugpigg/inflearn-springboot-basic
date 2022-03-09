package com.example;

import com.example.repository.JdbcMemberRepository;
import com.example.repository.JdbcTemplateMemberRepository;
import com.example.repository.JpaMemberRepository;
import com.example.repository.MemberRepository;
import com.example.repository.MemoryMemberRepository;
import com.example.service.MemberService;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
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

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
