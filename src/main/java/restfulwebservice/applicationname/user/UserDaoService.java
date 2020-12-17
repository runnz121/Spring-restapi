package restfulwebservice.applicationname.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Service //어떠한 것으로 사용될건지 지정해줘야 의존성 주입시 사용 가능
public class UserDaoService {//user에 대한 비즈니스 로직
    private static List<User> users = new ArrayList<>();//메모리에 유저 정보 저장

    private static int usersCount = 3;


    static { //위의 static으로 선언된 users 변수에 넣는거라 static 블럭으로 생성해도 됨
        users.add(new User(1,"Kenneth",new Date()));
        users.add(new User(2,"Alice",new Date()));
        users.add(new User(3,"Elena",new Date()));
    }

    public List<User> findAll(){
        return users; //전체 목록 조회
    }


    public User save(User user) { //사용자 저장 user를 매개변수로 받음 / 접근제어자 + 반환타입 + 매소드이름(파라미터) //즉 save라는 매소드는 파라미터값을 받아 하위로직을 처리 후 User로 반환한다.

        if(user.getId() == null) {
            user.setId(++usersCount);//전에 id가 없으면 하나 추가가
       }
        users.add(user);
        return user;
    }



    public User findOne(int id){ //id값을 매개 변수로 받아서 이를 조회해서 반환
        for (User user : users){
            if(user.getId()==id) { //getId는 롬복으로 호출
                return user;
            }
        }

        return null;
    }



    //유저 삭제
    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();//열거형 타입으로 변환

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId()== id){
                iterator.remove(); //이미 있는 아이디면 삭제
                return user; //삭제된 아이디 반환
            }
        }

        return null;

    }
}
