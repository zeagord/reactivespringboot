package com.bytesville.tutorial.reactivespringkotlin.Domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Book(@Id var id: String ? = null, var title: String ? = null, var author: String?=null)