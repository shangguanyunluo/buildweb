package com.lenovo.cloudbuild.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lenovo.cloudbuild.mapper.MessageMaper;
import com.lenovo.cloudbuild.model.Message;

//@Controller
//@RequestMapping("/message")
public class MessageController {

	private final MessageMaper messageMaper;

	public MessageController(MessageMaper messageMaper) {
		this.messageMaper = messageMaper;
	}

	@GetMapping
	public ModelAndView list() {
		Iterable<Message> messages = this.messageMaper.findAll();
		return new ModelAndView("messages/list", "messages", messages);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Message message) {
		return new ModelAndView("messages/view", "message", message);
	}

	@GetMapping(params = "form")
	public String createForm(@ModelAttribute Message message) {
		return "messages/form";
	}

	@PostMapping
	public ModelAndView create(@Valid Message message, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			System.out.println("result.hasErrors()");
			return new ModelAndView("messages/form", "formErrors", result.getAllErrors());
		}
		System.out.println("redirect:/{message.id}");
		message = this.messageMaper.save(message);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
		return new ModelAndView("redirect:/{message.id}", "message.id", message.getId());
	}

	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable(value = "id") Long id) {
		this.messageMaper.deleteMessage(id);
		Iterable<Message> messages = this.messageMaper.findAll();
		return new ModelAndView("messages/list", "messages", messages);
	}

	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Message message) {
		return new ModelAndView("messages/form", "message", message);
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

}
