package restfulwebservice.applicationname.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;//인슽턴스 선언(의존성 주입) ->userdaoservice 에 service로 지정

    public UserController(UserDaoService service) { //생성자의 매개변수를 이용하여 의존성 주입할 인스턴스를 선언 //여기서는 위의 인스턴스
        this.service = service; //전달되어진 service 인스턴스 값을 매개변수에 할당.
    }

 //---------------------------------------의존성 주입 끝----------------------------------//


    @GetMapping("/users") //url 에 users호출시 이게 호출됨
   public List<User> retrieveAllUsers(){
        return service.findAll(); //전체 사용자 목록 반환
    }



    //지정사용자 정보 반환 //pathvariable 사용 서버는 String으로 자동변환되어 전달됨
    @GetMapping("/users/{id}")//GET /users/1 or /users/2 ..... -> 서버에는 String으로 전달됨
    public User retrieveUser(@PathVariable int id){//매개변수 받는 pathvariable
        User user = service.findOne(id);

        //예외처리
        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));//UserNotFoundException 클래스이름
        }


        return user; //전달받은 id값이 findone에 전달된다 그리고 그 전달받은 findeone에서 해당 id를 찾아서 다시 반환

    }




    @PostMapping("/users")//post요청이 들어왔을시 실행 //@valid로  valid관련된 지정된 bean을 적용시킴
    //responseentity로 user값을 반환
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){ //전달받은 object 를 받기 위해서는 requestbody선언 User class 값에 mapping
        User savedUser = service.save(user); //saveduser변수 선언, service의 save 함수에 user 객체 전달


        //반환값 URI
       URI location =  ServletUriComponentsBuilder.fromCurrentRequest()// ServletUriComponentsBuilder(사용자 요청값 변환을 위해 사용).fromCurrentRequest()(현재 요청값 사용)
                .path("/{id}")//반환시켜줄 값을 지정
                .buildAndExpand(savedUser.getId()) //가변변수 {id}에 getId로 해당 변수를 지정
                .toUri(); //위의 모든 값을 uri 형태로 반환

        return ResponseEntity.created(location).build();//저장된 사용자의 주소값 = location을 build()
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id ) {
        User user = service.deleteById(id);

        if (user == null) {

            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }


    }
}
