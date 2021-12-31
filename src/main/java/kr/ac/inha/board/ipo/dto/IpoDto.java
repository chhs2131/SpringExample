package kr.ac.inha.board.ipo.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class IpoDto {
	@ApiParam(value="인덱스 넘버", required=true)
	private int ipoIndex;
	@ApiParam(value="종목명")
	private String stockName;
	private String stockExchange;
	private String stockCode;
	private String sector;
	private int ipoPrice;
	private double lockUpPercent;
	private double ipoRetailAcceptanceRate;
	private double ipoInstitutionalAcceptanceRate;
	@ApiParam(value="상장당일 시세")
	private int debutPrice;
	private int numberOfIpoShares;
	private String ipoStartDate;
	private String ipoRefundDate;
	@ApiParam(value="상장일자")
	private String ipoDebutDate;
	private String underwriter;
}
