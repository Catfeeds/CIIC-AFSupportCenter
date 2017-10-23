import Vue from 'vue'
import Vuex from 'vuex'

import * as index from './modules/index'
Vue.use(Vuex)

const store = new Vuex.Store({
  modules : {
    leftNaviModule : index.leftNaviStore,

    employeeSocialSecuritySearch: index.employeeSocialSecuritySearch,
    employeeSocialSecurityInfo: index.employeeSocialSecurityInfo,
    employeeSocialSecurityNew: index.companySocialSecurityNew,

    employeeOperatorView: index.employeeOperatorView
  }
});

export default store;
