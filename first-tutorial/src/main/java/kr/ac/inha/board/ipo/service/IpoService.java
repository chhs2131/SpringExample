package kr.ac.inha.board.ipo.service;

import java.util.List;

import kr.ac.inha.board.ipo.dto.IpoDto;

public interface IpoService {
	List<IpoDto> selectIpoList() throws Exception;
	IpoDto selectIpoDetail(int ipoIndex) throws Exception;
	void deleteIpo(int ipoIndex) throws Exception;
	void updateIpo(IpoDto ipo) throws Exception;
	void insertIpo(IpoDto ipo) throws Exception;
}
