<script setup>
import '@/styles/footer.scss'

defineProps(['todoCount'])
</script>

<template>
  <div class="footer-container todo-counter">
    <p>
      Total todos: <span>{{ todoCount }}</span>
    </p>
  </div>
</template>
