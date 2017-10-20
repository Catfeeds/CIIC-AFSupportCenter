<template>
  <approval-step-type-info :prevPage="'companytasklist'"></approval-step-type-info>
</template>
<script>
  import approvalStepTypeInfo from '../commoncontrol/approvalsteptypeinfo.vue'
  export default {
    name: 'companytaskprogresstypeinfo',
    components: {approvalStepTypeInfo},
    data() {
      return {

      }
    }
  }
</script>
