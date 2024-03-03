package org.example.concurrencyexample.board;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Board> findById(Long id);
}
