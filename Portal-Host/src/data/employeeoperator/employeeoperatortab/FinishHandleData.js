/**
 * Created by huangye on 2017/10/24.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/finishHandle').reply(200, {
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

    sSocialSecurityTypeData: [
      {
        id: '34235329',
        name: '中智大库'
      },
      {
        id: '23434324',
        name: '中智外包'
      },
      {
        id: '56565659',
        name: '独立户-欧莱雅'
      },
      {
        id: '34543543',
        name: '独立户-迅达电梯'
      }
    ],

    employeeResultData: [
      {
        action: '',
        tid: 'SS_KH_0001',
        type: '新进转入',
        emergency: '是',
        employee: '雇员9',
        employeeId: 'GY009',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: '',
        customerName: '客户1',
        finishDate: '',
        sponsor: '中智上海',
        initiator: '前客服1',
        sponsorTime: '2017/06/01 10:05:29',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_ZC_0001',
        type: '新进转入',
        emergency: '',
        employee: '雇员0',
        employeeId: 'GY000',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: '',
        customerName: '客户1',
        finishDate: '',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_XJ_0001',
        type: '新进转入',
        emergency: '是',
        employee: '雇员1',
        employeeId: 'GY001',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: '',
        customerName: '客户3',
        finishDate: '',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_ZC_0001',
        type: '调整',
        emergency: '',
        employee: '雇员2',
        employeeId: 'GY002',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: '',
        customerName: '客户3',
        finishDate: '',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_BJ_0001',
        type: '补缴',
        emergency: '是',
        employee: '雇员3',
        employeeId: 'GY003',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: '',
        customerName: '客户3',
        finishDate: '',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_ZZ_0001',
        type: '转出',
        emergency: '',
        employee: '雇员4',
        employeeId: 'GY004', employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: '',
        customerName: '客户3',
        finishDate: '',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_TZ_0001',
        type: '转出',
        emergency: '',
        employee: '雇员5',
        employeeId: 'GY005',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: '',
        customerName: '客户3',
        finishDate: '',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_TZ_0001',
        type: '转出',
        emergency: '是',
        employee: '雇员6',
        employeeId: 'GY006',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: '',
        customerName: '客户3',
        finishDate: '',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_TQ_0001',
        type: '转出',
        emergency: '',
        employee: '雇员8',
        employeeId: 'GY008',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: '',
        customerName: '客户3',
        finishDate: '',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      }
    ]
  }
})
const finishHandle = Axios.get('finishHandle')

export default {
  finishHandle
}
