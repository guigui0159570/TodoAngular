package fr.guigui0159570.todoangular.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.guigui0159570.todoangular.model.TodoItem;
import fr.guigui0159570.todoangular.repository.TodoItemRepository;

/**
 * Représente un service pour gérer les todo items des todolistes.
 * 
 * @author Guillaume CODER
 */
@Service
public class TodoItemService {
    private final TodoItemRepository todoItemRepository;

    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public boolean isTodoItemExistsById(Long idItem) {
        return todoItemRepository.existsById(idItem);
    }

    public Optional<TodoItem> getTodoItemById(Long idItem) {
        return todoItemRepository.findById(idItem);
    }

    public Iterable<TodoItem> getAllTodoItem() {
        return todoItemRepository.findAll();
    }

    public TodoItem addTodoItem(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public Optional<TodoItem> updateTodoItem(TodoItem todoItem) {
        // TODO a changer.
        Long idItem = todoItem.getIdItem();

        if (idItem != null && isTodoItemExistsById(idItem)) {
            // L'item a bien un ID correct.

            if (todoItem.getTodoList() == null) {
                // On configure l'idList si celui-ci est manquant.
                todoItem.setTodoList(todoItemRepository.findById(idItem).orElseThrow().getTodoList());
            }

            return Optional.of(todoItemRepository.save(todoItem));
        } else return Optional.empty();
    }

    public boolean deleteTodoItem(TodoItem todoItem) {
        return deleteTodoItemById(todoItem.getIdItem());
    }

    public boolean deleteTodoItemById(Long idItem) {
        if (isTodoItemExistsById(idItem)) {
            todoItemRepository.deleteById(idItem);
            return true;
        } else return false;
    }

}
