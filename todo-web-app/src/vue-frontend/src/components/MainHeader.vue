<script setup>
import '@/styles/theme-icons.scss'
import '@/styles/headings.scss'
import '@/styles/header.scss'
import '@/styles/switch-button.scss'

defineProps({
  title: {
    type: String,
    required: true
  },
  darkTheme: {
    type: Boolean,
    required: true
  }
})
</script>

<template>
  <div
    :class="{
      'header-container': !darkTheme,
      'header-container__dark': darkTheme
    }"
  >
    <h1 class="header-title">{{ title }}</h1>
    <label class="switch">
      <img class="moon" v-if="darkTheme" src="@/images/halbmond.png" alt="Moon" />
      <img class="sun" v-else src="@/images/sonne.png" alt="Moon" />
      <input type="checkbox" @change="$emit('themeChanged')" />
      <span class="slider round"></span>
    </label>
  </div>
</template>
