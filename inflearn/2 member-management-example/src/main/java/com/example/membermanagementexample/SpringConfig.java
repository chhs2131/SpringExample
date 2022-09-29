package com.example.membermanagementexample;

import com.example.membermanagementexample.api.member.repository.MemberRepository;
import com.example.membermanagementexample.api.member.repository.MemoryMemberRepository;
import com.example.membermanagementexample.api.member.service.MemberService;
import com.example.membermanagementexample.global.aop.TimeTraceAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // @컴포넌트를 사용하지 않고 자바코드로 @빈 으로 등록하는 방법
    // @컴포넌트 스캔과는 달리, 자바코드로 직접 등록한 경우 추후 연결에 사용되는 객체를 변경할 때 편한다.
    // 예를 들어 memberRepo sitory 에 연결되는 객체를 inMemory -> mysql 로 변경하는 경우, 여기서 내부로직만 수정하면 된다.

//    @Bean
//     public TimeTraceAop timeTraceAop() {  // 특별한 Bean의 경우 직접등록해주는게 확인하기가 좋음
//        return new TimeTraceAop();
//    }
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
