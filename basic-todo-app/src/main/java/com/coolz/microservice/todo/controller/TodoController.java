package com.coolz.microservice.todo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coolz.microservice.todo.model.Todo;
import com.coolz.microservice.todo.repository.TodoJpaRepository;

import jakarta.validation.Valid;

/**
 * @author Mahir
 *
 */
@Controller
@SessionAttributes("username")
public class TodoController {

	@Autowired
	private TodoJpaRepository _todoJpaRepository;

	@GetMapping("list-todos")
	public String listAllTodos(ModelMap modelMap) {
		String userName = loggeedInUserName();
		List<Todo> todoList = _todoJpaRepository.findByUserName(userName);
		modelMap.addAttribute("todos", todoList);
		return "listTodos";
	}

	@GetMapping("add-todo")
	public String showNewTodoPage(ModelMap model) {
		String username = (String) model.get("name");
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

	@PostMapping("add-todo")
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "todo";
		}

		String username = loggeedInUserName();
		todo.setUserName(username);

		_todoJpaRepository.save(todo);
		return "redirect:list-todos";
	}

	@GetMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		_todoJpaRepository.deleteById(id);
		return "redirect:list-todos";

	}

	@GetMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = _todoJpaRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}

	@PostMapping("update-todo")
	public String updateTodoPage(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "todo";
		}

		String username = loggeedInUserName();
		todo.setUserName(username);
		_todoJpaRepository.save(todo);
		return "redirect:list-todos";
	}

	private String loggeedInUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
