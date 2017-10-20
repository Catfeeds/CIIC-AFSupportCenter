<template>
  <ul>
    <chat-item v-for="item in chatList" :chatItem="item" :key="item.date"></chat-item>
  </ul>
</template>
<script>
  import chatItem from './chatitem.vue'
  export default {
    name:"chat",
    components: {chatItem},
    props: {
      chatList: {
        type: Array
      }
    }
  }
</script>
<style scoped>
  ul {list-style: none; width: 430px; height: 116px; overflow-y: auto;}
</style>
