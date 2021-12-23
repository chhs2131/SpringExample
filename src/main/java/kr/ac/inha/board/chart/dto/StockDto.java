package kr.ac.inha.board.chart.dto;

import lombok.Data;

@Data
public class StockDto {
	private String jongmokCode;
	private String date;
	private int open;
	private int high;
	private int low;
	private int close;
	private long volume;
}
