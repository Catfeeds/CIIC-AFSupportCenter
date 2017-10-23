// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import iView from 'iview'
import Axios from 'axios'
import VueAxios from 'vue-axios'
import commons from "./lib/commons";


import 'iview/dist/styles/iview.css';
import '../static/css/style.css';   //修改全局樣式

import store from './store'

Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(iView);
Vue.use(commons);
Vue.use(VueAxios,Axios);

const app = new Vue({
    router,
    store,
    render: h => h(App)
})

app.$mount('#app');
