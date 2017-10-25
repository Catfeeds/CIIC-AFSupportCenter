/**
 * Created by huangye on 2017/10/25.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/socialSecurityPay').reply(200, {
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

    changeData: [
      {
        shouldPayAmount: '80012.00',
        deductibleFee: '323.3'
      }
    ],

    payData: [
      {
        id: 0,
        customerNumber: '1001',
        customerName: '欧莱雅分公司1',
        companySocialSecurityAccount: '9910001',
        payDate: '201706',
        payState: '申请中(财务审批中)',
        operator: '',
        shouldPayAmount: '20000.20',
        applyPayAmount: '20500.00',
        changeAmount: '500',
        deductibleFee: '',
        companyPart: '',
        employeePart: '',
        applier: '后道1',
        applyTime: '2017/06/01 10:05:29',
        payDate: '',
        applyNotes: ''
      },
      {
        id: 1,
        customerNumber: '1002',
        customerName: '欧莱雅分公司2',
        companySocialSecurityAccount: '9910001',
        payDate: '201706',
        payState: '批退',
        operator: '',
        shouldPayAmount: '',
        applyPayAmount: '',
        changeAmount: '',
        deductibleFee: '',
        companyPart: '',
        employeePart: '',
        applier: '后道1',
        applyTime: '2017/06/01 10:05:29',
        payDate: '',
        applyNotes: ''
      },
      {
        id: 2,
        customerNumber: '1003',
        customerName: '欧莱雅分公司3',
        companySocialSecurityAccount: '9910001',
        payDate: '201706',
        payState: '支付成功',
        operator: '',
        shouldPayAmount: '5000.30',
        applyPayAmount: '4700.30',
        changeAmount: '-300',
        deductibleFee: '',
        companyPart: '',
        employeePart: '',
        applier: '后道1',
        applyTime: '2017/06/01 10:05:29',
        payDate: '',
        applyNotes: ''
      },
      {
        id: 3,
        customerNumber: '',
        customerName: '',
        companySocialSecurityAccount: '中智大库账号',
        payDate: '201706',
        payState: '申请中(内部审批中)',
        operator: '',
        shouldPayAmount: '',
        applyPayAmount: '',
        changeAmount: '',
        deductibleFee: '',
        companyPart: '',
        employeePart: '',
        applier: '后道1',
        applyTime: '2017/06/01 10:05:29',
        payDate: '',
        applyNotes: ''
      },
      {
        id: 4,
        customerNumber: '1005',
        customerName: '独立户客户',
        companySocialSecurityAccount: '910003',
        payDate: '201706',
        payState: '申请中(内部审批中)',
        operator: '',
        shouldPayAmount: '',
        applyPayAmount: '',
        changeAmount: '',
        deductibleFee: '',
        companyPart: '',
        employeePart: '',
        applier: '后道1',
        applyTime: '2017/06/01 10:05:29',
        payDate: '',
        applyNotes: ''
      },
    ]
  }
})
const socialSecurityPay = Axios.get('socialSecurityPay')

export default {
  socialSecurityPay
}
