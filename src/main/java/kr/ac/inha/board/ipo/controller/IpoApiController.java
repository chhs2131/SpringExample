package kr.ac.inha.board.ipo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.inha.board.ipo.dto.IpoDto;
import kr.ac.inha.board.ipo.dto.PswDto;
import kr.ac.inha.board.ipo.service.IpoService;
import kr.ac.inha.board.ipo.service.SystemLogService;

@RestController
public class IpoApiController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IpoService ipoService;
	
	@Autowired
	private SystemLogService systemLogService;

	@RequestMapping(value="/api/ipo/ipoList", method=RequestMethod.GET)
	@ResponseBody
	public List<IpoDto> ipoListAjax() throws Exception {
		List<IpoDto> list = ipoService.selectIpoList();
		
		systemLogService.writeLog("GET", "/api/ipo/ipoList", "Return IPO List");
		return list;
	}
	
	@RequestMapping(value="/api/ipo/{ipoIndex}", method=RequestMethod.GET)
	public IpoDto openApiIpoDetail(@PathVariable("ipoIndex") int ipoIndex) throws Exception {
		
		systemLogService.writeLog("GET", "/api/ipo", Integer.toString(ipoIndex));
		return ipoService.selectIpoDetail(ipoIndex);
	}
	
	@RequestMapping(value="/api/ipo/{ipoIndex}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteIpo(IpoDto ipo) throws Exception {
		ipoService.deleteIpo(ipo.getIpoIndex());
		
		systemLogService.writeLog("DELETE", "/api/ipo", ipo.toString());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value="/api/ipo", method=RequestMethod.POST)
	public ResponseEntity<Void> insertIpo(IpoDto ipo) throws Exception {
		ipoService.insertIpo(ipo);
		
		systemLogService.writeLog("POST", "/api/ipo", ipo.toString());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@RequestMapping(value="/api/ipo/{ipoIndex}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateIpo(IpoDto ipo) throws Exception {
		ipoService.updateIpo(ipo);
		
		systemLogService.writeLog("PUT", "/api/ipo", ipo.toString());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//
	// 이하 CURD 테스트용 
	//
	//////////////////////////////////////////////////////////////////////////////////////////////
	@CrossOrigin
	@RequestMapping(value="/api/psw", method=RequestMethod.GET)
	public PswDto testPswGet() throws Exception {
		PswDto selfMadeOrange = new PswDto();
		selfMadeOrange.setReturnValue("GET");
		return selfMadeOrange;
	}
	@CrossOrigin
	@RequestMapping(value="/api/psw", method=RequestMethod.POST)
	public PswDto testPswPost() throws Exception {
		PswDto selfMadeOrange = new PswDto();
		selfMadeOrange.setReturnValue("POST");
		return selfMadeOrange;
	}
	@CrossOrigin
	@RequestMapping(value="/api/psw", method=RequestMethod.PUT)
	public PswDto testPswPut() throws Exception {
		PswDto selfMadeOrange = new PswDto();
		selfMadeOrange.setReturnValue("PUT");
		return selfMadeOrange;
	}
	@CrossOrigin
	@RequestMapping(value="/api/psw", method=RequestMethod.DELETE)
	public PswDto testPswDelete() throws Exception {
		PswDto selfMadeOrange = new PswDto();
		selfMadeOrange.setReturnValue("DELETE");
		return selfMadeOrange;
	}

	
	@RequestMapping(value="/api/test", method=RequestMethod.GET)
	public String testGet() throws Exception {
		return "RETURN GET";
	}
	@RequestMapping(value="/api/test", method=RequestMethod.POST)
	public String testPost() throws Exception {
		return "RETURN POST";
	}
	@RequestMapping(value="/api/test", method=RequestMethod.PUT)
	public String testPut() throws Exception {
		return "RETURN PUT";
	}
	@RequestMapping(value="/api/test", method=RequestMethod.DELETE)
	public String testDelete() throws Exception {
		return "RETURN DELETE";
	}

	@RequestMapping(value={"/api/log"}, method=RequestMethod.GET)
	public void insertLog(String type, String target, String result) throws Exception{
		//예시 http://127.0.0.1:8080/api/log?type=hello&target=hi&result=bye
		System.out.println(type + target + result);
		log.warn("systemLog test data : " + type + " / " + target + " / " + result);
		systemLogService.writeLog(type, target, result);
	}
	
}	
