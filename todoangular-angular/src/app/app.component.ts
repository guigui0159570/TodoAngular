import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { TodolistComponent } from './todolist/todolist.component';
import { TodolistService } from './todolist.service';
import { TodoList } from './todo-list.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, TodolistComponent],
  styleUrl: './app.component.css',
  templateUrl: './app.component.html',
  providers: [TodolistService]
})
export class AppComponent implements OnInit {
  title = 'TODOAngular';
  todoListArray: TodoList[] = [];

  constructor(private todoListService: TodolistService) {}

  ngOnInit(): void {
    this.todoListService.getAllTodoList().subscribe((data) => {
      this.todoListArray = data;
    });
  }

}
