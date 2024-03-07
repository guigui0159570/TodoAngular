package fr.guigui0159570.todoangular.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.guigui0159570.todoangular.model.TodoList;

/**
 * Représente un objet d'accès aux données pour la gestion des TodoList.
 * 
 * @author Guillaume CODER
 */
@Repository
public interface TodoListRepository extends CrudRepository<TodoList, Long> {
}
