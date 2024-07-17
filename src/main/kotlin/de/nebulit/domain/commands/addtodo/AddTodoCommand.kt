package de.nebulit.domain.commands.addtodo

import org.axonframework.modelling.command.TargetAggregateIdentifier
import de.nebulit.common.Command
import java.util.UUID;


data class AddTodoCommand(
    @TargetAggregateIdentifier override var aggregateId:UUID,
	var description:String,
	var name:String
): Command
