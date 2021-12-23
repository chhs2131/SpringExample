package kr.ac.inha.board.ipo.dto;

import lombok.Data;

@Data
public class SystemLogDto {
	private int logIndex;
	private String type;
	private String target;
	private String result;
	private String logDate;
}
