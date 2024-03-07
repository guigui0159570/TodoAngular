package fr.guigui0159570.todoangular.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guigui0159570.todoangular.model.TodoItem;
import fr.guigui0159570.todoangular.repository.TodoItemRepository;
import jakarta.validation.Valid;

/**
 * Représente un contrôleur pour la gestion des items des todolistes.
 * 
 * @author Guillaume CODER
 */
@RestController
@RequestMapping("/todoitem")
public class TodoItemController {
    private final TodoItemRepository todoItemRepository;

    public TodoItemController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<TodoItem> getTodoItemById(@PathVariable Long idItem) {
        Optional<TodoItem> result = todoItemRepository.findById(idItem);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public Iterable<TodoItem> getAllTodoItem() {
        return todoItemRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> addTodoItem(@Valid @ModelAttribute TodoItem todoItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        } else {
            todoItemRepository.save(todoItem);
            return ResponseEntity.ok(todoItem);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateTodoItem(@Valid @ModelAttribute TodoItem todoItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        } else {
            Long idItem = todoItem.getIdItem();
            if (idItem != null && todoItemRepository.existsById(idItem)) {
                // On vérifie que la todo liste est bien associé, on la rajoute sinon.
                if (todoItem.getTodoList() == null) {
                    todoItem.setTodoList(todoItemRepository.findById(idItem).orElseThrow().getTodoList());
                }


                todoItemRepository.save(todoItem);
                return ResponseEntity.ok(todoItem);
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
