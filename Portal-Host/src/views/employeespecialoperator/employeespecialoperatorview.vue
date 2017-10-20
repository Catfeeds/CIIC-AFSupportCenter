<template>
  <div>
    <Tabs>
      <TabPane label="未处理">
        <employeespecialoperatorcontrol :isCperator="1"></employeespecialoperatorcontrol>
      </TabPane>
      <TabPane label="处理中">
        <employeespecialoperatorcontrol :isCperator="2"></employeespecialoperatorcontrol>
      </TabPane>
      <TabPane label="已完成">
        <employeespecialoperatorcontrol :isCperator="3"></employeespecialoperatorcontrol>
      </TabPane>
      <TabPane label="已批退">
        <employeespecialoperatorcontrol :isCperator="4"></employeespecialoperatorcontrol>
      </TabPane>
    </Tabs>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import employeespecialoperatorcontrol from "./employeespecialoperatorcontrol.vue";

  export default {
    components: {employeespecialoperatorcontrol},
    name:"employeespecialoperatorview",
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
