/**
 * Created by huangye on 2017/10/23.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/companySocialSecurityNew').reply(200, {
  data: {
    company: {
      companySocialSecurityAccount: '12123235',
      companyNumber: '340078',
      companyName: '上海XX信息技术有限公司',
      socialSecurityCenter: '徐汇',
      uKey: '152243',
      accountType: '独立户',
      companySocialSecurityState: '有效\\封存\\终止',
      companySocialSecurityEndData: '本月18日',
      companyServicer: '彭XX',
      serviceCenter: '大客户2'
    }, //企业社保账户信息

    employee: {
      code: 'GY0001',
      name: '张三',
      idNo: '35220219781124114',
      checkInTime: '2017-1-1',
      education: '本科',
      socialSecurityIndex: '123235564',
      companySocialSecurityAccount: '中智大库',
      residenceAddress: 'XXX路X号xxx',
      contactAddress: 'XXX路X号xxx',
      personType: '外地',
      socialSecurityType: '中智大库'
    }, //雇员信息

    taskNewInfo: {
      afDate: '2017-05-06',
      storePlace: '外来从业人员',
      jobState: '已用工',
      jobDate: '2017-06-1'
    },
    taskNewInfoData: [
      {base: '19000', startMonth: '201701', endYear: ''},
      {base: '', startMonth: '', endYear: ''}
    ], //任务单参考信息 -- 新增

    taskChangeInfo: {
      newPay: '19000',
      changeStartMonth: '201701',
      changeEndMonth: '201706'
    }, //任务单参考信息 -- 调整

    taskOutInfo: {
      state: '已退工',
      leaveDate: '2017-6-24',
      socialSecurityEndDate: '201706'
    }, //任务单参考信息 -- 退工
  }
})
const companySocialSecurityNew = Axios.get('companySocialSecurityNew')

export default {
  companySocialSecurityNew
}
