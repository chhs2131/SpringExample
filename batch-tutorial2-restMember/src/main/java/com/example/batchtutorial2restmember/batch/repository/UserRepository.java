package com.example.batchtutorial2restmember.batch.repository;

import com.example.batchtutorial2restmember.batch.domain.User;
import com.example.batchtutorial2restmember.batch.domain.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // 인자값: 기준날짜(1년전 날짜받기), UserStatus 현상태를 받는다.
    List<User> findByUpdatedDateBeforeAndStatusEquals(LocalDateTime localDateTime, UserStatus status);
}
