package hello.inflearn_spring.repository;

import hello.inflearn_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 메서드 실행 이후 초기화를 위한 함수
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        System.out.println("저장된 모든 회원: " + repository.findAll());

        Optional<Member> result = repository.findByName("spring1");
        System.out.println("찾은 결과: " + result);

        assertThat(result).isPresent(); // Optional 비어있지 않음을 먼저 검증
        assertThat(result.get()).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);  // ✅ 수정된 부분
        assertThat(result).contains(member1, member2); // 💡 추가 검증 (선택)
    }
}
