package restfulwebservice.applicationname.user;



//200번떄 -> 정상
//400번때 -> 클라이언트 오류
//500번때 -> 서버오류

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//http status 코드처리
//2xx - > 정상
//4xx -> client 오류
//5xx -> server 오류
@ResponseStatus(HttpStatus.NOT_FOUND)//5xx일때 resource가 없다는 에러코드를 처리
public class UserNotFoundException extends RuntimeException {//실행시 발생하는 오류
    public UserNotFoundException(String message) {
        super(message); //부모클래스로 전달받은 메세지를 반환
    }
}
