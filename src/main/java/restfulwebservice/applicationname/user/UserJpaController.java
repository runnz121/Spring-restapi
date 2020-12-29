package restfulwebservice.applicationname.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")//모든 메소드가 갖는 prefix를 지정 //localhost:8080/jpa/......
public class UserJpaController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    //jpa를 이용한 전체 목록 반환환
   @GetMapping("/users")
    public List<User> retrieveAllUsers() {
       return userRepository.findAll(); //jpaRepository가 지원하는 매소드

    }

    @GetMapping("/users/{id}")
    //public User retrieveUser(@PathVariable int id){ //hateos 기능 사용 안할시
    public Resource<User>retrieveUser(@PathVariable int id){ //hateos로 반환시
       Optional<User> user = userRepository.findById(id); //여기서 findbyid매소드는 optional을 사용함 -> jpareposityr클래스의 부모 클래스에서 확인 가능
        //optional data로 받았다

        if(!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        //hateos 기능 사용
        Resource<User> resource = new Resource<>(user.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers()); //retrieveAllusers 매소드와 링크 시켜줌
        resource.add(linkTo.withRel("all-users"));

        //return user.get(); //반환값은 get으로
        return resource;
    }


    //삭제 매소드
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
       userRepository.deleteById(id);

    }

    //사용자 추가 메소드
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
       User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }



    //Post 전체데이터 호출시 /jpa/users/90001/posts
    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id){

        Optional<User> user = userRepository.findById(id); //여기서 findbyid매소드는 optional을 사용함 -> jpareposityr클래스의 부모 클래스에서 확인 가능
        //optional data로 받았다

        if(!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user.get().getPosts();

    }



        //jpa를 사용해서 post 저장
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post){

       //Post 테이블 안에 userid 컬럼 때문에 해당 userid를 찾는 코드를 추가하여 post로 반환해줘야함
       Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        post.setUser(user.get()); //user.get()-> user라는 객체 생성 post.setUser()로 post로 지정함
//-----------------------------------------------------
        Post savedPost = postRepository.save(post); //위에서 지정한 값을 변수로 받음

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build(); //해당 url로 데이터 반환
    }
}
