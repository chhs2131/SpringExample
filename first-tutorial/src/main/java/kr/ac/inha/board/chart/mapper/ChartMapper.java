package kr.ac.inha.board.chart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.inha.board.chart.dto.StockDto;

@Mapper
public interface ChartMapper {
	List<StockDto> selectStockByJongmok(String jongmokCode) throws Exception;
}
