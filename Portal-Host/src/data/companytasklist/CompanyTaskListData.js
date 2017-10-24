/**
 * Created by huangye on 2017/10/23.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/companyTaskList').reply(200, {
  data: {
    taskData: [
      {
        action: '',
        tid: 'SS_KH_0001',
        type: '开户',
        customerId: '',
        companyCustomer: '客户1',
        finishDate: '2017/06/30',
        sponsor: '中智上海',
        initiator: '前客服1',
        sponsorTime: '2017/06/01 10:05:29',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_ZC_0001',
        type: '开户',
        customerId: '',
        companyCustomer: '客户1',
        finishDate: '2017/06/30',
        sponsor: '中智上海',
        initiator: '前客服1',
        sponsorTime: '2017/06/01 10:05:29',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_XJ_0001',
        type: '转移',
        customerId: '',
        companyCustomer: '客户1',
        finishDate: '2017/06/30',
        sponsor: '中智上海',
        initiator: '前客服1',
        sponsorTime: '2017/06/01 10:05:29',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_ZC_0001',
        type: '转移',
        customerId: '',
        companyCustomer: '客户1',
        finishDate: '2017/06/30',
        sponsor: '中智上海',
        initiator: '前客服1',
        sponsorTime: '2017/06/01 10:05:29',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_BJ_0001',
        type: '变更',
        customerId: '',
        companyCustomer: '客户1',
        finishDate: '2017/06/30',
        sponsor: '中智上海',
        initiator: '前客服1',
        sponsorTime: '2017/06/01 10:05:29',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_ZZ_0001',
        type: '变更',
        customerId: '',
        companyCustomer: '客户1',
        finishDate: '2017/06/30',
        sponsor: '中智上海',
        initiator: '前客服1',
        sponsorTime: '2017/06/01 10:05:29',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_TZ_0001',
        type: '终止',
        customerId: '',
        companyCustomer: '客户1',
        finishDate: '2017/06/30',
        sponsor: '中智上海',
        initiator: '前客服1',
        sponsorTime: '2017/06/01 10:05:29',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_TZ_0001',
        type: '终止',
        customerId: '',
        companyCustomer: '客户1',
        finishDate: '2017/06/30',
        sponsor: '中智上海',
        initiator: '前客服1',
        sponsorTime: '2017/06/01 10:05:29',
        notes: ''
      }
    ]
  }
})
const companyTaskList = Axios.get('companyTaskList')

export default {
  companyTaskList
}
