package org.example.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 뷰 템플릿을 호출하는 방법
@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
            .addObject("data", "hello! this is code.");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello! this is code.");
        return "response/hello";
    }

    // 권장하지 않는 방법
    // void를 반환하는 경우: View의 논리적 이름과 같다면 자동으로 해당 템플릿을 반환함
    // 단, HttpServletResponse, OutputStream 같은 메세지 바디를 처리하는 파라미터가 없는 경우에만 동작
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello! this is code.");
    }
}
