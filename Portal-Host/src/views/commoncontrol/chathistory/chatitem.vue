<template>
  <li>
    <div class="icon">
      <img :src="chatItem.icon">
    </div>
    <div class="content">
      <p class="info">
        <span>{{chatItem.name}}</span>
        <span>{{chatItem.date}}</span>
      </p>
      <p class="text">{{chatItem.content}}</p>
    </div>
  </li>
</template>
<script>
  export default {
    name:"employeecommcialprogress",
    props: {
      chatItem: {
        type: Object
      }
    }
  }
</script>
<style scoped>
  li {clear: both; margin-top: 10px;}
  .icon {
    width: 48px;
    margin-right: 20px;
    float: left;
  }
  .icon img {
    width: 48px;
    height: 48px;
    border-radius: 24px;
  }
  .content {
    width: 352px;
    float: left;
  }
  .content .info {
    color: #ccc;
    height: 24px;
  }
  .content .text {
    color: #333;
  }
</style>
