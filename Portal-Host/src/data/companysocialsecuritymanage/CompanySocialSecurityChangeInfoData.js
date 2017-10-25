/**
 * Created by huangye on 2017/10/25.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/companySocialSecurityChangeInfo').reply(200, {
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
    chatList: [
      {
        icon: '#',
        name: '客服',
        date: '2017-03-02 14:14:32',
        content: '【发起】该客户要求本月所有员工都缴纳社保。'
      },
    ],
  }
})
const companySocialSecurityChangeInfo = Axios.get('companySocialSecurityChangeInfo')

export default {
  companySocialSecurityChangeInfo
}
