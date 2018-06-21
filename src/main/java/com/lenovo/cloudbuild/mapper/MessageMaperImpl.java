package com.lenovo.cloudbuild.mapper;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import com.lenovo.cloudbuild.model.Message;

public class MessageMaperImpl implements MessageMaper {

	private static AtomicLong counter = new AtomicLong();
	private final ConcurrentMap<Long, Message> messages = new ConcurrentHashMap<Long, Message>();

	@Override
	public Iterable<Message> findAll() {
		return this.messages.values();
	}

	@Override
	public Message save(Message message) {
		Long id  = message.getId();
		if(id == null){
			id = counter.incrementAndGet();
			message.setId(id);
		}
		this.messages.put(id, message);
		return message;
	}

	@Override
	public Message findMessage(Long id) {
		return this.messages.get(id);
	}

	@Override
	public Message deleteMessage(Long id) {
		Message remove = this.messages.remove(id);
		return remove;
	}

}
