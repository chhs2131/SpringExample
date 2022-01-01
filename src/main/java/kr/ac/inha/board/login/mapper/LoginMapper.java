package kr.ac.inha.board.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.inha.board.login.dto.KakaoApiUserDto;
import kr.ac.inha.board.login.dto.KakaoMemberDto;
import kr.ac.inha.board.login.dto.MemberDto;
import kr.ac.inha.board.login.dto.MembersDto;

@Mapper
public interface LoginMapper {
	List<MembersDto> selectMembers(String userId) throws Exception;	
	void insertMembers(MembersDto membersDto) throws Exception;
	void updateMembersCert(MembersDto membersDto) throws Exception;
	
	int insertMember() throws Exception;
	KakaoMemberDto selectKakaoMember(String kakaoUid) throws Exception;
	void insertKakaoMember(KakaoMemberDto kakaoMemberDto) throws Exception;
}
