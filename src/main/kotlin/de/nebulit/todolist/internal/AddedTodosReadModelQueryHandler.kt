package de.nebulit.todolist.internal

import de.nebulit.todolist.AddedTodosReadModel
import org.springframework.stereotype.Component
import de.nebulit.todolist.internal.AddedTodosReadModelRepository
import org.axonframework.queryhandling.QueryHandler
import de.nebulit.todolist.AddedTodosReadModelQuery
import java.util.UUID;


@Component
class AddedTodosReadModelQueryHandler(private val repository:AddedTodosReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: AddedTodosReadModelQuery): AddedTodosReadModel? {
      return AddedTodosReadModel(repository.findAll())
  }

}
