package de.nebulit.todolist.internal

import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.UUID
import de.nebulit.events.TodoAddedEvent
import de.nebulit.todolist.AddedTodosReadModelEntity


import mu.KotlinLogging

interface AddedTodosReadModelRepository : JpaRepository<AddedTodosReadModelEntity, UUID>

@Component
class AddedTodosReadModelProjector(
    var repository: AddedTodosReadModelRepository
) {


    @EventHandler
    fun on(event: TodoAddedEvent) {
        //throws exception if not available (adjust logic)
        val entity = this.repository.findById(event.aggregateId).orElse(AddedTodosReadModelEntity())
        entity.apply {
            aggregateId = event.aggregateId
            description = event.description
            name = event.name
        }.also { this.repository.save(it) }
    }

}
