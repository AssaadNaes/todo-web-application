package com.naes.todo_web_app.todo;

public record Todo(
        int id,
        String content,
        boolean is_finished
) {
}
