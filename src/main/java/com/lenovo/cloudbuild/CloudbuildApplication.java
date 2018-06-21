package com.lenovo.cloudbuild;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import com.lenovo.cloudbuild.mapper.MessageMaper;
import com.lenovo.cloudbuild.mapper.MessageMaperImpl;
import com.lenovo.cloudbuild.model.Message;

@SpringBootApplication
public class CloudbuildApplication {

	@Bean
	public MessageMaper messageMaper() {
		return new MessageMaperImpl();
	}
	
	@Bean
	public Converter<String, Message> messageConverter() {
		return new Converter<String, Message>() {
			@Override
			public Message convert(String id) {
				return messageMaper().findMessage(Long.valueOf(id));
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CloudbuildApplication.class, args);
	}
}
