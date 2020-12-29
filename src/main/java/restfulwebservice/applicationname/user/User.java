package restfulwebservice.applicationname.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor//생성자 생성
@NoArgsConstructor//디폴트 생성자 대신생성
//@JsonIgnoreProperties(value ={"password"}) //전달 제어할 값을 적어줌 //class에서 적어줄떈 jsonproperties로 작성
//@JsonFilter("UserInfo")//필터 이름을 명명 ->hateos 필터링 때문에 주석처리
//@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@ApiModel(description = "All details about the user")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;


    @Size(min=2, message = "2글자 이상 입력해주세요") //지정값보다 무조건 커야되는 검증용 빈
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요")
    private String name;

    @Past //미래데이터는 안되고 과거 데이터만 가능하다는 검증
    @ApiModelProperty(notes = "사용자 등록일을 입력해주세요")
    private Date joinDate;

   // 데이터 전달 제어
   // @JsonIgnore //응답시 해당 값은 보여지지 않는다.
    @ApiModelProperty(notes = "사용자 패스워드을 입력해주세요")
    private String password;
   //  @JsonIgnore
    @ApiModelProperty(notes = "사용자 주민번호를 입력해주세요")
    private String ssn;


    @OneToMany(mappedBy = "user") //Post클래스와 연관
    private List<Post> posts;

    //post 데이터가 빠져있는 기본생성자를 추가
    //이유는 이미 UserDaoService 클래스에 데이터를 정의해 놓은 것이 있는데
    //sql파일을 통해 post 데이터를 추가함으로서 생기는 오류
    //따라서 post클래스에서 갖고 있지 않는 데이터의 대한 생성자 생성

    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
