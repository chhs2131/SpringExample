package com.example.membermanagementexample.api.test.service;

import com.example.membermanagementexample.api.test.domain.Member;
import com.example.membermanagementexample.api.test.repository.MemberRepository;
import com.example.membermanagementexample.api.test.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
