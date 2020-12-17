package restfulwebservice.applicationname.helloworld;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
    //GET method
    //hello-world ->Endpoint
    //@RequestMapping ->restAPI 쓰기전에 사용 = (method=RequestMethod.get, path="/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello world"; //hello-world 를 단순 반환(문자형 반환)

    }

    //클래스에 값을 받아 반환
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() { //HelloWorldBean으로 값을 반환
        return new HelloWorldBean("Hello world"); //HelloWorldBean 클래스를 통해서 반환(자바 bean 형태)-> json 형태로 반환 받음

    }

    //pathvariable 사용
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) { //name을 위의 가변 데이터 값으로 넣어줌
        return new HelloWorldBean(String.format("Hello World, %s", name)); //url 뒤에 해당 name을 입력하면 반환시 그 name이 출력됨
    }


}
