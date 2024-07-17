package de.nebulit.domain

import de.nebulit.common.CommandResult
import de.nebulit.domain.commands.addtodo.AddTodoCommand
import de.nebulit.events.TodoAddedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate
import java.util.UUID


@Aggregate
class TodoAggregate {

    @AggregateIdentifier
    lateinit var aggregateId: UUID

    @CreationPolicy(AggregateCreationPolicy.ALWAYS)
    @CommandHandler
    fun handle(command: AddTodoCommand):CommandResult {
        AggregateLifecycle.apply(
            TodoAddedEvent(command.aggregateId, command.name, command.description)
        )
        return CommandResult(command.aggregateId, AggregateLifecycle.getVersion())
    }

    @EventSourcingHandler
    fun on(event: TodoAddedEvent) {
        this.aggregateId = event.aggregateId
    }

}
