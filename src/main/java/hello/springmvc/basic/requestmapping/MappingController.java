package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본 요청
     * 둘다 허용 /hello-basic, /hello-basic/ <- slash
     * HTTP 메서드 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE
     *
     * 배열로 인한 다중 설정이 가능 {"/hello-basic", "/hello-go"}
     * @RequestMapping({"/mapping-get-v1", "/hello-go"})
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value="/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     *
     * - mapping?name=asd: 쿼리 파라미터 형식
     * - mappping/asd: path variable 형식
     */
    @GetMapping("/mapping/test/{userId}")
    public String mappingPath(
//            @PathVariable("userId") String data <- path variable의 이름과 파라미터가 같으면 생략 가능
            @PathVariable String userId
     ){
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/test/users/{userId}/orders/{orderId}")
    public String mappingPath(
            @PathVariable String userId,
            @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     * e.g.
     * 매핑되는 요청:
     * GET /mapping-param?mode=debug
     *
     * 매핑되지 않는 요청:
     * GET /mapping-param (파라미터 없음)
     * GET /mapping-param?mode=prod (값이 debug가 아님)
     * GET /mapping-param?otherParam=123 (필요한 파라미터가 없음)
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * Content-Type: 헤더는 HTTP 요청과 응답에서 전송되는 데이터의 MIME 타입을 나타내는데 사용
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * Accept 헤더: 클라이언트가 서버에게 "나는 이 데이터 형식을 받을 준비가 되어 있다."고 알려주는 역할
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     * produces = MediaType.TEXT_PLAIN_VALUE
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
