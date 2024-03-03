package org.example.concurrencyexample.board;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "boards")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private int count;

    public Board(String title, String content) {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(content, "내용은 필수입니다.");

        this.title = title;
        this.content = content;
    }

    public int increaseViewCount() {
        return ++count;
    }
}
