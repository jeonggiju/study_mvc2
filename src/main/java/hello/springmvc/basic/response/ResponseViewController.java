package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello").addObject("data","test");
        return mav;
    }

//    @ResponseBody // response/hello 가 뷰로 넘어가지 않고 고대로 출력됨.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data","test");
        return "response/hello";
    }

    // 강사는 권장하지 않는 방법
    @RequestMapping("/response/hello") // <- 요게 view 이름으로 진행된다.
    public void responseViewV3(Model model) {
        model.addAttribute("data","test");
    }
}
