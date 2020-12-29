package restfulwebservice.applicationname.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//예외처리 ->AOP기능 사용
@Data//게터세터
@AllArgsConstructor//일반 생성자 대신
@NoArgsConstructor//디폴트 생성자 대신
public class ExceptionResponse {

    private Date timestamp;//예외 발생 시간
    private String message;//에외 발생 메세지
    private String details;//예외 상세정보
}
