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

import fr.guigui0159570.todoangular.model.TodoList;
import fr.guigui0159570.todoangular.repository.TodoListRepository;
import jakarta.validation.Valid;

/**
 * Représente le contrôleur pour la gestion des todolistes.
 * 
 * @author Guillaume CODER
 */
@RestController
@RequestMapping("/todolist")
public class TodoListController {
    private final TodoListRepository todoListRepository;

    public TodoListController(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @GetMapping("/{idList}")
    public ResponseEntity<TodoList> getTodoListById(@PathVariable Long idList) {
        Optional<TodoList> result = todoListRepository.findById(idList);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public Iterable<TodoList> getAllTodoList() {
        return todoListRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> addTodoList(@Valid @ModelAttribute TodoList todoList, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        } else {
            todoListRepository.save(todoList);
            return ResponseEntity.ok(todoList);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateTodoList(@Valid @ModelAttribute TodoList todoList, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        } else {
            if (todoList.getIdList() != null && todoListRepository.existsById(todoList.getIdList())) {
                // C'est une mise à jour, pas une insertion.
                todoListRepository.save(todoList);
                return ResponseEntity.ok(todoList);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @DeleteMapping("/{idList}")
    public ResponseEntity<Object> deleteTodoListById(@PathVariable Long idList) {
        if (todoListRepository.existsById(idList)) {
            todoListRepository.deleteById(idList);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
