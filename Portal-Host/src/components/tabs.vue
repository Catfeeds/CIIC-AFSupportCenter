<template>
  <div>
    <Tabs @on-click="selectTab" :value="currentValue" :animated="false">
      <TabPane :label="item.label" :name="item.name" v-for="item in tabPans" :key="item.name"></TabPane>
    </Tabs>
    <transition name="fade" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>
<script>
  export default {
    name: 'tabs',
    props: {
      data: {
        type: Array,
        default() {
          return []
        }
      }
    },
    data() {
      return {
        tabPans: this.data,
        currentValue: this.$route.params.tabpan || this.data[0]['name']
      }
    },
    mounted() {
      this.selectTab(this.currentValue)
    },
    computed: {

    },
    methods: {
      selectTab(name) {
        this.$router.push({name: name, params: {tabpan: name}})
        this.$emit('on-click', name)
      }
    }
  }
</script>

<style scoped>
  .mt20 {
    margin-top: 20px;
  }
</style>
