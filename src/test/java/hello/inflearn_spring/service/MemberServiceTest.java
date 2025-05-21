package hello.inflearn_spring.service;

import hello.inflearn_spring.domain.Member;
import hello.inflearn_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberservice;
    MemoryMemberRepository memberRepository;
    // clear를 위해 MemoryMemberRepository 를 호출
    /*
    MemoryMemberRepository memberRepository =new MemoryMemberRepository();
    이렇게 될 경우 static으로 묶이지 않는 이상 main 폴더의 memberRepository랑 다른 DB를 사용하여 test 의미 사라짐
    따라서 main - memberservice의 객체를 변화해준다.
    */
    // 외부에서 객체를 넣어 같은 repository를 사용할 수 있게한다.
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberservice = new MemberService(memberRepository);
    }

    // 한번씩 돌 때마다 DB의 값을 다 날려줌
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberservice.join(member);

        //then
        Member findMember = memberservice.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberservice.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberservice.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

        /*
        중복 파악을 위해 try catch 문 사용은 너무 헤비함
        따라서 위의 assertThrows 구문으로 대체
        try{
            memberservice.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
        */

        //then
    }

    @Test
    void 회원조회() {
    }

    @Test
    void findOne() {
    }
}