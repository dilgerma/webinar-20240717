package de.nebulit.todolist.internal

import de.nebulit.todolist.AddedTodosReadModel
import de.nebulit.todolist.AddedTodosReadModelQuery
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import java.util.concurrent.CompletableFuture
import java.util.UUID;




@RestController
class TodolistRessource(
    private var queryGateway: QueryGateway
    ) {

    var logger = KotlinLogging.logger {}

    @CrossOrigin
    @GetMapping("/todolist")
                    fun findReadModel():CompletableFuture<AddedTodosReadModel> {
                         return queryGateway.query(AddedTodosReadModelQuery(), AddedTodosReadModel::class.java)  
                    }

}
