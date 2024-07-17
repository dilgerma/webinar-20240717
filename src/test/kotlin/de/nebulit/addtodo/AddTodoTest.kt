package de.nebulit.addtodo

import de.nebulit.common.Event
import de.nebulit.common.support.RandomData
import de.nebulit.domain.TodoAggregate
import de.nebulit.common.CommandException
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import de.nebulit.domain.commands.addtodo.AddTodoCommand;
import de.nebulit.events.TodoAddedEvent
import java.util.UUID

class AddTodoTest {

    private lateinit var fixture: FixtureConfiguration<TodoAggregate>

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(TodoAggregate::class.java)
    }

    @Test
    fun `AddTodoTest`() {
        //GIVEN
        val events = mutableListOf<Event>()


        //WHEN
        val command = AddTodoCommand(
            aggregateId = UUID.fromString("090f0b7a-141e-46b9-a561-0f72c5510670"),
            description = RandomData.newInstance { },
            name = RandomData.newInstance { }
        )

        //THEN
        val expectedEvents = mutableListOf<Event>()

        expectedEvents.add(RandomData.newInstance<TodoAddedEvent> {
            this.aggregateId = command.aggregateId
            this.name = command.name
            this.description = command.description
        })


        fixture.given(events)
            .`when`(command)
            .expectSuccessfulHandlerExecution()
            .expectEvents(*expectedEvents.toTypedArray())
    }

}
