package restfulwebservice.applicationname.user;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor//생성자 생성
public class User {

    private Integer id;

    @Size(min=2, message = "2글자 이상 입력해주세요") //지정값보다 무조건 커야되는 검증용 빈
    private String name;

    @Past //과거날짜는 안된다는 검증
    private Date joinDate;

}
