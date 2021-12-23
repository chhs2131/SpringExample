package kr.ac.inha.board.login.service;

import kr.ac.inha.board.login.dto.MembersDto;
import kr.ac.inha.board.login.dto.RegistDto;

public interface LoginService {
	String saveRegisterInfo(RegistDto mbr) throws Exception;
	String saveCertification(MembersDto mbr) throws Exception;
}
