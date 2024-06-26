<script setup>
import '@/styles/todo-list.scss'
import apiServices from '@/scripts/apiServices.js'
import TodoItem from '@/components/TodoItem.vue'

import { onMounted, ref } from 'vue'

const todos = ref([])

defineProps({
  darkTheme: {
    type: Boolean,
    required: false
  }
})

defineExpose({ getTodos })

onMounted(getTodos)

async function getTodos() {
  return (todos.value = (await apiServices.get()).data)
}

async function finishTodo(todo) {
  todo.is_finished = true
  await apiServices.update(todo.id, todo)
  getTodos()
}

async function deleteTodo(todoItem) {
  await apiServices.delete(todoItem.id)

  const index = todos.value.indexOf(todoItem)
  todos.value.splice(index, 1)
  console.log('Todo deleted from Array!')
}
</script>

<template>
  <div class="todo-list">
    <div v-for="todoItem in todos" :key="todoItem.id">
      <TodoItem
        :darkTheme="darkTheme"
        :todoItem="todoItem"
        @finish-todo="finishTodo(todoItem)"
        @delete-todo="$emit('countChanged'), deleteTodo(todoItem)"
      />
    </div>
  </div>
</template>
