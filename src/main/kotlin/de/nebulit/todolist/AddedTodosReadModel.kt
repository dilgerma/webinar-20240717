package de.nebulit.todolist

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.UUID;


class AddedTodosReadModelQuery()

@Entity
class AddedTodosReadModelEntity {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "aggregateId")
    var aggregateId: UUID? = null;
    @Column(name = "description")
    var description: String? = null;
    @Column(name = "name")
    var name: String? = null;
}

data class AddedTodosReadModel(val data: List<AddedTodosReadModelEntity>)
