import Vue from 'vue'
import Vuex from 'vuex'

import leftNavigation from './modules/leftNavigation'

import EmployeeSocialSecuritySearch from './modules/shsocialsecurity/EmployeeSocialSecuritySearch'
import EmployeeSocialSecurityInfo from './modules/shsocialsecurity/EmployeeSocialSecurityInfo'
import CompanySocialSecurityNew from './modules/shsocialsecurity/CompanySocialSecurityNew'

import EmployeeOperatorView from './modules/employeeoperator/EmployeeOperatorView'
import ThisMonthHandle from './modules/employeeoperator/employeeoperatortab/ThisMonthHandle'
import NextMonthHandle from './modules/employeeoperator/employeeoperatortab/NextMonthHandle'
import FinishHandle from './modules/employeeoperator/employeeoperatortab/FinishHandle'
import RefuseHandle from './modules/employeeoperator/employeeoperatortab/RefuseHandle'
import EmployeeCommcialOperator from './modules/employeeoperator/EmployeeCommcialOperator'

import EmployeeSpecialOperatorView from './modules/employeespecialoperator/EmployeeSpecialOperatorView'
import NoProgress from './modules/employeespecialoperator/employeespecialoperatortab/NoProgress'
import Progressing from './modules/employeespecialoperator/employeespecialoperatortab/Progressing'
import Finished from './modules/employeespecialoperator/employeespecialoperatortab/Finished'
import Refused from './modules/employeespecialoperator/employeespecialoperatortab/Refused'
import EmployeeSpecialProgress2 from './modules/employeespecialoperator/EmployeeSpecialProgress2'
import EmployeeSpecialProgress3 from './modules/employeespecialoperator/EmployeeSpecialProgress3'

import CThisMonthHandle from './modules/companytasklist/companytasklisttab/CThisMonthHandle'
import CNextMonthHandle from './modules/companytasklist/companytasklisttab/CNextMonthHandle'
import CFinished from './modules/companytasklist/companytasklisttab/CFinished'
import CRefused from './modules/companytasklist/companytasklisttab/CRefused'
import CompanyTaskProgress2 from './modules/companytasklist/CompanyTaskProgress2'
import CompanyTaskProgressTransferInfo from './modules/companytasklist/CompanyTaskProgressTransferInfo'
import CompanyTaskProgressTypeInfo from './modules/companytasklist/CompanyTaskProgressTypeInfo'
import CompanyTaskProgressChangeInfo from './modules/companytasklist/CompanyTaskProgressChangeInfo'
import CompanyTaskProgressSendInfo from './modules/companytasklist/CompanyTaskProgressSendInfo'

import CompanySocialSecurityManage from './modules/companysocialsecuritymanage/CompanySocialSecurityManage'
import CompanySocialSecurity from './modules/companysocialsecuritymanage/CompanySocialSecurity'
import CompanySocialSecurityProgress2 from './modules/companysocialsecuritymanage/CompanySocialSecurityProgress2'
import CompanySocialSecurityChangeInfo from './modules/companysocialsecuritymanage/CompanySocialSecurityChangeInfo'
import CompanySocialSecurityEndInfo from './modules/companysocialsecuritymanage/CompanySocialSecurityEndInfo'

import SocialSecurityPay from './modules/socialsecuritypay/SocialSecurityPay'
import PaymentNotice from './modules/socialsecuritypay/PaymentNotice'

Vue.use(Vuex)

export default new Vuex.Store({
  modules : {
    leftNaviModule : leftNavigation,

    employeeSocialSecuritySearch: EmployeeSocialSecuritySearch,
    employeeSocialSecurityInfo: EmployeeSocialSecurityInfo,
    companySocialSecurityNew: CompanySocialSecurityNew,
    employeeSocialSecurityNew: CompanySocialSecurityNew,

    employeeOperatorView: EmployeeOperatorView,
    thisMonthHandle: ThisMonthHandle,
    nextMonthHandle: NextMonthHandle,
    finishHandle: FinishHandle,
    refuseHandle: RefuseHandle,
    employeeCommcialOperator: EmployeeCommcialOperator,

    employeeSpecialOperatorView: EmployeeSpecialOperatorView,
    noProgress: NoProgress,
    Progressing: Progressing,
    Finished: Finished,
    Refused: Refused,
    employeeSpecialProgress2: EmployeeSpecialProgress2,
    employeeSpecialProgress3: EmployeeSpecialProgress3,

    cThisMonthHandle: CThisMonthHandle,
    cNextMonthHandle: CNextMonthHandle,
    cFinished: CFinished,
    cRefused: CRefused,
    companyTaskProgress2: CompanyTaskProgress2,
    companyTaskProgressTransferInfo: CompanyTaskProgressTransferInfo,
    companyTaskProgressTypeInfo: CompanyTaskProgressTypeInfo,
    companyTaskProgressChangeInfo: CompanyTaskProgressChangeInfo,
    companyTaskProgressSendInfo: CompanyTaskProgressSendInfo,

    companySocialSecurityManage: CompanySocialSecurityManage,
    companySocialSecurity: CompanySocialSecurity,
    companySocialSecurityProgress2: CompanySocialSecurityProgress2,
    companySocialSecurityChangeInfo: CompanySocialSecurityChangeInfo,
    companySocialSecurityEndInfo: CompanySocialSecurityEndInfo,

    socialSecurityPay: SocialSecurityPay,
    paymentNotice: PaymentNotice,
  }
});
