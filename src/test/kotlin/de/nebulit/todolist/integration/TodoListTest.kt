package de.nebulit.todolist.integration

import de.nebulit.common.CommandResult
import de.nebulit.common.support.BaseIntegrationTest
import de.nebulit.common.support.RandomData
import de.nebulit.common.support.awaitUntilAssserted
import de.nebulit.domain.commands.addtodo.AddTodoCommand
import de.nebulit.todolist.AddedTodosReadModelQuery
import de.nebulit.todolist.AddedTodosReadModel
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.assertj.core.api.Assertions.assertThat
import java.util.*

class TodoListTestIntegration : BaseIntegrationTest() {

    @Autowired
    private lateinit var commandGateway: CommandGateway

    @Autowired
    private lateinit var queryGateway: QueryGateway

    @Test
    fun `TodoListTest`() {


        val aggregateId = UUID.randomUUID()

        var addTodoCommand = RandomData.newInstance<AddTodoCommand> {
            this.aggregateId = aggregateId
        }

        var addTodoCommandResult = commandGateway.sendAndWait<CommandResult>(addTodoCommand)



        awaitUntilAssserted {
            var readModel = queryGateway.query(AddedTodosReadModelQuery(), AddedTodosReadModel::class.java)
            //TODO add assertions
            assertThat(readModel.get().data).isNotEmpty
        }


    }

}
