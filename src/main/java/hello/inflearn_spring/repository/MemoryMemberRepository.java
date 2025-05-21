package hello.inflearn_spring.repository;

import hello.inflearn_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Null 값 반환가능성이 있으면 Optional로 감싸기
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> name.equals(member.getName()))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // store 초기화를 위한 함수 생성
    public void clearStore(){
        store.clear();
        sequence = 0L;
    }
}
