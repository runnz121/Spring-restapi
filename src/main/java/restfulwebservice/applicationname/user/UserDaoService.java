package restfulwebservice.applicationname.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Service //어떠한 것으로 사용될건지 지정해줘야 의존성 주입시 사용 가능 -> 여기서는 서비스로 사용함 지정안하면 usercontroller에서 인식 못함
public class UserDaoService {//user에 대한 비즈니스 로직
    private static List<User> users = new ArrayList<>();//메모리에 유저 정보 저장

    private static int usersCount = 3;


    static { //위의 static으로 선언된 users 변수에 넣는거라 static 블럭으로 생성해도 됨
        users.add(new User(1,"Kenneth",new Date(),"pass1","701010-11111111")); //new 연산자를 써줌으로서 메모리에 저장공간 할당
        users.add(new User(2,"Alice",new Date(),"pass2","801010-11111111"));
        users.add(new User(3,"Elena",new Date(),"pass3","901010-11111111"));
    }

    public List<User> findAll(){
        return users; //전체 목록 조회
    }


    public User save(User user) { //사용자 저장 user를 매개변수로 받음 / 접근제어자 + 반환타입 + 매소드이름(파라미터) //즉 save라는 매소드는 파라미터값을 받아 하위로직을 처리 후 User로 반환한다.

        if(user.getId() == null) {
            user.setId(++usersCount);//전에 id가 없으면 하나 추가가
       }
        users.add(user);
        return user;//save된 id 가 있는 user를 반환
    }



    public User findOne(int id){ //id값을 매개 변수로 받아서 이를 조회해서 user로반환
        for (User user : users){ //foreach 사용
            if(user.getId()==id) { //getId는 롬복으로 호출
                return user;
            }
        }

        return null; //없으면 null
    }



    //유저 삭제
    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();//열거형 타입으로 변환(리스트나 배열에 순차적으로 접근)

        while (iterator.hasNext()) { //반복문으로 순차적으로 갖고옴
            User user = iterator.next();

            if (user.getId()== id){ //이미 있는 아이디면
                iterator.remove(); //데이터 삭제
                return user; //삭제한 아이디 반환
            }
        }

        return null;

    }
}
