package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController : 문자열 반환 -> 그 문자열 String 그대로 반환.
@Slf4j
@RestController
public class LogTestController {

    // @Slf4j 추가하면 아래 코드 선언문 작성 안해도 됨
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);
        log.trace(" info log = {}", name);
        log.debug(" info log = {}", name);
        log.info(" info log = {}", name);
        log.warn(" info log = {}", name);
        log.error(" info log = {}", name);

        /**
         * appligcation.properties
         * #hello.springmvc 패키지와 그 하위 로그 레벨 설정
         * logging.level.hello.springmvc=trace
         *
         * name = Spring
         * 2022-03-19 15:21:11.295 TRACE 16180 --- [nio-8080-exec-4] hello.springmvc.basic.LogTestController  :  info log = Spring
         * 2022-03-19 15:21:11.297 DEBUG 16180 --- [nio-8080-exec-4] hello.springmvc.basic.LogTestController  :  info log = Spring
         * 2022-03-19 15:21:11.297  INFO 16180 --- [nio-8080-exec-4] hello.springmvc.basic.LogTestController  :  info log = Spring
         * 2022-03-19 15:21:11.297  WARN 16180 --- [nio-8080-exec-4] hello.springmvc.basic.LogTestController  :  info log = Spring
         * 2022-03-19 15:21:11.297 ERROR 16180 --- [nio-8080-exec-4] hello.springmvc.basic.LogTestController  :  info log = Spring
         */

        return "ok";
    }
}
