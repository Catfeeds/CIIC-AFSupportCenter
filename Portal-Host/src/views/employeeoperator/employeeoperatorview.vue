<template>
  <div class="smList">
    <Tabs>
      <TabPane label="本月处理">
        <employeeoperatorcontrol :isCperator="1"></employeeoperatorcontrol>
      </TabPane>
      <TabPane label="下月处理">
        <employeeoperatorcontrol :isCperator="2"></employeeoperatorcontrol>
      </TabPane>
      <TabPane label="已完成">
        <employeeoperatorcontrol :isCperator="3"></employeeoperatorcontrol>
      </TabPane>
      <TabPane label="批退">
        <employeeoperatorcontrol :isCperator="4"></employeeoperatorcontrol>
      </TabPane>
    </Tabs>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import employeeoperatorcontrol from "./employeeoperatorcontrol.vue";

  export default {
    components: {employeeoperatorcontrol},
    name:"employeeoperatorview",
    data() {
      return {

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
