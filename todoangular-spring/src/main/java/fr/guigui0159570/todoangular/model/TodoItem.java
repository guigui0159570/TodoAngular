package fr.guigui0159570.todoangular.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Représente une tâche d'une todo liste.
 * 
 * @author Guillaume CODER
 */
@Entity
public class TodoItem {
    /**
     * L'id de l'item.
     */
    @Id @GeneratedValue
    private Long idItem;
    /**
     * Le nom de l'item.
     */
    @NotBlank
    private String name;
    /**
     * L'état de l'item.
     */
    @NotNull
    private Boolean state;
    /**
     * La todo liste associée.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    @JsonBackReference
    private TodoList todoList;

    /**
     * Renvoie l'id de l'item.
     * @return L'id de l'item.
     */
    public Long getIdItem() {
        return idItem;
    }

    /**
     * Fixe l'id de l'item.
     * @param idItem L'id de l'item.
     */
    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    /**
     * Renvoie le nom de l'item.
     * @return Le nom de l'item.
     */
    public String getName() {
        return name;
    }

    /**
     * Fixe le nom de l'item.
     * @param name Le nom de l'item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renvoie l'état de l'item.
     * @return L'état de l'item.
     */
    public Boolean isState() {
        return state;
    }

    /**
     * Fixe l'état de l'item.
     * @param state Le nom de l'item.
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * Renvoie la todo liste associée.
     * @return La todo liste associée.
     */
    public TodoList getTodoList() {
        return todoList;
    }

    /**
     * Fixe la todo liste associée.
     * @param todoList La todo liste associée.
     */
    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

}
