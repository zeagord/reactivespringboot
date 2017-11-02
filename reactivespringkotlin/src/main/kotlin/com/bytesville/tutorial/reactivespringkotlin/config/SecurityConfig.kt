package com.bytesville.tutorial.reactivespringkotlin.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.vault.config.databases.VaultMongoProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.core.userdetails.MapUserDetailsRepository
import org.springframework.security.core.userdetails.User
import javax.annotation.PostConstruct

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Value("\${X-Service-Pass}")
    var pass:String?=null;

    @Bean
    @PostConstruct
    fun users () = MapUserDetailsRepository(User.
            withUsername("bytesville")
           .password(pass)
            .roles("ADMIN", "USER")
            .build());

}
