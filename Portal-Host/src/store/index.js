import Vue from 'vue'
import Vuex from 'vuex'

import leftNavigation from './modules/leftNavigation'

import EmployeeSocialSecuritySearch from './modules/shsocialsecurity/EmployeeSocialSecuritySearch'
import EmployeeSocialSecurityInfo from './modules/shsocialsecurity/EmployeeSocialSecurityInfo'
import CompanySocialSecurityNew from './modules/shsocialsecurity/CompanySocialSecurityNew'

import EmployeeOperatorView from './modules/employeeoperator/EmployeeOperatorView'

Vue.use(Vuex)

export default new Vuex.Store({
  modules : {
    leftNaviModule : leftNavigation,

    employeeSocialSecuritySearch: EmployeeSocialSecuritySearch,
    employeeSocialSecurityInfo: EmployeeSocialSecurityInfo,
    companySocialSecurityNew: CompanySocialSecurityNew,

    employeeOperatorView: EmployeeOperatorView,
  }
});
