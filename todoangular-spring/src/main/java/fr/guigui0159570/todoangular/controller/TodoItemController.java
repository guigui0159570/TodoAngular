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

import fr.guigui0159570.todoangular.model.TodoItem;
import fr.guigui0159570.todoangular.repository.TodoItemRepository;
import fr.guigui0159570.todoangular.service.TodoItemService;
import jakarta.validation.Valid;

/**
 * Représente un contrôleur pour la gestion des items des todolistes.
 * 
 * @author Guillaume CODER
 */
@RestController
@RequestMapping("/todoitem")
@CrossOrigin("*")
public class TodoItemController {
    private final TodoItemService todoItemService;
    private final TodoItemRepository todoItemRepository;

    public TodoItemController(TodoItemService todoItemService, TodoItemRepository todoItemRepository) {
        this.todoItemService = todoItemService;
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<TodoItem> getTodoItemById(@PathVariable Long idItem) {
        Optional<TodoItem> result = todoItemService.getTodoItemById(idItem);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public Iterable<TodoItem> getAllTodoItem() {
        return todoItemService.getAllTodoItem();
    }

    @PostMapping
    public ResponseEntity<Object> addTodoItem(@Valid @RequestBody TodoItem todoItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        } else {
            todoItemRepository.save(todoItem);
            return ResponseEntity.ok(todoItem);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateTodoItem(@Valid @RequestBody TodoItem todoItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        } else {
            Optional<TodoItem> update = todoItemService.updateTodoItem(todoItem);

            if (update.isPresent()) {
                return ResponseEntity.ok(update.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<Object> deleteTodoItem(@PathVariable Long idItem) {
        if (todoItemRepository.existsById(idItem)) {
            todoItemRepository.deleteById(idItem);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
