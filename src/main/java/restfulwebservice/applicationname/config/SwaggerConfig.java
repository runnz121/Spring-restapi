package restfulwebservice.applicationname.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration//환경설정용이라는 정의
@EnableSwagger2//swagger로서 사용할거라는 것
public class SwaggerConfig { //swagger에 나타낼 정보를 추가함(보통 고정적인 정보를 변수로 선언한다)
    private static final Contact DEFAULT_CONTACT = new Contact("Kenneth Lee", "http://www.joneconsultin.gco.kr",
                                                                "edwon@joneonsulting.co.kr");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome API Title",
                                                        "My User management REST API Service","1.0","urn:tos",DEFAULT_CONTACT, "Apache 2.0","http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
                                                Arrays.asList("application/json","application/xml")); //aslist : 배열형태를 리스트로 바꿔줌
    @Bean
    public Docket api(){ //Docket은 documentation 형태로 바환
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO) //apiInfo : 제목 설명 문서에 대한 정보
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);



    }
}
