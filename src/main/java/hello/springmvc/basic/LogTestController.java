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

        System.out.println("name = " + name); // 어떤 레벨이든 다 찍힘

        /**
         * 가령 다음과 같이 설정되어있다고 가정하자.
         * logging.level.hello.springmvc=info
         * 그렇다면 trace는 출력이 안되는 것이 정상이다.
         * 1. logger.trace(" trace log= "+  name); <- x
         * 2. logger.trace(" trace log={}", name); <- o
         * 둘은 출력되지 않는 것이 정상이다. 허나 이 둘의 차이는 java의 언어적 특징과 관련이 있다.
         * 1번의 경우 "trace log=spring" 연산이 일어난다. 즉 쓸모없는 리소스가 사용되는 것이다.
         * 하지만 2번의 경우는 연산 자체도 일어나지 않는다. 단순히 메서드에 파라미터만 넘긴다.
         * 허나 호출되지 않기에 어떠한 연산도 일어나지 않는다.
         **/
        logger.trace(" trace log= "+  name); // 안됨

        logger.trace(" trace log={}", name);
        logger.debug(" debug log={}", name);
        logger.info(" info log={}", name);
        logger.warn(" warn log={}", name);
        logger.error(" error log={}", name);
        return "ok";
    }
}
