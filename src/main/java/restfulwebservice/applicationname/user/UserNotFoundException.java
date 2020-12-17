package restfulwebservice.applicationname.user;



//200번떄 -> 정상
//400번때 -> 클라이언트 오류
//500번때 -> 서버오류

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {//실행시 오류
    public UserNotFoundException(String message) {
        super(message); //부모클래스로 반환
    }
}
