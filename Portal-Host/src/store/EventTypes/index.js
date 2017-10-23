import LeftNavigationType from "./LeftNavigationType";
import EmployeeOperatorViewType from "./employeeoperator/EmployeeOperatorViewType";

import CompanySocialSecurityNewType from "./shsocialsecurity/CompanySocialSecurityNewType";
import EmployeeSocialSecurityInfoType from "./shsocialsecurity/EmployeeSocialSecurityInfoType";
import EmployeeSocialSecuritySearchType from "./shsocialsecurity/EmployeeSocialSecuritySearchType";

export default {
  ...LeftNavigationType,

  ...EmployeeOperatorViewType,

  ...CompanySocialSecurityNewType,
  ...EmployeeSocialSecurityInfoType,
  ...EmployeeSocialSecuritySearchType,
}

