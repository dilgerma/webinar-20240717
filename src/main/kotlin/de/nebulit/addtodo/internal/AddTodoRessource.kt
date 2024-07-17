package de.nebulit.addtodo.internal

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import de.nebulit.domain.commands.addtodo.AddTodoCommand
import de.nebulit.common.CommandResult

import java.util.UUID;

import java.util.concurrent.CompletableFuture


data class AddTodoPayload(	var aggregateId:UUID,
	var description:String,
	var name:String)

@RestController
class AddTodoRessource(private var commandGateway: CommandGateway) {

    var logger = KotlinLogging.logger {}

    
    @CrossOrigin
    @PostMapping("/debug/addtodo")
    fun processDebugCommand(@RequestParam aggregateId:UUID,
	@RequestParam description:String,
	@RequestParam name:String):CompletableFuture<CommandResult> {
        return commandGateway.send(AddTodoCommand(aggregateId,
	description,
	name))
    }
    

    
       @CrossOrigin
       @PostMapping("/addtodo/{aggregateId}")
    fun processCommand(
        @PathVariable("aggregateId") aggregateId: UUID,
        @RequestBody payload: AddTodoPayload
    ):CompletableFuture<CommandResult> {
         return commandGateway.send(AddTodoCommand(			aggregateId=payload.aggregateId,
			description=payload.description,
			name=payload.name))
        }
       

}
