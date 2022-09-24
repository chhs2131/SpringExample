package com.example.membermanagementexample.api.member.service;

import com.example.membermanagementexample.api.member.domain.Member;
import com.example.membermanagementexample.api.member.repository.JpaMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    // 내부에서 직접 new 로 생성하게 되면 새로운 객체가 되므로 차이가 발생할 수 있다.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 외부로부터 전달받게하면 동일한 객체를 사용하게되므로 안전하다.
    private final JpaMemberRepository memberRepository;

    public MemberService(JpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * register. join.
     */
    public Long join(Member member) {
        // 중복 이름은 가입을 못시키게 한다는 가정
        validateDuplicateMember(member);  // 중복회원검증

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 회원 검증
     * control^ + T 를 누르고 method 를 누르면 함수로 분리 가능
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->  {
                    throw new IllegalStateException("이미 존재하는 회원입니다. (이름중복)");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
