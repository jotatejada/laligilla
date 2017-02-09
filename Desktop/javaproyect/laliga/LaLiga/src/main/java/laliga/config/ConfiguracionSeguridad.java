package laliga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import laliga.modelo.servicio.ServicioAutenticacion;

@Configuration
@EnableWebSecurity


public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	@Autowired private ServicioAutenticacion servicioAutenticacion;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(servicioAutenticacion)
			.passwordEncoder(encoder());
			/*auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");*/
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				//.antMatchers("/jugadores/**", "/equipos/**", "/federaciones/**").hasRole("ADMIN")
				
				.anyRequest().permitAll()
		.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/autenticar")
				.defaultSuccessUrl("/jugadores")
				.failureUrl("/login?semacesso=true")
				.usernameParameter("username")
				.passwordParameter("password")
				
			.and()
				.logout()
					.logoutUrl("/sair")
					.logoutSuccessUrl("/login?saiu=true");
	}
	
	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
	}
	

}
