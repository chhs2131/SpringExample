package kr.ac.inha.board.board.dto;

import lombok.Data;

@Data
public class BoardDto {
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String createdDatetime;
	private String creatorId;
	private String updateDatetime;
	private String updaterId;
	private String deletedYn;
}
