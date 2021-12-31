package kr.ac.inha.board.login.service;

public interface KakaoLoginService {
	String getFirstToken(String authCode) throws Exception;
	String getKakaoUid(String accessToken) throws Exception;
}
