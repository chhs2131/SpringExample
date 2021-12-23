package kr.ac.inha.board.login.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.ac.inha.board.login.dto.MembersDto;
import kr.ac.inha.board.login.dto.RegistDto;
import kr.ac.inha.board.login.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public String saveRegisterInfo(RegistDto mbr) throws Exception {
	    String emailRegExp =
	            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
	            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	    Pattern pattern;
	    
	    pattern = Pattern.compile(emailRegExp);
	 
		if (mbr.getFirstName() == null || mbr.getFirstName().trim().isEmpty()) {
			return "이름(First Name)은 필수 정보 입니다.";
		}
		if (mbr.getLastName() == null || mbr.getLastName().trim().isEmpty()) {
			return "성(Last Name)은 필수 정보 입니다.";
		}
		if (mbr.getInputEmail() == null || mbr.getInputEmail().trim().isEmpty()) {
			return "이메일(Email address)은 필수 정보 입니다.";
		}
		else {
			Matcher matcher = pattern.matcher(mbr.getInputEmail());
			if (!matcher.matches()) {
				return "이메일 형식이 올바르지 않습니다.";
			}
		}
		if (mbr.getInputPassword() == null || mbr.getInputPassword().trim().isEmpty()) {
			return "비밀번호(Password)은 필수 정보 입니다.";
		}
		if (mbr.getInputPassword().trim().length() < 8) {
			return "비밀번호(Password)는 8자 이상 입력하세요.";
		}
		if (mbr.getConfirmPassword() == null || mbr.getConfirmPassword().trim().isEmpty()) {
			return "비밀번호확인(Confirm password)은 필수 정보 입니다.";
		}
		if (!mbr.getInputPassword().equals(mbr.getConfirmPassword())) {
			return "비밀번호가 일치하지 않습니다.";
		}
		
		// 등록상태 확인
		List<MembersDto> membersList = loginMapper.selectMembers(mbr.getInputEmail());
		if (!membersList.isEmpty()) {
			MembersDto members = membersList.get(0);
			if (members.getCertificationDate() == null || members.getCertificationDate().equals("")) {
				return "이미 신청중인 상태이나 이메일 인증이 완료되지 않았습니다.\n발송된 이메일을 열어서 인증 처리하시기 바랍니다.";
			}
			else if (!members.getEnabled().equals("Y")) {
				return "계정이 비활성(사용불가) 상태입니다.";
			}
			else {
				return "이미 신청이 완료되었습니다.\n로그인 하셔서 사용하시기 바랍니다.";
			}
		}

		// 계정 등록
		MembersDto members = new MembersDto();
		members.setUserId(mbr.getInputEmail());
		members.setFirstName(mbr.getFirstName());
		members.setLastName(mbr.getLastName());
		members.setUserPass(mbr.getInputPassword());
		
		loginMapper.insertMembers(members);
		
		membersList = loginMapper.selectMembers(mbr.getInputEmail());
		members = membersList.get(0);

		// 이메일 발송
		try {
			String htmlStr = "<h2>"+members.getLastName() + members.getFirstName()+"님 안녕하세요.</h2>" 
					+ "<h3><p>사이트 로그인을 하시려면, 인증하기 버튼을 눌러서 인증 처리를 하시기 바랍니다. -&gt; " 
					+ "<a href='http://localhost:8080/login/certification.do?userNo="+members.getUserNo()+"&userId="+ members.getUserId() +"&userKey=18f198e0-92a9-447c-9adf-b93b4b578644'>[인증하기]</a></p>"
					+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)</h3>";
			
			  MailHandler mail = new MailHandler(emailSender);
			  mail.setFrom("woohaeng@gmail.com", "인하대학교");
			  mail.setTo(mbr.getInputEmail());
			  mail.setSubject("[본인인증] 회원가입 인증요청");
			  mail.setText(htmlStr);
			  mail.send();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
		
		return "Ok";
	}
	

	@Override
	public String saveCertification(MembersDto mbr) throws Exception {
		// 등록상태 확인
		List<MembersDto> membersList = loginMapper.selectMembers(mbr.getUserId());
		if (membersList.isEmpty()) {
			return "신청한 내역이 존재하지 않습니다.";
		}

		MembersDto members = membersList.get(0);
		if (!members.getUserNo().equals(mbr.getUserNo())) {
			return "신청한 계정 정보와 인증 정보가 불일치합니다.";
		}
		else if (members.getCertificationDate() != null && !members.getCertificationDate().equals("")) {
			if (!members.getEnabled().equals("Y")) {
				return "계정이 비활성(사용불가) 상태입니다.";
			}
			return "Ok";
		}
		else if (members.getEnabled().equals("Y") && members.getCertificationDate() != null) {
			return "Ok";
		}
		
		// 계정 인증
		loginMapper.updateMembersCert(members);
		
		return "Ok";
	}
	

}
