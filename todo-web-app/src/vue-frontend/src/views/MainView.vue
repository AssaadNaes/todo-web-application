<script setup>
import { onMounted, ref } from 'vue'
import MainHeader from '@/components/MainHeader.vue'
import MainFooter from '@/components/MainFooter.vue'
import CreateTodoForm from '@/components/CreateTodoForm.vue'
import TodoList from '@/components/TodoList.vue'
import apiServices from '@/scripts/apiServices.js'
import '@/styles/main-container.scss'
import '@/styles/body.scss'

const todoList = ref()
const todoCount = ref()
const darkTheme = ref(false)

defineExpose({ getCount })

function toggleTheme() {
  darkTheme.value = !darkTheme.value

  if (darkTheme.value) {
    document.body.classList.add('body__dark')
  } else {
    document.body.classList.remove('body__dark')
  }
}

async function getCount() {
  return (todoCount.value = (await apiServices.getCount()).data)
}

function decreaseCount() {
  todoCount.value--
}

function incrementCount() {
  getCount()
}

onMounted(getCount)

async function createTodo(todoContent) {
  if (todoContent.trim() !== '') {
    await apiServices.create(todoContent)
    todoList.value.getTodos()
    await incrementCount()
  }
}
</script>

<template>
  <div>
    <MainHeader :title="'Todo'" :darkTheme="darkTheme" @theme-changed="toggleTheme" />
    <div class="main-container">
      <CreateTodoForm :dark-theme="darkTheme" @create-todo="(text) => createTodo(text)" />
      <TodoList ref="todoList" :dark-theme="darkTheme" @count-changed="decreaseCount()" />
      <MainFooter :todoCount="todoCount" />
    </div>
  </div>
</template>
