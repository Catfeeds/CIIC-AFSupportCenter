<template>
  <div class="smList">
    <tabs :data="tabPans" :value="currentValue" @on-click="selectTabPan"></tabs>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import EventTypes from '../../store/EventTypes'
  import tabs from '../../components/tabs.vue'

  export default {
    components: {tabs},
    props: {
      data: {
        require: true,
        type: Array
      }
    },
    data() {
      return {
        currentValue: 'thismonthhandle',
        tabPans:
          [
            {name: 'thismonthhandle', label: '本月处理'},
            {name: 'nextmonthhandle', label: '下月处理'},
            {name: 'finishhandle', label: '已完成'},
            {name: 'refusehandle', label: '批退'},
          ]
      }
    },
    mounted() {

    },
    computed: {

    },
    methods: {
      selectTabPan(name) {

      }
    }
  }
</script>

<style scoped>
  .mt20 {
    margin-top: 20px;
  }
</style>
