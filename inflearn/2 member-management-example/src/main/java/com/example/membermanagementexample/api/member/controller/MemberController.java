package com.example.membermanagementexample.api.member.controller;

import com.example.membermanagementexample.api.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
// 스프링 컨테이너에 컨트롤러 객체를 생성해 넣어두고 관리한다.
// (스프링 빈으로 등록하는 방법중 하나이며, 기본적으로 싱글턴 패턴이 적용된다. 따라서 같은 스프링빈 == 같은 인스턴스)
// @Controller 내부로직확인시 @Componet 인 것을 알 수 있다.
// 즉, 해당 방식을 컴포넌트 스캔이라고 부른다.
// 단, 메인문이 있는 패키지와 그 하위 패키지 부터만 @컴포넌트 스캔 적용 대상이다.
// Controller 는 무조건 @컴포넌트로 등록 해야함
public class MemberController {
    // [ 0번 방법 ]
    // 이런식으로 매번 생성하기보다는, 하나만 생성을 해두고 공용으로 쓰면 좋을 것이다.
    // private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    // [ 1번 방법 : 생성자 주입 ]
    // 의존관계가 실행중(런타임)에 바뀔일은 거의 없으므로, 그냥 생성자 주입을 쓰는게 좋다.
    // Autowired 를 사용하면 스프링 컨테이너에서 `MemberService` 를 찾아서 주입해준다. (단, @Service를 사용해서 컨테이너에 등록해야함)
    // 참고로 @Autowired 는 소속 객체(현재는 MemberController)가 컨테이너에 등록되어있어야만 동작한다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // [ 2번 방법 : 필드 주입 ]
    // 권장되지 않는 방법
    // @Autowired private final MemberService memberService;

    // [ 3번 방법 : setter 주입 ]
    // setter 가 public 하게 노출되게 되는 단점이 있다. (로딩 시점이후, 런타임에 되면 문제 발생 가능성)
    // @Autowired setMemberSerivce ...

}
