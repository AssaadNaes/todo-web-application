package com.naes.todo_web_app.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(TodoRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    void SetUp(){
        todoRepository.cleanTable();
        todoRepository.create(new Todo(1, "first test", false));
        todoRepository.create(new Todo(2, "second test", true));
    }

    @Test
    void getCount() {
        int count = todoRepository.getCount();
        assertEquals(2, count);
    }

    @Test
    void getAll() {
        List<Todo> todos = todoRepository.getAll();
        assertEquals(todos.size(), 2);
    }

    @Test
    void create() {
        todoRepository.create(new Todo(3, "third test", true));
        List<Todo> todos = todoRepository.getAll();
        assertEquals(3, todos.size());
    }

    @Test
    void update() {
        List<Todo> todos = todoRepository.getAll();
        int id = todos.get(0).id();
        todoRepository.update(id, new Todo(0, "updated test todo", true));
    }

    @Test
    void deleteById() {
        List<Todo> todos = todoRepository.getAll();
        int id = todos.get(0).id();
        todoRepository.deleteById(id);
    }
}
