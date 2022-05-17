package kr.ac.inha.board.ipo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ac.inha.board.ipo.dto.IpoDto;
import kr.ac.inha.board.ipo.dto.SystemLogDto;

@Mapper
public interface IpoMapper {
	List<IpoDto> selectIpoList() throws Exception;
	IpoDto selectIpoDetail(int ipoIndex) throws Exception;
	void deleteIpo(int ipoIndex) throws Exception;
	void updateIpo(IpoDto ipo) throws Exception;
	void insertIpo(IpoDto ipo) throws Exception;
	List<SystemLogDto> selectSystemLog() throws Exception;
	//void insertSystemLog(String type,  String target,  String result) throws Exception;
	void insertSystemLog(@Param("type") String type,@Param("target")  String target,@Param("result")  String result) throws Exception;
}
