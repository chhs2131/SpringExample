package kr.ac.inha.board.login.dto;

import lombok.Data;

@Data
public class MemberDto {
	private long userNo;
	private String roleName;
	private String enabled;
	private String registDate;
}
