<template>
  <div class="smList">
    <tabs :data="tabPans" @on-click="selectTabPan"></tabs>
  </div>
</template>
<script>
  import tabs from '../../components/tabs.vue'

  export default {
    components: {tabs},
    data() {
      return {
        tabPans:
          [
            {name: 'cthismonthhandle', label: '本月处理'},
            {name: 'cnextmonthhandle', label: '下月处理'},
            {name: 'cfinished', label: '已完成'},
            {name: 'crefused', label: '批退'},
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
