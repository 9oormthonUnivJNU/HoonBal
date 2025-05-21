package hello.inflearn_spring.repository;

import hello.inflearn_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    //Optional : Null 값 처리를 위해 사용
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
