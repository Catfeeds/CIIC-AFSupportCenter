import Vue from 'vue'
import Vuex from 'vuex'

import * as index from './modules/index'
Vue.use(Vuex)

const store = new Vuex.Store({
  modules : {
    leftNaviModule : index.leftNaviStore
  }
});

export default store;
