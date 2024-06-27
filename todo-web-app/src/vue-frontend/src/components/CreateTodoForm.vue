<script setup>
import { ref } from 'vue'
import '@/styles/todo-form.scss'

const todoText = ref('')
const maxLength = 40
const remainingCharacters = ref(maxLength)

defineProps({
  darkTheme: {
    type: Boolean,
    required: true
  }
})

function getText() {
  return todoText.value
}

const validateInput = () => {
  if (todoText.value.length > maxLength) {
    todoText.value = todoText.value.slice(0, maxLength)
  }
  remainingCharacters.value = maxLength - todoText.value.length
}
</script>

<template>
  <div>
    <form
      class="todo-form"
      @submit.prevent="
        $emit('createTodo', getText()), ((todoText = ''), (remainingCharacters = 40))
      "
    >
      <div class="textbox-container test-helper">
        <input
          :class="{
            'textbox-input': !darkTheme,
            'textbox-input__dark': darkTheme
          }"
          @input="validateInput()"
          type="text"
          placeholder="Enter a todo.."
          v-model="todoText"
          maxlength="40"
        />
        <span class="char-count">{{ remainingCharacters }}/40</span>
      </div>
      <input type="submit" class="button__add" value="Add" />
    </form>
  </div>
</template>
