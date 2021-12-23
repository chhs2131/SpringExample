package kr.ac.inha.board.ipo.dto;

import lombok.Data;

@Data
public class IpoDto {
	private int ipoIndex;
	private String stockName;
	private String stockExchange;
	private String stockCode;
	private String sector;
	private int ipoPrice;
	private double lockUpPercent;
	private double ipoRetailAcceptanceRate;
	private double ipoInstitutionalAcceptanceRate;
	private int debutPrice;
	private int numberOfIpoShares;
	private String ipoStartDate;
	private String ipoRefundDate;
	private String ipoDebutDate;
	private String underwriter;
}
