package com.kuyt1819.webservice.config.auth;

import com.kuyt1819.webservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵션들을 disable한다.
                .and()
                    //URL별 권한 관리를 설정하는 옵션의 시작점. authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있다.
                    .authorizeRequests()
                    //andMatchers == 권한 관리 대상을 지정하는 옵션
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile", "/register").permitAll() //permitAll() 옵션을 통해 전체 열람권한을 줌.
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // /api/v1/** 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록.
                    .anyRequest().authenticated() //설정된 값 이외의 URL들은 모두 인증된 사용자에게만 허용하겠다는 의미
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        //OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당한다
                        .userInfoEndpoint()
                            //소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다
                            //리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시한다.
                            .userService(customOAuth2UserService);
    }
}