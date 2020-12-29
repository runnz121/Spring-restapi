package restfulwebservice.applicationname.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor//생성자 생성
@NoArgsConstructor
//@JsonIgnoreProperties(value ={"password"}) //전달 제어할 값을 적어줌 //class에서 적어줄떈 jsonproperties로 작성
@JsonFilter("UserInfoV2")//필터 이름을 명명
public class UserV2 extends User { //User를 상속받는 상속관계, 부모클래스에 디폴트 생성자 있어야한다

    private String grade;



}
