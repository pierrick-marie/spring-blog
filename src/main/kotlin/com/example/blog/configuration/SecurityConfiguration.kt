package com.example.blog.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfiguration {

//	@Bean
//	fun filterChain(http: HttpSecurity): SecurityFilterChain {
//		http
//			.httpBasic()
//			.and()
//			.requiresChannel().anyRequest().requiresSecure()
//			.and()
//			.authorizeHttpRequests()
//			.requestMatchers("/").permitAll()
//			.requestMatchers("/authors").hasRole("USER")
//			.and()
//			.formLogin().loginPage("/login")
//
//		return http.build()
//	}

	@Bean
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		http
			.httpBasic(withDefaults())
//			.authorizeHttpRequests()
//			.requestMatchers("/").permitAll()
//			.and()
			.formLogin().loginPage("/login")

		return http.build()
	}

	@Bean
	fun userDetailsService(): UserDetailsService {

		val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
		val password = encoder.encode("password")
		val manager = InMemoryUserDetailsManager()

		val user = User.withUsername("user")
			.password(password)
			.roles("USER")
			.build()
		manager.createUser(user)

		val admin = User.withUsername("admin")
			.password(password)
			.roles("USER", "ADMIN")
			.build()
		manager.createUser(admin)

		return manager
	}
}