package fr.guigui0159570.todoangular.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.guigui0159570.todoangular.model.TodoItem;

/**
 * Représente un objet d'accès aux données pour la gestion des items des todolistes.
 * 
 * @author Guillaume CODER
 */
@Repository
public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
}
