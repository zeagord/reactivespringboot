package com.bytesville.tutorial.reactivespringkotlin.Service

import com.bytesville.tutorial.reactivespringkotlin.Domain.BookEvent
import com.bytesville.tutorial.reactivespringkotlin.Repository.BookRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink
import java.time.Duration
import java.util.*

@Service
class BookService(private val bookRepository: BookRepository) {

    // Find all the books in the document
    fun findAll()  = bookRepository.findAll();

    // Find a book by its ID
    fun findById(id: String) = bookRepository.findById(id);

    //Generate events for the book
    fun events (id: String) = Flux
            .generate({ sink: SynchronousSink<BookEvent> ->  sink.next(BookEvent(id,  Date())) })
            .delayElements(Duration.ofSeconds(1L));
}