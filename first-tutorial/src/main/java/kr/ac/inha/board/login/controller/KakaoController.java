package kr.ac.inha.board.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import kr.ac.inha.board.login.dto.KakaoApiUserDto;
import kr.ac.inha.board.login.dto.KakaoMemberDto;
import kr.ac.inha.board.login.dto.KakaoOAuthDto;
import kr.ac.inha.board.login.dto.MemberDto;
import kr.ac.inha.board.login.service.KakaoLoginService;

@Controller
@Api(value = "KakaoController", description = "카카오 OAuth 로그인을 위한 API")
public class KakaoController {
	@Autowired
	private KakaoLoginService kakaoLoginService;
	
	@RequestMapping(value="/login/kakaoLoginUrl", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="백엔드 테스트용", notes="카카오 AuthCode 취득을 위한 URI 반환") 
	public String kakaoLoginUrl() throws Exception {
		String kakaoLoginUrl = "https://kauth.kakao.com/oauth/authorize?client_id=e0b6130240281c4b18e88e405545754f&redirect_uri=http://1.243.131.200:8080/login/kakao&response_type=code";
		return kakaoLoginUrl;
	}

	
	@RequestMapping(value={"/login/kakao"}, method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="카카오 OAuth2.0 (REST:AuthCode)", notes="카카오 AuthCode를 전달하고, 서버의 계정정보값을 취득합니다."
			+ "<br/>카카오 로그인시 RedirectURI를 해당으로 설정하여 진행합니다.<br/><br/>단, 카카오 보안규칙상 AuthCode 취득자와 이용자가 동일해야하므로 사용시 주의필요."
			+ "<br/>Redirect URI 예시: https://kauth.kakao.com/oauth/authorize?client_id=a130d4bc5b0df2dd600ac87ffdda755a&redirect_uri=http://1.243.131.200:8080/login/kakao&response_type=code")
    @ApiImplicitParam(
            name = "code"
            , value = "카카오에서 부여받은 AuthCode"
            , required = true
            , dataType = "string"
            , paramType = "AuthCode"
            , defaultValue = "")
	public MemberDto kakaoLogin(String code) throws Exception {
		KakaoOAuthDto kakaoOAuthDto = kakaoLoginService.getToken(code);  //Token을 받아옵니다.
		String accessToken = kakaoOAuthDto.getAccessToken();
		KakaoApiUserDto kakaoApiUserDto = kakaoLoginService.getKakaoUid(accessToken);  //카카오 계정정보를 받아옵니다.
		KakaoMemberDto kakaoMemberDto = kakaoLoginService.selectKakaoMember(kakaoApiUserDto.getId());  //DB에 기등록된 사용자인지 확인합니다.
		
		MemberDto memberDto = new MemberDto();
		if(kakaoMemberDto == null) { //DB에 등록되지 않았으면 신규등록합니다.
			System.out.println("신규등록진행");
			memberDto = kakaoLoginService.insertKakaoMember(kakaoOAuthDto, kakaoApiUserDto);
		} else {
			long userNo = kakaoMemberDto.getUserNo();
			memberDto = kakaoLoginService.selectMember(userNo); 
		}
		
		return memberDto;
	}
	
	

	@RequestMapping(value={"/login/kakaoAccessToken"}, method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="카카오 OAuth2.0 (Android:AccessToken)", notes="카카오 AccessToken을 전달하고, 서버의 계정정보값을 취득합니다."
			+ "<br/>ㅇㄹ")
    @ApiImplicitParam(
            name = "code"
            , value = "카카오에서 부여받은 accessToken"
            , required = true
            , dataType = "string"
            , paramType = "AuthCode"
            , defaultValue = "")
	public MemberDto kakaoLoginAccessToken(String accessToken) throws Exception {
		KakaoOAuthDto kakaoOAuthDto = new KakaoOAuthDto();
		kakaoOAuthDto.setAccessToken(accessToken);
		//String accessToken = "U_HWl8U66pZddVBZz4OWnC2NrMMGF_Yt7SFKSQorDR8AAAF-Jacxag";
		KakaoApiUserDto kakaoApiUserDto = kakaoLoginService.getKakaoUid(accessToken);  //카카오 계정정보를 받아옵니다.
		KakaoMemberDto kakaoMemberDto = kakaoLoginService.selectKakaoMember(kakaoApiUserDto.getId());  //DB에 기등록된 사용자인지 확인합니다.
		
		MemberDto memberDto = new MemberDto();
		if(kakaoMemberDto == null) { //DB에 등록되지 않았으면 신규등록합니다.
			System.out.println("신규등록진행");
			memberDto = kakaoLoginService.insertKakaoMember(kakaoOAuthDto, kakaoApiUserDto);
		} else {
			long userNo = kakaoMemberDto.getUserNo();
			memberDto = kakaoLoginService.selectMember(userNo); 
		}
		
		return memberDto;
	}
	
}
