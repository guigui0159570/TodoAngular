import { TodoItem } from "./todo-item.model";

export interface TodoList {
    idList: Number;
    name: string;
    items: TodoItem[];
}
