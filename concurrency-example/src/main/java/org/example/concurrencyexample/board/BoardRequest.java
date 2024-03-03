package org.example.concurrencyexample.board;

import org.springframework.util.Assert;

public record BoardRequest(String title, String content) {
    public BoardRequest(String title, String content) {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(content, "제목은 필수입니다.");

        this.title = title;
        this.content = content;
    }
}
