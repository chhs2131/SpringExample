package kr.ac.inha.board.chart.service;

import java.util.List;

import kr.ac.inha.board.chart.dto.StockDto;

public interface ChartService {
	List<StockDto> selectStockByJongmok(String jongmokCode) throws Exception;
}
