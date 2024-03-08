import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { TodoList } from '../todo-list.model';

@Component({
  selector: 'app-todolist',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './todolist.component.html',
  styleUrl: './todolist.component.css'
})
export class TodolistComponent {
  @Input() todoList: TodoList|null = null;
}
