package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Slf4j
@RestController
public class MappingController {

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }


    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mapping-get-v1");
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
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     * http://localhost:8080/mapping/userA
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        // @PathVariable String userId 이렇게 생략 가능하여 써도 된다.
        log.info("mappingPath userId={}", data);
        // 2022-03-19 15:52:58.901  INFO 15476 --- [nio-8080-exec-6] h.s.b.requestmapping.MappingController   : mappingPath userId=userA
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     * http://localhost:8080/mapping/users/userA/orders/100
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        // 2022-03-19 15:56:23.875  INFO 21828 --- [nio-8080-exec-4] h.s.b.requestmapping.MappingController   : mappingPath userId=userA, orderId=100
        return "ok";
    }

    /**
     * 파라미터로도 추가 매핑(특정 파라미터가 있으면 얘가 호출된다)
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     * http://localhost:8080/mapping-param?mode=debug
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑(포스트맨의 Header에 mode를 선언하여, debug를 넣어주어서 테스트 가능)
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
     * Content-Type 요청 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE) // 요청의 Content-type을 consumes 컨트롤러 입장에서 소비하는 입장
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 요청 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html") // 컨트롤러가 생산해내는 타입
    public String mappingProduces() {
        // Accept : text/html => 클라이언트가 Content-Type이 text/html인 것을 받아들일 수 있어!에 대한 클라이언트의 요청 정보
        log.info("mappingProduces");
        return "ok";
    }
}
