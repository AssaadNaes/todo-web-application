package com.naes.todo_web_app.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Repository
public class TodoRepository {
    Logger log = LoggerFactory.getLogger(TodoController.class);
    private final JdbcClient jdbcClient;

    public  TodoRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    int getCount(){
        return jdbcClient.sql("SELECT * FROM todos;")
                .query()
                .listOfRows()
                .size();
    }

    List<Todo> getAll(){
        return jdbcClient.sql("SELECT * FROM todos ORDER BY is_finished;")
                .query(Todo.class)
                .list();
    }

    void create(@RequestBody Todo todo) {
        int updated = jdbcClient.sql("INSERT INTO todos(content, is_finished) VALUES(?, ?);")
                    .params(List.of(todo.content().toLowerCase(), false))
                    .update();

        Assert.state(updated == 1, "Failed to create todo: " + todo.content());
    }

    void update(@PathVariable int id, @RequestBody Todo todo){
        log.info(getAll().toString());
        int updated = jdbcClient.sql("UPDATE todos SET content = ?, is_finished = ? WHERE id = ?;")
                .params(List.of(todo.content(), todo.is_finished(), id))
                .update();

        Assert.state(updated == 1, "Failed to update todo");
    }

    void deleteById(@PathVariable int id){
        int updated = jdbcClient.sql("DELETE FROM todos WHERE id = ?")
                .param(id)
                .update();

        Assert.state(updated == 1, "Failed to delete todo with id: " + id);
    }

    void cleanTable(){
        jdbcClient.sql("DELETE FROM todos").update();
    }
}
