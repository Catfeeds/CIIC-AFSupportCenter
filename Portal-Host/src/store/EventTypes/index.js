import LeftNavigationType from "./LeftNavigationType";

import EmployeeSocialSecuritySearchType from "./shsocialsecurity/EmployeeSocialSecuritySearchType";
import EmployeeSocialSecurityInfoType from "./shsocialsecurity/EmployeeSocialSecurityInfoType";
import CompanySocialSecurityNewType from "./shsocialsecurity/CompanySocialSecurityNewType";

import EmployeeOperatorViewType from "./employeeoperator/EmployeeOperatorViewType";
import ThisMonthHandleType from "./employeeoperator/employeeoperatortab/ThisMonthHandleType"
import NextMonthHandleType from "./employeeoperator/employeeoperatortab/NextMonthHandleType"
import FinishHandleType from "./employeeoperator/employeeoperatortab/FinishHandleType"
import RefuseHandleType from "./employeeoperator/employeeoperatortab/RefuseHandleType"
import EmployeeCommcialOperatorType from './employeeoperator/EmployeeCommcialOperatorType'

import EmployeeSpecialOperatorViewType from "./employeespecialoperator/EmployeeSpecialOperatorViewType"
import EmployeeSpecialProgress2Type from "./employeespecialoperator/EmployeeSpecialProgress2Type"
import EmployeeSpecialProgress3Type from "./employeespecialoperator/EmployeeSpecialProgress3Type"

import CompanyTaskListType from "./companytasklist/CompanyTaskListType"

export default {
  ...LeftNavigationType,

  ...EmployeeSocialSecuritySearchType,
  ...EmployeeSocialSecurityInfoType,
  ...CompanySocialSecurityNewType,

  ...EmployeeOperatorViewType,
  ...ThisMonthHandleType,
  ...NextMonthHandleType,
  ...FinishHandleType,
  ...RefuseHandleType,
  ...EmployeeCommcialOperatorType,

  ...EmployeeSpecialOperatorViewType,
  ...EmployeeSpecialProgress2Type,
  ...EmployeeSpecialProgress3Type,

  ...CompanyTaskListType
}

