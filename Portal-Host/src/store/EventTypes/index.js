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
import NoProgressType from "./employeespecialoperator/employeespecialoperatortab/NoProgressType"
import ProgressingType from "./employeespecialoperator/employeespecialoperatortab/ProgressingType"
import FinishedType from "./employeespecialoperator/employeespecialoperatortab/FinishedType"
import RefusedType from "./employeespecialoperator/employeespecialoperatortab/RefusedType"
import EmployeeSpecialProgress2Type from "./employeespecialoperator/EmployeeSpecialProgress2Type"
import EmployeeSpecialProgress3Type from "./employeespecialoperator/EmployeeSpecialProgress3Type"

import CThisMonthHandleType from "./companytasklist/companytasklisttab/CThisMonthHandleType"
import CNextMonthHandleType from "./companytasklist/companytasklisttab/CNextMonthHandleType"
import CFINISHEDTYPE from "./companytasklist/companytasklisttab/CFINISHEDTYPE"
import CREFUSEDTYPE from "./companytasklist/companytasklisttab/CRefusedType"
import COMPANYTASKPROGRESS2TYPE from "./companytasklist/CompanyTaskProgress2Type"
import COMPANYTASKPROGRESSTRANSFERINFOTYPE from "./companytasklist/CompanyTaskProgressTransferInfoType"
import COMPANYTASKPROGRESSTYPEINFOTYPE from "./companytasklist/CompanyTaskProgressTypeInfoType"
import COMPANYTASKPROGRESSCHANGEINFOTYPE from "./companytasklist/CompanyTaskProgressChangeInfoType"
import COMPANYTASKPROGRESSSENDINFOTYPE from "./companytasklist/CompanyTaskProgressSendInfoType"

import COMPANYSOCIALSECURITYMANAGETYPE from "./companysocialsecuritymanage/CompanySocialSecurityManageType"
import COMPANYSOCIALSECURITYTYPE from "./companysocialsecuritymanage/CompanySocialSecurityType"
import COMPANYSOCIALSECURITYPROGRESS2TYPE from "./companysocialsecuritymanage/CompanySocialSecurityProgress2Type"
import COMPANYSOCIALSECURITYCHANGEINFOTYPE from "./companysocialsecuritymanage/CompanySocialSecurityChangeInfoType"
import COMPANYSOCIALSECURITYENDINFOTYPE from "./companysocialsecuritymanage/CompanySocialSecurityEndInfoType"

import SOCIALSECURITYPAYTYPE from "./socialsecuritypay/SocialSecurityPayType"
import PAYMENTNOTICETYPE from "./socialsecuritypay/PaymentNoticeType"

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
  ...NoProgressType,
  ...ProgressingType,
  ...FinishedType,
  ...RefusedType,
  ...EmployeeSpecialProgress2Type,
  ...EmployeeSpecialProgress3Type,

  ...CThisMonthHandleType,
  ...CNextMonthHandleType,
  ...CFINISHEDTYPE,
  ...CREFUSEDTYPE,
  ...COMPANYTASKPROGRESS2TYPE,
  ...COMPANYTASKPROGRESSTRANSFERINFOTYPE,
  ...COMPANYTASKPROGRESSTYPEINFOTYPE,
  ...COMPANYTASKPROGRESSCHANGEINFOTYPE,
  ...COMPANYTASKPROGRESSSENDINFOTYPE,

  ...COMPANYSOCIALSECURITYMANAGETYPE,
  ...COMPANYSOCIALSECURITYTYPE,
  ...COMPANYSOCIALSECURITYPROGRESS2TYPE,
  ...COMPANYSOCIALSECURITYCHANGEINFOTYPE,
  ...COMPANYSOCIALSECURITYENDINFOTYPE,

  ...SOCIALSECURITYPAYTYPE,
  ...PAYMENTNOTICETYPE
}

