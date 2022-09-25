package com.example.membermanagementexample.api.member.repository;

import com.example.membermanagementexample.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepositroy를 extends로 가지면 SpringDataJpa의 구현체가 자동으로 스프링 @Bean 등록을 자동으로 해준다
public interface SpringDataJpaRepository extends JpaRepository<Member, Long>, MemberRepository {
    // JpaRepository 에 기본적으로 각 기능들이 구현이 되어있다. (단순조회 및 CRUD 들)
    @Override
    Optional<Member> findByName(String name);  // 하지만 name 처럼 비즈니스 마다 이름이 다른경우에는 별도로 선언을 해줘야된다. 메소드 선언시 규칙에 맞춰서 넣으면 알아서 만들어준다.

    // 하지만 엄청 복잡한건 위 내용으로 할 수 없으니, 추가적인 기능 사용이 필요하다. -> QueryDSL 또는 nativeQuery, 아니면 다른 기술 ex mybatis
}
