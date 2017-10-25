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
            {name: 'noprogress', label: '未处理'},
            {name: 'progressing', label: '处理中'},
            {name: 'finished', label: '已完成'},
            {name: 'refused', label: '已批退'},
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
