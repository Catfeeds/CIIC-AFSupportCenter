/**
 * Created by huangye on 2017/10/25.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/companySocialSecurityManage').reply(200, {
  data: {
    accountManageData: [
      {
        action: '',
        pensionCompanyName: '中智上海经济技术合作公司',
        accountType: '中智大库',
        state: '有效',
        companySocialSecurityAccount: '',
        checkInDate: '2017-06-30',
        endDate: '2017-09-30',
        openHandler: '',
        openHandleDate: '',
        notes: ''
      },
      {
        action: '',
        pensionCompanyName: '上海中智项目外包咨询服务有限公司',
        accountType: '中智外包',
        state: '有效',
        companySocialSecurityAccount: '',
        checkInDate: '',
        endDate: '',
        openHandler: '',
        openHandleDate: '',
        notes: ''
      },
      {
        action: '',
        pensionCompanyName: 'xx有限公司1',
        accountType: '独立户',
        state: '有效',
        companySocialSecurityAccount: '',
        checkInDate: '',
        endDate: '',
        openHandler: '',
        openHandleDate: '',
        notes: ''
      },
      {
        action: '',
        pensionCompanyName: 'xx有限公司2',
        accountType: '独立户',
        state: '有效',
        companySocialSecurityAccount: '',
        checkInDate: '',
        endDate: '',
        openHandler: '',
        openHandleDate: '',
        notes: ''
      },
      {
        action: '',
        pensionCompanyName: 'xx有限公司3',
        accountType: '独立户',
        state: '有效',
        companySocialSecurityAccount: '',
        checkInDate: '',
        endDate: '',
        openHandler: '',
        openHandleDate: '',
        notes: ''
      },
      {
        action: '',
        pensionCompanyName: 'xx有限公司4',
        accountType: '独立户',
        state: '有效',
        companySocialSecurityAccount: '',
        checkInDate: '',
        endDate: '',
        openHandler: '',
        openHandleDate: '',
        notes: ''
      },
      {
        action: '',
        pensionCompanyName: 'xx有限公司5',
        accountType: '独立户',
        state: '有效',
        companySocialSecurityAccount: '',
        checkInDate: '',
        endDate: '',
        openHandler: '',
        openHandleDate: '',
        notes: ''
      }
    ]
  }
})
const companySocialSecurityManage = Axios.get('companySocialSecurityManage')

export default {
  companySocialSecurityManage
}
