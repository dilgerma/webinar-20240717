package de.nebulit.events

import de.nebulit.common.Event

import java.util.UUID;


data class TodoAddedEvent(
    var aggregateId:UUID,
	var name:String,
	var description:String
) : Event
