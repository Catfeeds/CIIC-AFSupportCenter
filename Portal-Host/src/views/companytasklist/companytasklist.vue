<template>
  <div>
    <Tabs>
      <TabPane :label="tabInfo.tab1">
        <company-task-control :isCperator="1"></company-task-control>
      </TabPane>
      <TabPane :label="tabInfo.tab2">
        <company-task-control :isCperator="2"></company-task-control>
      </TabPane>
      <TabPane :label="tabInfo.tab3">
        <company-task-control :isCperator="3"></company-task-control>
      </TabPane>
      <TabPane :label="tabInfo.tab4">
        <company-task-control :isCperator="4"></company-task-control>
      </TabPane>
    </Tabs>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import companyTaskControl from "./companytaskcontrol.vue";

  export default {
    components: {companyTaskControl},
    name:"companytasklist",
    data() {
      return {
        tabInfo: {
          tab1: (h) => {
            return h('div', [
              h('span', '本月处理 '),
              h('Badge', {props: {count: 3}})
            ])
          },
          tab2: (h) => {
            return h('div', [
              h('span', '下月处理 '),
              h('Badge', {props: {count: 3}})
            ])
          },
          tab3: (h) => {
            return h('div', [
              h('span', '已完成')
            ])
          },
          tab4: (h) => {
            return h('div', [
              h('span', '批退')
            ])
          },
        },
      }
    },
    mounted() {

    },
    computed: {

    },
    methods: {

    }
  }
</script>

<style scoped>
  .mt20 {
    margin-top: 20px;
  }
</style>
