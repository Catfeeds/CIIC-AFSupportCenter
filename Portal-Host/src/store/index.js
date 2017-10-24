import Vue from 'vue'
import Vuex from 'vuex'

import leftNavigation from './modules/leftNavigation'

import EmployeeSocialSecuritySearch from './modules/shsocialsecurity/EmployeeSocialSecuritySearch'
import EmployeeSocialSecurityInfo from './modules/shsocialsecurity/EmployeeSocialSecurityInfo'
import CompanySocialSecurityNew from './modules/shsocialsecurity/CompanySocialSecurityNew'

import EmployeeOperatorView from './modules/employeeoperator/EmployeeOperatorView'
import EmployeeCommcialOperator from './modules/employeeoperator/EmployeeCommcialOperator'

import EmployeeSpecialOperatorView from './modules/employeespecialoperator/EmployeeSpecialOperatorView'
import EmployeeSpecialProgress2 from './modules/employeespecialoperator/EmployeeSpecialProgress2'
import EmployeeSpecialProgress3 from './modules/employeespecialoperator/EmployeeSpecialProgress3'

import CompanyTaskList from './modules/companytasklist/CompanyTaskList'

Vue.use(Vuex)

export default new Vuex.Store({
  modules : {
    leftNaviModule : leftNavigation,

    employeeSocialSecuritySearch: EmployeeSocialSecuritySearch,
    employeeSocialSecurityInfo: EmployeeSocialSecurityInfo,
    employeeSocialSecurityNew: CompanySocialSecurityNew,

    employeeOperatorView: EmployeeOperatorView,
    employeeCommcialOperator: EmployeeCommcialOperator,

    employeeSpecialOperatorView: EmployeeSpecialOperatorView,
    employeeSpecialProgress2: EmployeeSpecialProgress2,
    employeeSpecialProgress3: EmployeeSpecialProgress3,

    companyTaskList: CompanyTaskList
  }
});
