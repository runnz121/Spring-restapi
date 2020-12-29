package restfulwebservice.applicationname.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //DB관련된 빈 추가
public interface UserRepository extends JpaRepository<User,Integer> { //Jparepository를 상속 받고 이는 User타입을 다루며 User의 키값은 Integer 타입이다




}
