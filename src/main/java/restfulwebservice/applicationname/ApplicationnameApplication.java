package restfulwebservice.applicationname;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class ApplicationnameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationnameApplication.class, args);
	}

	@Bean//다국어 처리를 위한 locale 지정
	public LocaleResolver localeResolver()	{
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();//session을 통해서 locale 정보를 얻어옴
		localeResolver.setDefaultLocale(Locale.KOREA); //기본 locale 및 언어 지정
		return localeResolver;
	}

}
