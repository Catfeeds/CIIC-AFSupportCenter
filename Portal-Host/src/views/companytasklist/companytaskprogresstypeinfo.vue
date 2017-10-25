<template>
  <approval-step-type-info :prevPage="'companytasklist'"></approval-step-type-info>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import approvalStepTypeInfo from '../commoncontrol/approvalsteptypeinfo.vue'
  import eventType from '../../store/EventTypes'

  export default {
    components: {approvalStepTypeInfo},
    data() {
      return {

      }
    },
    mounted() {
      this.setCompanyTaskProgressTypeInfo()
    },
    computed: {
      ...mapGetters('companyTaskProgressTypeInfo',[
        'companytaskprogresstypeinfo'
      ])
    },
    methods: {
      ...mapActions('companyTaskProgressTypeInfo', {
        setCompanyTaskProgressTypeInfo: eventType.COMPANYTASKPROGRESSTYPEINFOTYPE
      }),
    }
  }
</script>
