package hello.inflearn_spring.service;

import hello.inflearn_spring.domain.Member;
import hello.inflearn_spring.repository.MemberRepository;
import hello.inflearn_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // Alt + insert(delete)사용하여 constructor 사용
    // 객체 생성이 아닌 외부에서 로직 생성
    // test에서 동일한 db사용 가능
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     회원가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원X
        /**
         Optional<Member> result = memberRepository.findByName(member.getName());
         result.ifPresent(m ->{
         throw new IllegalStateException("이미 존재하는 회원입니다.");

         });
         */
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // validateDuplicateMember 통과하면 저장
        return member.getId(); // return
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    /**
     전체 회원 조회
     */
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
