<template>
  <ul>
    <li v-for="(step, index) in steps" :key="index">
      <div class="progressPointBig" v-if="step.isOver === 0"></div>
      <div class="progressPointSmall" v-else-if="step.isOver === 1"></div>
      <div class="progressPointSmallGray" v-else></div>
      <div class="progressBar" v-if="step.isOver === 1"></div>
      <div class="progressBarGray" v-else></div>

      <div class="progressPointSmallEnd" v-if="(index === steps.length - 1) && (step.isOver === 1 || step.isOver === 0)"></div>
      <div class="progressPointSmallGrayEnd" v-else-if="(index === steps.length - 1) && (step.isOver === -1)"></div>

      <p>
        {{step.title}}<br/>
        {{step.author}}<br/>
        {{step.date}}<br/>
      </p>
    </li>
  </ul>
</template>
<script>
  export default {
    name:"progressBar",
    data() {
      return{
        steps: [
          {isOver: 1, title: '创建支付', author: '迎曦', date: '2016-12-12 12:32', action: {name: '', action: ''}},
          {isOver: 0,title: '部门初审', author: '小龙女', date: '', action: {name: '催一下', action: ''}},
          {isOver: -1, title: '财务复审', author: '', date: '', action: {name: '', action: ''}},
        ], //isOver: 1-已经过 0-正在经过 -1-未经过
      }
    },
    computed: {

    },
    methods: {

    }
  }
</script>
<style scoped>
  ul, li {list-style: none;}
  ul {width: 640px; overflow: auto;}
  li {width: 213px; height: 80px; float: left; position: relative;}
  li p {width: 100%; position: absolute; left: 0; top: 24px;}

  .progressPointBig, .progressPointSmall, .progressPointSmallGray, .progressPointSmallEnd, .progressPointSmallGrayEnd {position: absolute;}
  .progressPointBig {width: 16px; height: 16px; top: 0; border-radius: 8px; background-color: #2d8cf0; z-index: 3;}
  .progressPointSmall, .progressPointSmallGray, .progressPointSmallEnd, .progressPointSmallGrayEnd {width: 12px; height: 12px; top: 2px; border-radius: 6px;}
  .progressPointSmall {background-color: #2d8cf0; z-index: 3;}
  .progressPointSmallGray {background-color: #ccc; z-index: 3;}
  .progressPointSmallEnd {right: 0;background-color: #2d8cf0; z-index: 4;}
  .progressPointSmallGrayEnd {right: 0;background-color: #ccc; z-index: 4;}

  .progressBar, .progressBarGray {width: 100%; height: 4px; position: absolute; top: 6px; left: 0;}
  .progressBar {background-color: #2d8cf0;}
  .progressBarGray {background-color: #ccc;}
</style>
