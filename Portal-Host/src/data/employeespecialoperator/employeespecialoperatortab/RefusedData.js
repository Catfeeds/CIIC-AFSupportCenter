/**
 * Created by huangye on 2017/10/25.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/refused').reply(200, {
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
        type: '终止',
        emergency: '',
        employee: '雇员9',
        employeeId: 'GY009',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: 'KH001',
        companyCustomer: '客户1',
        finishDate: '',
        serviceCenter: '大客户',
        serviceManager: '',
        employeeType: '本地',
        accountType: '独立户客户1',
        region: '徐汇',
        sponsor: '中智上海',
        initiator: '前客服1',
        sponsorTime: '2017/06/01 10:05:29',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_KH_0001',
        type: '终止',
        emergency: '',
        employee: '雇员0',
        employeeId: 'GY000',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: 'KH001',
        companyCustomer: '客户1',
        finishDate: '',
        serviceCenter: '日本',
        serviceManager: '',
        employeeType: '外地',
        accountType: '独立户客户1',
        region: '长宁',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_XJ_0001',
        type: '退账',
        emergency: '是',
        employee: '雇员1',
        employeeId: 'GY001',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: 'KH003',
        companyCustomer: '客户3',
        finishDate: '',
        serviceCenter: '虹桥',
        serviceManager: '',
        employeeType: '外籍三险',
        accountType: '中智外包',
        region: '徐汇',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_ZC_0001',
        type: '退账',
        emergency: '',
        employee: '雇员2',
        employeeId: 'GY002',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: 'KH003',
        companyCustomer: '客户3',
        finishDate: '',
        serviceCenter: '浦东',
        serviceManager: '',
        employeeType: '外籍五险',
        accountType: '中智大库',
        region: '徐汇',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_BJ_0001',
        type: '退账',
        emergency: '',
        employee: '雇员3',
        employeeId: 'GY003',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: 'KH003',
        companyCustomer: '客户3',
        finishDate: '',
        serviceCenter: '浦东',
        serviceManager: '',
        employeeType: '本地',
        accountType: '中智大库',
        region: '徐汇',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_ZZ_0001',
        type: '外籍人提取',
        emergency: '',
        employee: '雇员4',
        employeeId: 'GY004',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: 'KH003',
        companyCustomer: '客户3',
        finishDate: '',
        serviceCenter: '虹桥',
        serviceManager: '',
        employeeType: '外地',
        accountType: '中智外包',
        region: '徐汇',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      },
      {
        action: '',
        tid: 'SS_TQ_0001',
        type: '特殊操作',
        emergency: '',
        employee: '雇员8',
        employeeId: 'GY008',
        employeeCardNumber: '',
        companySocialSecurityAccount: '',
        uKey: '',
        doDate: '',
        customerId: 'KH003',
        companyCustomer: '客户3',
        finishDate: '',
        serviceCenter: '虹桥',
        serviceManager: '',
        employeeType: '外地',
        accountType: '中智外包',
        region: '徐汇',
        sponsor: '',
        initiator: '',
        sponsorTime: '',
        notes: ''
      }
    ]
  }
})
const refused = Axios.get('refused')

export default {
  refused
}
