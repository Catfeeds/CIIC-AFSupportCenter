<template>
  <div>
    <Tabs>
      <TabPane label="未处理">
        <employeespecialoperatorcontrol :isCperator="1" :customerData="employeespecialoperatorview.customerData" :sSocialSecurityTypeData="employeespecialoperatorview.sSocialSecurityTypeData" :employeeResultData="employeespecialoperatorview.employeeResultData"></employeespecialoperatorcontrol>
      </TabPane>
      <TabPane label="处理中">
        <employeespecialoperatorcontrol :isCperator="2" :customerData="employeespecialoperatorview.customerData" :sSocialSecurityTypeData="employeespecialoperatorview.sSocialSecurityTypeData" :employeeResultData="employeespecialoperatorview.employeeResultData"></employeespecialoperatorcontrol>
      </TabPane>
      <TabPane label="已完成">
        <employeespecialoperatorcontrol :isCperator="3" :customerData="employeespecialoperatorview.customerData" :sSocialSecurityTypeData="employeespecialoperatorview.sSocialSecurityTypeData" :employeeResultData="employeespecialoperatorview.employeeResultData"></employeespecialoperatorcontrol>
      </TabPane>
      <TabPane label="已批退">
        <employeespecialoperatorcontrol :isCperator="4" :customerData="employeespecialoperatorview.customerData" :sSocialSecurityTypeData="employeespecialoperatorview.sSocialSecurityTypeData" :employeeResultData="employeespecialoperatorview.employeeResultData"></employeespecialoperatorcontrol>
      </TabPane>
    </Tabs>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import employeespecialoperatorcontrol from "./employeespecialoperatorcontrol.vue";
  import eventType from '../../store/EventTypes'

  export default {
    components: {employeespecialoperatorcontrol},
    name:"employeespecialoperatorview",
    data() {
      return {

      }
    },
    mounted() {
      this.setEmployeeSpecialOperatorView()
    },
    computed: {
      ...mapGetters([
        'employeespecialoperatorview'
      ])
    },
    methods: {
      ...mapActions({
        setEmployeeSpecialOperatorView: eventType.EMPLOYEESPECIALOPERATORVIEW
      })
    }
  }
</script>
