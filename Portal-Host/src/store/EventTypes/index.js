import LeftNavigationType from "./LeftNavigationType";

import EmployeeSocialSecuritySearchType from "./shsocialsecurity/EmployeeSocialSecuritySearchType";
import EmployeeSocialSecurityInfoType from "./shsocialsecurity/EmployeeSocialSecurityInfoType";
import CompanySocialSecurityNewType from "./shsocialsecurity/CompanySocialSecurityNewType";

import EmployeeOperatorViewType from "./employeeoperator/EmployeeOperatorViewType";
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
  ...EmployeeCommcialOperatorType,

  ...EmployeeSpecialOperatorViewType,
  ...EmployeeSpecialProgress2Type,
  ...EmployeeSpecialProgress3Type,

  ...CompanyTaskListType
}

