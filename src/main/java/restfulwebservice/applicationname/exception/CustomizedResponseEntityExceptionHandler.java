package restfulwebservice.applicationname.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import restfulwebservice.applicationname.user.UserNotFoundException;

import java.util.Date;


@RestController//웹서비스에서 사용 하기 위함
@ControllerAdvice//모든 컨트롤러가 실행하기전에 이 클래스가 가장 먼저 실행된다
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler { //ResponseEntityExceptionHandler의 스프링 부트의 클래스를 상속


    @ExceptionHandler(Exception.class)//예외처리시 사용된다는 매소드 선언 //에러객체, 웹 에러 발생 원인
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = //exceptionResponse는 우리가 생성한 클래스
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);//500번 에러
                    //object 객체로 반환하기 때문에 generic 필요 없음
        }



                    //UserNotFoundException 클래스에 대한 예외가 발생시시 작동
    @ExceptionHandler(UserNotFoundException.class)//user가 없을 경우 발생
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);//404에러

    }



    //기본 매소드를 재정의 함(ResponseEntityExceptionHandler)
    @Override//부모클래스를 상속받아서 씀
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                "validationfailed", ex.getBindingResult().toString());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }






}
