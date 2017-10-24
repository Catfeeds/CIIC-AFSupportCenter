<template>
  <div>
    <Tabs>
      <TabPane :label="tabInfo.tab1">
        <company-task-control :isCperator="1" :taskData="companytasklist.taskData"></company-task-control>
      </TabPane>
      <TabPane :label="tabInfo.tab2">
        <company-task-control :isCperator="2" :taskData="companytasklist.taskData"></company-task-control>
      </TabPane>
      <TabPane :label="tabInfo.tab3">
        <company-task-control :isCperator="3" :taskData="companytasklist.taskData"></company-task-control>
      </TabPane>
      <TabPane :label="tabInfo.tab4">
        <company-task-control :isCperator="4" :taskData="companytasklist.taskData"></company-task-control>
      </TabPane>
    </Tabs>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import companyTaskControl from "./companytaskcontrol.vue";
  import * as eventType from '../../store/EventTypes/companytasklist/CompanyTaskListType'

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
      this.setCompanyTaskList()
    },
    computed: {
      ...mapGetters([
        'companytasklist'
      ])
    },
    methods: {
      ...mapActions({
        setCompanyTaskList: eventType.COMPANYTASKLIST
      })
    }
  }
</script>

<style scoped>
  .mt20 {
    margin-top: 20px;
  }
</style>
