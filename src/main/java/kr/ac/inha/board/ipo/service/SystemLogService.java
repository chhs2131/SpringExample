package kr.ac.inha.board.ipo.service;

import java.util.List;

import kr.ac.inha.board.ipo.dto.SystemLogDto;

public interface SystemLogService {
	List<SystemLogDto> selectSystemLog() throws Exception;
	void writeLog(String type, String target, String result) throws Exception;
}
