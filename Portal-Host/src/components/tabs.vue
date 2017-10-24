<template>
  <div>
    <Tabs @on-click="selectTab" :value="value" :animated="false">
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
        require: true,
        type: Array
      },
      value: {
        require: true,
        type: String
      }
    },
    data() {
      return {
        tabPans: this.data,
      }
    },
    mounted() {

    },
    computed: {

    },
    methods: {
      selectTab(name) {
        this.$router.push({name: name, params: {tabpan: name}})
      }
    },
    watch: {
//      value(newValue) {
//        console.log("newValue = " + newValue)
//        this.currentValue = newValue
//      },
//      currentValue(newValue) {
//        this.$emit('input', newValue)
//      }
    },
  }
</script>

<style scoped>
  .mt20 {
    margin-top: 20px;
  }
</style>
