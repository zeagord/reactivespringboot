package com.bytesville.tutorial.reactivewebclient

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@SpringBootApplication
class ReactivewebclientApplication {
    @Bean
    fun webClient(): WebClient = WebClient.create("http://localhost:8080/books").mutate().build()


    @Bean
    fun runner (webClient:WebClient)=  ApplicationRunner {
        webClient.get()
                .uri("")
                .retrieve()
                .bodyToFlux(Book::class.java)
                .filter({book -> book.title.equals("Cloud Native Java")})
                .flatMap {
                    webClient.get().uri("/{id}/events", it.id)
                            .retrieve()
                            .bodyToFlux(BookEvent::class.java)
                }
                .subscribe { println(it) }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(ReactivewebclientApplication::class.java, *args)
}


data class Book (val id: String? =null, val title: String? =null, val author: String?=null)

data class BookEvent (val id: String? =null, val date: Date? =null)