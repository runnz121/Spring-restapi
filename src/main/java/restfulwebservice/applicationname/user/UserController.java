package restfulwebservice.applicationname.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service) { //생성자 이용하여 의존성 주입
        this.service = service;
    }

    @GetMapping("/users") //url 에 users호출시 이게 호출됨
   public List<User> retrieveAllUsers(){
        return service.findAll(); //전체 사용자 목록 반환
    }

    //지정사용자 정보 반환 //pathvariable 사용 서버는 String으로 자동변환되어 전달됨
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        //예외처리
        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }


        return user; //전달받은 id값이 findone에 전달된다 그리고 그 전달받은 findeone에서 해당 id를 찾아서 다시 반환

    }

    @PostMapping("/users")//post요청이 들어왔을시 실행 //@valid로  valid관련된 지정된 bean을 적용시킴
    public ResponseEntity createUser(@Valid @RequestBody User user){ //전달받은 object 를 받기 위해서는 requestbody선언 User class 값에 mapping
        User savedUser = service.save(user); //user 라는 파라미터를 service의 save 함수에 전달


        //
       URI location =  ServletUriComponentsBuilder.fromCurrentRequest()//현재 request값을 이용
                .path("/{id}")//반환시켜줄 곳을 지정
                .buildAndExpand(savedUser.getId()) //{id}값을 맵핑
                .toUri(); //위의 값을 uri 형태로 반환

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id ) {
        User user = service.deleteById(id);

        if (user == null) {

            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }


    }
}
