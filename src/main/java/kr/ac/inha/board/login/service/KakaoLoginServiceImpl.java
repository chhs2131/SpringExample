package kr.ac.inha.board.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.inha.board.common.HttpConnection;
import kr.ac.inha.board.login.dto.KakaoApiUserDto;
import kr.ac.inha.board.login.dto.KakaoMemberDto;
import kr.ac.inha.board.login.dto.KakaoOAuthDto;
import kr.ac.inha.board.login.dto.MemberDto;
import kr.ac.inha.board.login.mapper.LoginMapper;


@Service
public class KakaoLoginServiceImpl implements KakaoLoginService {
	@Autowired
	private LoginMapper loginMapper;
	
	String kakaoRestKey = "e0b6130240281c4b18e88e405545754f";
    HttpConnection httpConnection = new HttpConnection();
	
	public KakaoOAuthDto getToken(String authCode) throws Exception {
		// 변수 선언
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoOAuthDto kakaoOAuthDto = new KakaoOAuthDto();
        String accessToken = "no_data";
    	String redirectUri = "http://1.243.131.200:8080/login/kakao";
        
        String apiUrl = "https://kauth.kakao.com/oauth/token?" + 
                    "grant_type=authorization_code&" + 
                    "client_id=" + kakaoRestKey + 
                    "&redirect_uri=" + redirectUri + 
                    "&code=" + authCode;

        // HTTP 통신
        String newResult = httpConnection.getMethod(apiUrl);

        // JSON 해석 및 kakaoOAuthDto 반환
        kakaoOAuthDto = objectMapper.readValue(newResult, KakaoOAuthDto.class);
		return kakaoOAuthDto;
	}

	public KakaoApiUserDto getKakaoUid(String accessToken) throws Exception {
		// 변수 초기화
		accessToken = "Bearer " + accessToken;
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoApiUserDto kakaoApiUserDto = new KakaoApiUserDto();
        String apiUrl = "https://kapi.kakao.com/v2/user/me?property_keys=%5B%22id%22%5D";

        Map<String,String> header = new HashMap<String,String>();
        header.put("Authorization", accessToken);

        // HTTP 통신
        String newResult = httpConnection.getMethod(apiUrl, header);

        // JSON 해석 및 kakaoApiUserDto 반환
        kakaoApiUserDto = objectMapper.readValue(newResult, KakaoApiUserDto.class);
		return kakaoApiUserDto;
	}

	public KakaoMemberDto selectKakaoMember(String kakaoUid) throws Exception {
		return loginMapper.selectKakaoMember(kakaoUid);
	}
	
	public MemberDto insertKakaoMember(KakaoOAuthDto kakaoOAuthDto, KakaoApiUserDto kakaoApiUserDto) throws Exception {
		//신규 계정 생성
		MemberDto memberDto = new MemberDto();
		loginMapper.insertMember(memberDto);
		
		//카카오 정보 정리
		KakaoMemberDto kakaoMemberDto = new KakaoMemberDto();
		kakaoMemberDto.setUserNo(memberDto.getUserNo());
		kakaoMemberDto.setKakaoUid(kakaoApiUserDto.getId());
		
		kakaoMemberDto.setAccessToken(kakaoOAuthDto.getAccessToken());
		//kakaoMemberDto.setRefreshDate(kakaoOAuthDto.get);
		//kakaoMemberDto.setExpiresIn(kakaoOAuthDto.getExpiresIn());
		kakaoMemberDto.setRefreshToken(kakaoOAuthDto.getRefreshToken());
		//kakaoMemberDto.setRefreshTokenExpiresIn(kakaoOAuthDto.getRefreshTokenExpiresIn());
		
		//카카오 계정정보 등록
		System.out.println(kakaoMemberDto);
        loginMapper.insertKakaoMember(kakaoMemberDto);
		return memberDto;
	}
	
	public MemberDto selectMember(long userNo) throws Exception {
		return loginMapper.selectMember(userNo);
	}

}




