import Vue from 'vue'
import Vuex from 'vuex'

import * as index from './modules/index'
Vue.use(Vuex)

const store = new Vuex.Store({
  modules : {
    leftNaviModule : index.leftNaviStore,
    payrollDataSourceImportModule:index.payrollDataSourceImportStore,
    payrollTaskSubmitModule:index.payrollTaskSubmitStore,
    payrollEmployeeModule:index.payrollEmployeeStore,
    addEmployeeModule:index.addEmployeeStore
  }
});

export default store;
