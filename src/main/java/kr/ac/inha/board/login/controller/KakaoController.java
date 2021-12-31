package kr.ac.inha.board.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.ac.inha.board.login.dto.KakaoOAuthDto;
import kr.ac.inha.board.login.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;

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
	public String kakaoLogin(String code) throws Exception {
		String token = kakaoLoginService.getFirstToken(code);
		String returnValue = kakaoLoginService.getKakaoUid(token);
		return returnValue;
	}

	@RequestMapping(value="/login/kakao2", method=RequestMethod.GET)
	@ResponseBody
	public String kakaoLogin2(String code) throws Exception {
		String returnValue = kakaoLoginService.getTest(code);
		return returnValue;
	}
	
	@RequestMapping(value="/login/ttt", method=RequestMethod.GET)
	@ResponseBody
	public String kakaoLogin3(String code) throws Exception {
		String returnValue = kakaoLoginService.testTest(code);
		return returnValue;
	}
	
	@RequestMapping(value="/login/kakaoUid", method=RequestMethod.GET)
	@ResponseBody
	public String kakaoUid(String accessToken) throws Exception {
		String token = kakaoLoginService.getKakaoUid(accessToken);
		return token;
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
