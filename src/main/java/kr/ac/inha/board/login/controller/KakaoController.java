package kr.ac.inha.board.login.controller;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.inha.board.login.dto.KakaoApiUserDto;
import kr.ac.inha.board.login.dto.KakaoMemberDto;
import kr.ac.inha.board.login.dto.KakaoOAuthDto;
import kr.ac.inha.board.login.service.KakaoLoginService;

@Controller
public class KakaoController {
	@Autowired
	private KakaoLoginService kakaoLoginService;
	
	@RequestMapping(value="/login/kakaoLoginUrl", method=RequestMethod.GET)
	@ResponseBody
	public String kakaoLoginUrl() throws Exception {
		String kakaoLoginUrl = "https://kauth.kakao.com/oauth/authorize?client_id=a130d4bc5b0df2dd600ac87ffdda755a&redirect_uri=http://1.243.131.200:8080/login/kakao&response_type=code";
		return kakaoLoginUrl;
	}

	@RequestMapping(value="/login/kakao", method=RequestMethod.GET)
	@ResponseBody
	public KakaoApiUserDto kakaoLogin(String code) throws Exception {
		KakaoOAuthDto kakaoOAuthDto = kakaoLoginService.getToken(code);  //Token을 받아옵니다.
		KakaoApiUserDto kakaoApiUserDto = kakaoLoginService.getKakaoUid(kakaoOAuthDto.getAccessToken());  //카카오 계정정보를 받아옵니다.
		KakaoMemberDto kakaoMemberDto = kakaoLoginService.selectKakaoMember(kakaoApiUserDto.getId());  //DB에 기등록된 사용자인지 확인합니다.
		
		
		//boolean already = StringUtils.isEmpty(kakaoMemberDto.getKakaoUid());
		//boolean already = kakaoMemberDto.equals(null);
		if(kakaoMemberDto == null) { //DB에 등록되지 않았으면 신규등록합니다.
			System.out.println("신규등록진행");
			kakaoLoginService.insertKakaoMember(kakaoOAuthDto, kakaoApiUserDto);
		}
		
		return kakaoApiUserDto;
	}
	
	@RequestMapping(value="/login/kakaoTest")
	@ResponseBody
	public String kakaoTest() throws Exception {
		// DTO 객체 생성
		KakaoOAuthDto kakaoOAuthDto = new KakaoOAuthDto();
		
		// 데이터 값 입력
		kakaoOAuthDto.setAccessToken("abcdekAccessToken");
		kakaoOAuthDto.setExpiresIn(12345);
		kakaoOAuthDto.setRefreshToken("showmethemoneyRefreshToken");
		kakaoOAuthDto.setRefreshTokenExpiresIn(54321);
		kakaoOAuthDto.setScope("account_email profile");
		kakaoOAuthDto.setTokenType("bearer");
		System.out.println(kakaoOAuthDto);
		
		// JACKSON. DTO -> JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String memberJson = objectMapper.writeValueAsString(kakaoOAuthDto);
		System.out.println(memberJson);
		
		// JACKSON. JSON -> DTO
		KakaoOAuthDto jsonToDto = objectMapper.readValue(memberJson, KakaoOAuthDto.class);
		System.out.println(jsonToDto);
		
		// JACKSON. JSON(String) -> DTO
		String jsonString = "{\"access_token\":\"uZCcpm3QU0NqpXNq1b7Lf9Za6OIo2MlZFTL-lwo9dRkAAAF9_EWX1g\",\"token_type\":\"bearer\",\"refresh_token\":\"XT4OyYuClunH_qv6vuxdHNijYLmMfuH-HLEluwo9dRkAAAF9_EWX1Q\",\"expires_in\":21599,\"scope\":\"profile_nickname\",\"refresh_token_expires_in\":5183999}";
		jsonToDto = objectMapper.readValue(jsonString, KakaoOAuthDto.class);
		System.out.println(jsonToDto);
		
		return "nope";
	}
}
