<template>
  <div class="smList">
    <Tabs>
      <TabPane label="本月处理">
        <employeeoperatorcontrol :isCperator="1" :customerData="employeeoperatorview.customerData" :sSocialSecurityTypeData="employeeoperatorview.sSocialSecurityTypeData" :employeeResultData="employeeoperatorview.employeeResultData"></employeeoperatorcontrol>
      </TabPane>
      <TabPane label="下月处理">
        <employeeoperatorcontrol :isCperator="2" :customerData="employeeoperatorview.customerData" :sSocialSecurityTypeData="employeeoperatorview.sSocialSecurityTypeData" :employeeResultData="employeeoperatorview.employeeResultData"></employeeoperatorcontrol>
      </TabPane>
      <TabPane label="已完成">
        <employeeoperatorcontrol :isCperator="3" :customerData="employeeoperatorview.customerData" :sSocialSecurityTypeData="employeeoperatorview.sSocialSecurityTypeData" :employeeResultData="employeeoperatorview.employeeResultData"></employeeoperatorcontrol>
      </TabPane>
      <TabPane label="批退">
        <employeeoperatorcontrol :isCperator="4" :customerData="employeeoperatorview.customerData" :sSocialSecurityTypeData="employeeoperatorview.sSocialSecurityTypeData" :employeeResultData="employeeoperatorview.employeeResultData"></employeeoperatorcontrol>
      </TabPane>
    </Tabs>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import employeeoperatorcontrol from "./employeeoperatorcontrol.vue";
  import EventTypes from '../../store/EventTypes'

  export default {
    components: {employeeoperatorcontrol},
    name:"employeeoperatorview",
    data() {
      return {

      }
    },
    mounted() {
      this.setEmployeeOperatorView()
    },
    computed: {
      ...mapGetters('employeeOperatorView',[
        'employeeoperatorview'
      ])
    },
    methods: {
      ...mapActions('employeeOperatorView',{
        setEmployeeOperatorView: EventTypes.EMPLOYEEOPERATORVIEW
      })
    }
  }
</script>

<style scoped>
  .mt20 {
    margin-top: 20px;
  }
</style>
