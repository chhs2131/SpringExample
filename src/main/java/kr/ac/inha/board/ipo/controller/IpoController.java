package kr.ac.inha.board.ipo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.inha.board.ipo.dto.IpoDto;
import kr.ac.inha.board.ipo.dto.SystemLogDto;
import kr.ac.inha.board.ipo.service.IpoService;
import kr.ac.inha.board.ipo.service.SystemLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IpoController {
	@Autowired
	private IpoService ipoService;
	
	@Autowired
	private SystemLogService systemLogService;

	@RequestMapping(value = {"/ipo/ipoDashboard.do","/" })
	public ModelAndView openIpoDashboard() throws Exception {
		ModelAndView mv = new ModelAndView("/ipo/ipoDashboard");

		return mv;
	}

	@RequestMapping("/ipo/ipoInform.do")
	public ModelAndView openIpoInform() throws Exception {
		ModelAndView mv = new ModelAndView("/ipo/ipoInform");

		return mv;
	}

	@RequestMapping("/ipo/ipoAdmin.do")
	public ModelAndView openIpoAdmin() throws Exception {
		ModelAndView mv = new ModelAndView("/ipo/ipoAdmin");

		return mv;
	}
	@RequestMapping("/ipo/ipoSystemLog.ajax")
	@ResponseBody
	public List<SystemLogDto> ipoSystemLogAjax() throws Exception {
		List<SystemLogDto> list = systemLogService.selectSystemLog();
		return list;
	}
	
	@RequestMapping("/ipo/ipoList.do")
	public ModelAndView openIpoList() throws Exception {
		ModelAndView mv = new ModelAndView("/ipo/ipoList");
		
		return mv;
	}
	@RequestMapping("/ipo/ipoList.ajax")
	@ResponseBody
	public List<IpoDto> ipoListAjax() throws Exception {
		List<IpoDto> list = ipoService.selectIpoList();
		return list;
	}

	@RequestMapping("/ipo/ipoEditer.do")
	public ModelAndView openIpoEditer(@RequestParam int ipoIndex) throws Exception {
		ModelAndView mv = new ModelAndView("/ipo/ipoEditer");
		log.warn("Start ipo Editer Controller");
		IpoDto ipo = ipoService.selectIpoDetail(ipoIndex);
		
		mv.addObject("ipo", ipo);
		return mv;
	}

	@RequestMapping("/ipo/ipoWrite.do")
	public ModelAndView openIpoWrite() throws Exception {
		ModelAndView mv = new ModelAndView("/ipo/ipoWrite");
		return mv;
	}

	@RequestMapping(value="/ipo", method=RequestMethod.POST)
	public String insertIpo(IpoDto ipo) throws Exception {
		log.warn("start POST controller => " + ipo);
		ipoService.insertIpo(ipo);
		
		systemLogService.writeLog("POST", "/ipo", ipo.toString());
		return "redirect:/ipo/ipoList.do";
	}	
	
	@RequestMapping(value="/ipo/{ipoIndex}", method=RequestMethod.PUT)
	public String updateIpo(IpoDto ipo) throws Exception {
		log.warn("start put controller => " + ipo);
		ipoService.updateIpo(ipo);
		
		systemLogService.writeLog("PUT", "/ipo", ipo.toString());
		return "redirect:/ipo/"+Integer.toString(ipo.getIpoIndex());
	}	
	
	@RequestMapping(value="/ipo/{ipoIndex}", method=RequestMethod.DELETE)
	public String deleteIpo(IpoDto ipo) throws Exception {
		ipoService.deleteIpo(ipo.getIpoIndex());

		systemLogService.writeLog("DELETE", "/ipo", ipo.toString());
		return "redirect:/ipo/ipoList.do";
	}
	
	@RequestMapping(value="/ipo/{ipoIndex}", method=RequestMethod.GET)
	public ModelAndView openIpoDetail(@PathVariable("ipoIndex") int ipoIndex) throws Exception {
		ModelAndView mv = new ModelAndView("/ipo/ipoDetail");
		
		IpoDto ipo = ipoService.selectIpoDetail(ipoIndex);
		mv.addObject("ipo", ipo);

		log.warn("IpoDetail Result <= " + mv);
		
		systemLogService.writeLog("GET", "/ipo", Integer.toString(ipoIndex));
		return mv;
	}

}
