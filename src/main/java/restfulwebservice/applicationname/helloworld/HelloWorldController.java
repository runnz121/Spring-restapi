package restfulwebservice.applicationname.helloworld;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@RestController
public class HelloWorldController {

    @Autowired //어노테이션을 통한 의존성 주입
    private MessageSource messageSource; //다국어처리를 위한 의존성 주입



    //GET method
    //hello-world ->Endpoint : 호출 되는 페이지
    //@RequestMapping ->restAPI 쓰기전에 사용 = (method=RequestMethod.get, path="/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello world"; //hello-world 를 단순 반환(문자형 반환)

    }

    //클래스에 값을 받아 반환, 자바 bena형태 https://elfinlas.github.io/2017/10/31/javabean/
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() { //HelloWorldBean으로 값을 반환
        return new HelloWorldBean("Hello world"); //HelloWorldBean 클래스를 통해서 반환(자바 bean 형태)-> json 형태로 반환 받음

    }

    //pathvariable 사용
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")    //@Pathvaraible이라고 지정한것과 같은 이름 써줌
    public HelloWorldBean helloWorldBean(@PathVariable String name) { //name을 위의 가변 데이터 값으로 넣어줌
        return new HelloWorldBean(String.format("Hello World, %s", name)); //url 뒤에 해당 name을 입력하면 반환시 그 name이 출력됨 , string format은 단지 형식 문자열 출력을 위함
    }

    //다국어처리
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(
            @RequestHeader(name ="Accept-Language", required = false) Locale locale) { //locale 값을 requestheadr로 전달

        return messageSource.getMessage("greeting.message",null,locale);//resource folder의 다국어 처리를 위한 번들에서
                                                                                  //해당 내용의 메세지를 가져옴
    }


}
