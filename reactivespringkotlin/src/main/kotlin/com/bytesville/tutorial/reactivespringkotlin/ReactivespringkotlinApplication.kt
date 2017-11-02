package com.bytesville.tutorial.reactivespringkotlin

import com.bytesville.tutorial.reactivespringkotlin.Repository.BookRepository
import com.bytesville.tutorial.reactivespringkotlin.Domain.Book
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import reactor.core.publisher.Flux

@SpringBootApplication
class ReactivespringkotlinApplication {

    @Bean
    fun runner (bookRepository: BookRepository)= ApplicationRunner{

        val books = Flux.just(Book(title="Cloud Native Java", author = "Josh Long and Kenny")
                                , Book(title="Building Microservices", author = "Sam Newman")
                                ,Book(title="Patterns of Enterprise Architecture", author = "Martin Fowler"))
                        .flatMap {  bookRepository.save(it) }

        bookRepository .deleteAll()
                .thenMany(books)
                .thenMany(bookRepository.findAll())
                .subscribe { println(it) }

    }


}

fun main(args: Array<String>) {
    SpringApplication.run(ReactivespringkotlinApplication::class.java, *args)
}
