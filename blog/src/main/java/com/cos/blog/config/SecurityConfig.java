package com.cos.blog.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.cos.blog.config.auth.PrincipalDetailService;

// 아래 3개는 시큐리티 세트 어노테이션
// 빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration
// 시큐리티 필터가 등록이 된다.
@EnableWebSecurity
// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Bean
	public BCryptPasswordEncoder encodePWD() {

		return new BCryptPasswordEncoder();
	}

	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable() // csrf 토큰 비활성화(테스트시 걸어두는 게 좋음)
				.authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")
				// 스프링 시큐리티가 해당 주소로 요청 오는 로그인을 가로채서 대신 로그인 해준다.
				.defaultSuccessUrl("/");
		
		http.rememberMe()
				.key("Yong")
				.userDetailsService(principalDetailService)
				.tokenRepository(tokenRepository())
				.tokenValiditySeconds(604800);
		
		http.logout()
		  .logoutUrl("/logout")
		  .logoutSuccessUrl("/")
		  .invalidateHttpSession(true) //세션 삭제
		  .deleteCookies("remember-me", "JSESSIONID");
	} // configure(http)

	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}
}