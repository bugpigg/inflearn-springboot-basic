package com.example.repository;

import com.example.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 하지만 모든 쿼리에 대해 메서드를 대응시킬수는 없으니깐
    // 아래와 같은 메서드만 정의해놓으면 된다.
    // JPQL select * from Member m where m.name = ?
    // 메서드 이름의 규칙이 있다!
    @Override
    Optional<Member> findByName(String name);
}
