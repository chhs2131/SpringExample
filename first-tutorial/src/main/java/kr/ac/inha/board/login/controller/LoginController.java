package kr.ac.inha.board.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.inha.board.login.dto.MembersDto;
import kr.ac.inha.board.login.dto.RegistDto;
import kr.ac.inha.board.login.dto.ResultDto;
import kr.ac.inha.board.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login/login.do")
	public ModelAndView login() throws Exception {
		ModelAndView mv = new ModelAndView("login/login");

		return mv;
	}

	@RequestMapping("/login/register.do")
	public ModelAndView register() throws Exception {
		ModelAndView mv = new ModelAndView("login/register");
		
		return mv;
	}
	

	// ajax 데이터 받는 형식 (POST, json파일, utf8 코덱)
	@RequestMapping(value="/login/register.ajax", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String saveRegisterInfo(@RequestBody RegistDto mbr) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		
		ResultDto result = new ResultDto();
		
		log.info(mbr.toString());
		
		String rtn = loginService.saveRegisterInfo(mbr);
		if (rtn.equals("Ok")) {
			result.setStatus("Ok");
			result.setErrMsg("");
		}
		else {
			result.setStatus("Error");
			result.setErrMsg(rtn);
		}
				
		String jsonString = mapper.writeValueAsString(result);
		
		log.info("result = " + result);
		
		return jsonString;
	}
	

	@RequestMapping(value="/login/certification.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView certification(MembersDto mbr) throws Exception{
		ModelAndView mv = null;
		
		log.info(mbr.toString());

		String rtn = loginService.saveCertification(mbr);
		if (rtn.equals("Ok")) {
			mv = new ModelAndView("login/welcome");
		}
		else {
			mv = new ModelAndView("login/denied");
			mv.addObject("errMsg", rtn);
		}
		
		log.info("result = " + rtn);

		return mv;
	}
}
