package com.bytesville.tutorial.reactivespringkotlin.Controller

import com.bytesville.tutorial.reactivespringkotlin.Service.BookService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BooksController(private val bookService: BookService){

    @GetMapping("/books")
    fun findAllMovies() = bookService.findAll();

    @GetMapping("/books/{id}")
    fun findMovieById(@PathVariable id: String) = bookService.findById(id);

    @GetMapping("/books/{id}/events", produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
    fun events(@PathVariable id: String ) = bookService.events(id);
}