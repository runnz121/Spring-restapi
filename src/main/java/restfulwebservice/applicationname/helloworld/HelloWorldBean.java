package restfulwebservice.applicationname.helloworld;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//lombok 사용위함(setter, getter)
@AllArgsConstructor//private String message를 사용하기 위한 생성자를 대신해줌
                    //public HelloWorldBean(String message) {
                    //      this.message = message; }
@NoArgsConstructor//디폴트 생성자 만들기
public class HelloWorldBean {

    private String message;// setter getter 모두 포함

}
