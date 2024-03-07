package fr.guigui0159570.todoangular.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

/**
 * Représente une todo liste.
 * 
 * @author Guillaume CODER
 */
@Entity
public class TodoList {
    /**
     * L'id de la liste.
     */
    @Id @GeneratedValue
    private Long idList;
    /**
     * Le nom de la liste.
     */
    @NotBlank
    private String name;
    /**
     * La liste des items de la liste.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "todoList")
    private Set<TodoItem> items;

    /**
     * Renvoie l'id de la liste.
     * @return L'id de la liste.
     */
    public Long getIdList() {
        return idList;
    }

    /**
     * Fixe l'id de la liste.
     * @param idList L'id de la liste.
     */
    public void setIdList(Long idList) {
        this.idList = idList;
    }

    /**
     * Renvoie le nom de la liste.
     * @return Le nom de la liste.
     */
    public String getName() {
        return name;
    }

    /**
     * Fixe le nom de la liste.
     * @param name Le nom de la liste.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renvoie la liste des items de la liste.
     * @return La liste des items de la liste.
     */
    public Set<TodoItem> getItems() {
        return items;
    }

    /**
     * Fixe la liste des items de la liste.
     * @param items La liste des items de la liste.
     */
    public void setItems(Set<TodoItem> items) {
        this.items = items;
    }

    
}
