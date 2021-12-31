package kr.ac.inha.board.login.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.inha.board.login.dto.KakaoApiUserDto;
import kr.ac.inha.board.login.dto.KakaoOAuthDto;


@Service
public class KakaoLoginServiceImpl implements KakaoLoginService {
	String kakaoRestKey = "a130d4bc5b0df2dd600ac87ffdda755a";
	String redirectUri = "http://1.243.131.200:8080/login/kakao";
	
	public String getFirstToken(String authCode) throws Exception {
		// 변수초기화
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuffer result = new StringBuffer();
        KakaoOAuthDto kakaoOAuthDto = new KakaoOAuthDto();
        String accessToken = "no_data";
        
        try {
            String apiUrl = "https://kauth.kakao.com/oauth/token?" + 
                    "grant_type=authorization_code&" + 
                    "client_id=" + kakaoRestKey + 
                    "&redirect_uri=" + redirectUri + 
                    "&code=" + authCode;
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            kakaoOAuthDto = objectMapper.readValue(result.toString(), KakaoOAuthDto.class);
            accessToken = kakaoOAuthDto.getAccessToken();
            //JSONObject jsonObject = (JSONObject) parser.parse(result.toString());
            //accessToken = (String) jsonObject.get("access_token");
        } catch (Exception e) {
            e.printStackTrace();
        }

		//return "AuthCode : " + authCode + " <br/><br/> JsonData : " + result + " <br/> JsonToDto : " + kakaoOAuthDto + " <br/><br/> accessToken is : " + accessToken;
		return accessToken;
	}
	
	public String getKakaoUid(String accessToken) throws Exception {     
		// 변수 초기화
		accessToken = "Bearer " + accessToken;
        StringBuffer result = new StringBuffer();
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoApiUserDto kakaoApiUserDto = new KakaoApiUserDto();
        String kakaoId = "no_data";
        String kakaoNickname = "no_data";
        
        try {
            String apiUrl = "https://kapi.kakao.com/v2/user/me?" +
                    "property_keys=%5B%22properties.nickname%22%5D";
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", accessToken);
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            kakaoApiUserDto = objectMapper.readValue(result.toString(), KakaoApiUserDto.class);
            kakaoId = kakaoApiUserDto.getId();
            kakaoNickname = kakaoApiUserDto.getNickname();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		return "JSON_DATA : " + kakaoApiUserDto + " <br/> USER_ID : " + kakaoId + "<br/> NICKNAME : " + kakaoNickname;
	}
}




