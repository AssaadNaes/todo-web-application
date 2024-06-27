<script setup>
import '@/styles/todo-item.scss'
import '@/styles/button.scss'

defineProps({
  todoItem: {
    type: Object,
    required: true
  },
  darkTheme: {
    type: Boolean,
    required: false
  }
})
</script>

<template>
  <div
    :class="{
      'todo-container': !todoItem.is_finished && !darkTheme,
      'todo-container__done': todoItem.is_finished && !darkTheme,
      'todo-container__dark': !todoItem.is_finished && darkTheme,
      'todo-container__dark__done': todoItem.is_finished && darkTheme
    }"
  >
    <div
      :class="{
        'todo-text': !darkTheme,
        'todo-text__dark': darkTheme
      }"
    >
      <p>{{ todoItem.content }}</p>
    </div>

    <div>
      <button
        @click="$emit('finishTodo')"
        :class="{
          button__done: !todoItem.is_finished,
          'button__done-disabled': todoItem.is_finished
        }"
        :disabled="todoItem.is_finished"
      >
        Done
      </button>
    </div>

    <div>
      <button @click="$emit('deleteTodo')" class="button__remove">Remove</button>
    </div>
  </div>
</template>
