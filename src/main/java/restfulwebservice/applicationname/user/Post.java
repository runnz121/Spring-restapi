package restfulwebservice.applicationname.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post { //게시판 클래스
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    //User : Post -> 1:N관계 -> Main : Sub -> Parent : Child
    //Post의 값은 여러개가 있고 하나의 값과 메칭 될 수 있다.
    @ManyToOne(fetch = FetchType.LAZY) //fetchtype = lazy : post가 로딩될때 필요한 정보를 가저오겠다
    @JsonIgnore//외부 공개 방지
    private User user;


}
