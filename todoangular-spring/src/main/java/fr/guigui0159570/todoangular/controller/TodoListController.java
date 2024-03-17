package fr.guigui0159570.todoangular.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guigui0159570.todoangular.model.TodoList;
import fr.guigui0159570.todoangular.service.TodoListService;
import jakarta.validation.Valid;

/**
 * Représente le contrôleur pour la gestion des todolistes.
 * 
 * @author Guillaume CODER
 */
@RestController
@RequestMapping("/todolist")
@CrossOrigin("*")
public class TodoListController {
    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping("/{idList}")
    public ResponseEntity<TodoList> getTodoListById(@PathVariable Long idList) {
        Optional<TodoList> result = todoListService.getTodoListById(idList);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<TodoList>> getAllTodoList() {
        return ResponseEntity.ok(todoListService.getAllTodoList());
    }

    @PostMapping
    public ResponseEntity<Object> addTodoList(@Valid @RequestBody TodoList todoList, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        } else {
            return ResponseEntity.ok(todoListService.addTodoList(todoList));
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateTodoList(@Valid @RequestBody TodoList todoList, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        } else {
            if (todoListService.updateTodoList(todoList)) {
                return ResponseEntity.ok(todoList);
            } else return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idList}")
    public ResponseEntity<Object> deleteTodoListById(@PathVariable Long idList) {
        if (todoListService.deleteTodoListById(idList)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
