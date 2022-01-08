package kr.ac.inha.board.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config.....");
		// @RequestMapping 별 권한제어
		http.authorizeRequests().antMatchers("/login/**").permitAll();  // 로그인 폴더는 권한없이 접근 가능
		http.authorizeRequests().antMatchers("/board/**").hasAnyRole("ADMIN", "USER", "GUEST");  // board는 admin, user, guest 권한만 접근이 가능함
		http.authorizeRequests().antMatchers("/ipo/**").hasAnyRole("ADMIN", "USER", "GUEST");  // board는 admin, user, guest 권한만 접근이 가능함
		http.authorizeRequests().antMatchers("/").hasAnyRole("ADMIN", "USER", "GUEST");  // board는 admin, user, guest 권한만 접근이 가능함
		
		// CSRF 예외처리 (토큰 없이도 동작 가능하게 하기)
		http.csrf().ignoringAntMatchers("/login/**").ignoringRequestMatchers();
		http.csrf().ignoringAntMatchers("/api/**").ignoringRequestMatchers();
		http.csrf().ignoringAntMatchers("/ipo/**").ignoringRequestMatchers();
		//http.csrf().ignoringAntMatchers("/board/**").ignoringRequestMatchers();
		
		// 기본 제공 로그인 페이지
		http.formLogin().loginPage("/login/login.do");
		
		// 권한 없을때 띄울 페이지
		//http.exceptionHandling().accessDeniedPage("/board/openBoardList3.do");
		
		// 로그아웃시 지정 페이지로 이동
		//http.logout().logoutSuccessUrl("/board/openBoardList3.do").invalidateHttpSession(true);
	}

	// 계정 정보 확인 및 비교하여 등록된 사용자인지 확인
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		String query1 = "SELECT user_id username, CONCAT('{noop}', user_pass) password, "
				+ " CASE WHEN enabled = 'Y' THEN true else false END enabled" + " FROM members WHERE user_id = ?";
		String query2 = "SELECT user_id, role_name role" + " FROM members WHERE user_id = ?";
		
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query1).rolePrefix("ROLE_")
				.authoritiesByUsernameQuery(query2);
		/*
		// in memory 계정정보 저장방식
		auth.inMemoryAuthentication().withUser("admin@inha.ac.kr").password("{noop}admin!123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user@inha.ac.kr").password("{noop}user!123").roles("USER");
		auth.inMemoryAuthentication().withUser("guest@inha.ac.kr").password("{noop}guest!123").roles("GUEST");
		*/
	}

	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
}
