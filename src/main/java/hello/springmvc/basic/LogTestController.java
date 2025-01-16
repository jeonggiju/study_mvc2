package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Controller 는 기본적으로 view 이름이 반환된다. e.g. "new-form"
 * @RestController 는 문자가 반환된다.
 */
@RestController
public class LogTestController {

    // 자신의 class 지정
    // private final Logger logger = LoggerFactory.getLogger(LogTestController.class);
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);
        logger.info(" info log={}", name);

        return "ok";
    }

}
