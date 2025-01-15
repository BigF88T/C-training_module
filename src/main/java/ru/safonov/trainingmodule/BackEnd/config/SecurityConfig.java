package ru.safonov.trainingmodule.BackEnd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.safonov.trainingmodule.BackEnd.services.UserDetailsService;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    //Конфигурация самого Spring Security
    //Конфигурация авторизации (не аутентификации)
    @Override
    protected void configure(HttpSecurity http)  throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN") // Предоставлять доступ к странице /admin только пользователям с ролью администратор
                .antMatchers("/auth/login", "/auth/registration", "/error","/css/main.css", "/img/logo.png", "/article").permitAll() // Обработка доступа к страницам неаутентифицированных пользователей
                .anyRequest().hasAnyRole("USER", "ADMIN") // Предоставление доступа ко всем остальным страницам с ролями: пользователь и администратор
                .and()
                .formLogin().loginPage("/auth/login") // Изменение дефолтной страницы login`а
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login")  // Конфигурируем logout
        ;
    }

    //Настраиваем аутентификацию
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder())  // Назначение использования алгоритма BCrypt при аутентификации
        ;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(); // Конфигурация типа шифрования пароля. В данном случае это BCrypt
    }

}
