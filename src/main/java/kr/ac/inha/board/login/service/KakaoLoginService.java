package kr.ac.inha.board.login.service;

import kr.ac.inha.board.login.dto.KakaoApiUserDto;
import kr.ac.inha.board.login.dto.KakaoMemberDto;
import kr.ac.inha.board.login.dto.KakaoOAuthDto;

public interface KakaoLoginService {
	KakaoOAuthDto getToken(String authCode) throws Exception;
	KakaoApiUserDto getKakaoUid(String accessToken) throws Exception;
	KakaoMemberDto selectKakaoMember(String kakaoUid) throws Exception;
	String insertKakaoMember(KakaoOAuthDto kakaoOAuthDto, KakaoApiUserDto kakaoApiUserDto) throws Exception;
}
