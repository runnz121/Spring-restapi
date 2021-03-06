package restfulwebservice.applicationname.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration//스프링부트 실행시 해당 어노테이션 있으면 메모리에 설정정보를 같이 로딩한다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override //security의 http 매소드를 재 정의 하여 h2 database에 접속 가능하게 함
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll(); //h2-console/뒤에 어떤값이와도 모두 허용 //.antMAchers로 해당 path는 통과시킴
        http.csrf().disable(); //csrf 공격방지 설정
        http.headers().frameOptions().disable();//clickjacking 공격방지 설정
    }

    //스프링 시큐리티에 유저 권한 설정 및 부여 설정정 -> 로그인 구현
   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) //AuthenticationProviders를 추가 하기위ㅏㅎㅁ
        throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test") //->id
                .password("{noop}test") //{noop}인코딩 없이 값 그대로를 사용한다는 듯 ->비밀번호
                .roles("USER"); //유저 권한 부여
    }



}
