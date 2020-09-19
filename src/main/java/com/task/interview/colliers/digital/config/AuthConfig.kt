package com.task.interview.colliers.digital.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
open class AuthConfig : WebSecurityConfigurerAdapter() {

    companion object {
        const val DELIMITER: String = ":"
    }

    @Value("\${spring.security.username.password.list}")
    lateinit var usersWithPasswords: Array<String>

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        val inMemoryAuthentication = auth.inMemoryAuthentication()

        resolveUsers().forEach { user ->
            inMemoryAuthentication
                    .withUser(user.user)
                    .password(passwordEncoder().encode(user.password))
                    .authorities(emptyList())
                    .and()
        }
    }

    private class UserDetails(userWithPassword: List<String>) {
        val user: String = userWithPassword[0]
        val password: String = userWithPassword[1]
    }

    private fun resolveUsers(): List<UserDetails> {
        return usersWithPasswords.map { userWithPassword ->
            createUser(userWithPassword.split(DELIMITER))
        }
    }

    private fun createUser(userAndPasswordArray: List<String>): UserDetails {
        return UserDetails(userAndPasswordArray)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and().httpBasic();
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}