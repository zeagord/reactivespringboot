package com.bytesville.tutorial.reactivespringkotlin.Repository

import com.bytesville.tutorial.reactivespringkotlin.Domain.Book
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface BookRepository : ReactiveMongoRepository<Book, String> {
}