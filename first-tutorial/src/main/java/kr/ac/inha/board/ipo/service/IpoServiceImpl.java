package kr.ac.inha.board.ipo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.inha.board.ipo.dto.IpoDto;
import kr.ac.inha.board.ipo.mapper.IpoMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IpoServiceImpl implements IpoService {
	@Autowired
	private IpoMapper ipoMapper;
	
	@Override
	public List<IpoDto> selectIpoList() throws Exception {
		return ipoMapper.selectIpoList();
	}
	
	@Override
	public IpoDto selectIpoDetail(int ipoIndex) throws Exception {
		log.warn("ipoDetail IpoIndex number => "+Integer.toString(ipoIndex));
		IpoDto result = ipoMapper.selectIpoDetail(ipoIndex);
		log.warn("Query Return Value <= " + result);
		return result;
	}
	
	@Override
	public void deleteIpo(int ipoIndex) throws Exception {
		log.warn("start delete service");
		ipoMapper.deleteIpo(ipoIndex);
	}
	
	@Override
	public void updateIpo(IpoDto ipo) throws Exception {
		log.warn("start put service <= " + ipo);
		ipoMapper.updateIpo(ipo);
	}
	
	@Override
	public void insertIpo(IpoDto ipo) throws Exception {
		ipoMapper.insertIpo(ipo);
	}
	
}
