package com.lenovo.cloudbuild.mapper;

import com.lenovo.cloudbuild.model.Message;

public interface MessageMaper {

	public Iterable<Message> findAll();

	public Message save(Message message);

	public Message findMessage(Long id);

	public Message deleteMessage(Long id);

}
