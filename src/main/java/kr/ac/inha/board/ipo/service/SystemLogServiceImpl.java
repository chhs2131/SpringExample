package kr.ac.inha.board.ipo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.inha.board.ipo.dto.SystemLogDto;
import kr.ac.inha.board.ipo.mapper.IpoMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SystemLogServiceImpl implements SystemLogService{
	@Autowired
	private IpoMapper ipoMapper;

	@Override
	public List<SystemLogDto> selectSystemLog() throws Exception {
		return ipoMapper.selectSystemLog();
	}
	
	@Override
	public void writeLog(String type, String target, String result) throws Exception {
		log.warn("");
		ipoMapper.insertSystemLog(type, target, result);
	}
	
}
