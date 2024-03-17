package fr.guigui0159570.todoangular.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.guigui0159570.todoangular.model.TodoList;
import fr.guigui0159570.todoangular.repository.TodoListRepository;

/**
 * Représente un service pour la gestion des todolistes.
 * 
 * @author Guillaume CODER
 */
@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public boolean isTodoListExistsById(Long idList) {
        return todoListRepository.existsById(idList);
    }

    public Optional<TodoList> getTodoListById(Long idList) {
        return todoListRepository.findById(idList);
    }

    public Iterable<TodoList> getAllTodoList() {
        return todoListRepository.findAll();
    }

    public TodoList addTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    public boolean updateTodoList(TodoList todoList) {
        if (todoList.getIdList() != null && todoListRepository.existsById(todoList.getIdList())) {
            // C'est une mise à jour, pas une insertion.
            todoListRepository.save(todoList);
            return true;
        } else return false;
    }

    public boolean deleteTodoList(TodoList todoList) {
        return deleteTodoListById(todoList.getIdList());
    }

    public boolean deleteTodoListById(Long idList) {
        if (isTodoListExistsById(idList)) {
            todoListRepository.deleteById(idList);
            return true;
        } else return false;
    }

}
