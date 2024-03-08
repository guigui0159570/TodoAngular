import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TodoList } from './todo-list.model';

@Injectable({
  providedIn: 'root'
})
export class TodolistService {
  readonly API_SPRING =  "http://localhost:8080";
  readonly ENDPOINT = "/todolist"

  constructor(private httpClient: HttpClient) {

  }

  getTodoListById(idList: Number) : Observable<TodoList[]> {
    return this.httpClient.get<TodoList[]>(this.API_SPRING + this.ENDPOINT + `/${idList}`)
  }

  getAllTodoList() : Observable<TodoList[]> {
    return this.httpClient.get<TodoList[]>(this.API_SPRING + this.ENDPOINT + "/all")
  }


}
