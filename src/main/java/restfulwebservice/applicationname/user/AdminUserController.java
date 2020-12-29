package restfulwebservice.applicationname.user;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController//json형태로 객체 반환
@RequestMapping("/admin") //공통 path 설정 (/admin/user 이런식으로...)
public class AdminUserController {

    private UserDaoService service;

    public AdminUserController(UserDaoService service) { //생성자의 매개변수를 이용하여 의존성 주입할 인스턴스를 선언 //여기서는 위의 인스턴스
        this.service = service;
    }
//전체사용자 목록 보기
    @GetMapping("/users")
   public MappingJacksonValue retrieveAllUsers(){
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter //2번
                .filterOutAllExcept("id","name","joinDate","password","ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter); //3번

        MappingJacksonValue mapping = new MappingJacksonValue(users); //4번
        mapping.setFilters(filters); //5번

        return mapping;
    }








//개별사용자 목록보기 /admin/vq/users/1
    //@GetMapping("/v1/users/{id}") //filter를 통해서 전달할 값을 제어
    //@GetMapping(value = "/users/{id}/", params = "version=1") //2가지 이상 정보 올때는 value ,지정해줘야됨 -> post맨 호출시 : http://localhost:8088/admin/users/1/?version1
    //@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1") //버전값을 헤더로 전달 -> POST 맨 호출시 : http://localhost:8088/admin/users/1 하고 headers 에 key : x-api-version  value : 1
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json") //마임타입을 이용 ->post맨 호출시 : http://localhost:8088/admin/users/1 하고 headsrs에 key : Accept   value :application/vnd.company.appv1+json

    public MappingJacksonValue retrieveUser(@PathVariable int id){ // 1번

        User user = service.findOne(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter //2번
                                            .filterOutAllExcept("id","name","joinDate","password","ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter); //3번

        MappingJacksonValue mapping = new MappingJacksonValue(user); //4번
        mapping.setFilters(filters); //5번

        return mapping;

    }
    // 1.매소드부터 mappingJacksonvalue로 값을 반환
    // 2.SimpleBeanPropertyFilter.filterOutAllExcept// JSon 라이브러리 기능 : 지정된 값을 json형태로 반환
    // 3.User 클래스에서 지정한 UserInfo를 필터링 값으로 지정(User 클래스의 @JSonfilter)
    // 4.해당 필터를 전역 등록
    // 5.필터링 맵핑
    //https://kwonnam.pe.kr/wiki/java/jackson/jsonfilter




    //버전 v2
    //@GetMapping("/v2/users/{id}") //filter를 통해서 전달할 값을 제어
    //@GetMapping(value = "/users/{id}/", params = "version=2")//버전값을 requestparam으로 전달
    //@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2") //버전값을 헤더로 전달
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")//마임타입을 이용
    public MappingJacksonValue retrieveUserV2(@PathVariable int id){ // 1번
        User user = service.findOne(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        //User -> User2 내용을 User2로 모두 복사하는 법
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); //beanutils : bean관련 여기서는 두 bean간의 공통 속성을 copy한다
        userV2.setGrade("VIP"); //userV2의 grade속성 추가

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter //2번
                .filterOutAllExcept("grade","id","name","joinDate","password","ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter); //3번

        MappingJacksonValue mapping = new MappingJacksonValue(userV2); //4번
        mapping.setFilters(filters); //5번

        return mapping;

    }

}
