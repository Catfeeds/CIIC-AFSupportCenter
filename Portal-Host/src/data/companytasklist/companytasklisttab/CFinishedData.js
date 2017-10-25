/**
 * Created by huangye on 2017/10/25.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/cFinished').reply(200, {
  data: {
    customerData: [
      {
        id: 0,
        name: '客户1',
        code: 'KH001',
        nature: '企业',
        type: '国家'
      },
      {
        id: 1,
        name: '客户2',
        code: 'KH002',
        nature: '代表处',
        type: '集体'
      },
      {
        id: 2,
        name: '客户3',
        code: 'KH003',
        nature: '',
        type: '民营'
      },
      {
        id: 3,
        name: '客户4',
        code: 'KH004',
        nature: '',
        type: '合资'
      },
      {
        id: 4,
        name: '客户5',
        code: 'KH005',
        nature: '',
        type: '其他'
      },
      {
        id: 5,
        name: '客户6',
        code: 'KH006',
        nature: '其他',
        type: '其他'
      }
    ],

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
const cFinished = Axios.get('cFinished')

export default {
  cFinished
}
