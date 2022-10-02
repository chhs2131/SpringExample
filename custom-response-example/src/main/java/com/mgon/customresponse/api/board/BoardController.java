package com.mgon.customresponse.api.board;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {
    @GetMapping("")
    public List<BoardDto> getBoardList() {
        return new ArrayList<BoardDto>() {{
            add(new BoardDto("1", "제목제목제목 001", "게시글내용테스트ㅇㅇㅇㅇㅇㅇ"));
            add(new BoardDto("2", "제목제목제목 002", "게시글내용테스트ㅇㅇㅇㅇㅇㅇ"));
            add(new BoardDto("3", "제목제목제목 003", "게시글내용테스트ㅇㅇㅇㅇㅇㅇ"));
            add(new BoardDto("4", "제목제목제목 004", "게시글내용테스트ㅇㅇㅇㅇㅇㅇ"));
        }};
    }

    @GetMapping("/{boardId}")
    public BoardDto getBoardDetail(@PathVariable("boardId") String id) {
        return BoardDto.builder()
                .id(id)
                .title("게시글 제목 입니다.")
                .content("게시글 내용 입니다.")
                .build();
    }
}
