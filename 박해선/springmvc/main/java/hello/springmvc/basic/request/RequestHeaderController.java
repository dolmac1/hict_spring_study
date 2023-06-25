package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod,
            Locale locale,
            @RequestHeader MultiValueMap<String, String> headerMap,
            @RequestHeader("host") String host,
            @CookieValue(value = "myCookie", required = false) String cookie
            ) {

        log.info("request = {}"    , request   );
        log.info("response = {}"   , response  );
        log.info("httpMethod = {}" , httpMethod);
        log.info("locale= {} "     , locale    );
        log.info("headerMap= {} "  , headerMap );
        log.info("header host= {} ", host      );
        log.info("myCookie= {} "   , cookie    );

        /**
         * request = org.apache.catalina.connector.RequestFacade@33469169
         * response = org.apache.catalina.connector.ResponseFacade@15789439
         * httpMethod = GET
         * locale= ko_KR
         * headerMap= {user-agent=[PostmanRuntime/7.29.0], accept= ~ (이후 생략 Content-Type 등등 다 있음)
         * header host = localhost:8080
         * myCookie=null
         */

        /**
         * MultiValueMap
         * MAP과 유사한데, 하나의 키에 여러 값을 받을 수 있다.
         * HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용한다.
         * keyA=value1&keyA=value2
         */

        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("keyA", "value1");
        map.add("keyA", "value2");

        //[value1,value2]
        List<String> values = map.get("keyA");

        /**
         * 1. 참고
         * @Conroller 의 사용 가능한 파라미터 목록은 다음 공식 메뉴얼에서 확인할 수 있다.
         * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-annarguments
         * ctrl + f : Method Arguments
         *
         * 2. 참고
         * @Conroller 의 사용 가능한 응답 값 목록은 다음 공식 메뉴얼에서 확인할 수 있다.
         * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-annreturn-types
         */

        return "ok";
    }
}
