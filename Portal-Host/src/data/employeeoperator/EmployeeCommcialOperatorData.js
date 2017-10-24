/**
 * Created by huangye on 2017/10/23.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/employeeCommcialOperator').reply(200, {
  data: {
    operatorTableNewData: [
      {
        action: '移出',
        tid: 'SOC_XJ_000001',
        customerNumber: '1001',
        customerName: '客户1',
        employeeName: '雇员1',
        checkDate: '2017-01-06',
        personType: '本地',
        handleMethod: '',
        socialSecurityAccountType: '',
        task: '',
        socialSecurityIndex: '',
        base: '',
        socialSecurityStartMonth: '',
        socialSecurityEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000002',
        customerNumber: '1002',
        customerName: '客户2',
        employeeName: '雇员2',
        checkDate: '2017-01-06',
        personType: '外地',
        handleMethod: '',
        socialSecurityAccountType: '',
        task: '',
        socialSecurityIndex: '',
        base: '',
        socialSecurityStartMonth: '',
        socialSecurityEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000003',
        customerNumber: '1003',
        customerName: '客户1',
        employeeName: '雇员3',
        checkDate: '2017-01-06',
        personType: '外籍五险',
        handleMethod: '',
        socialSecurityAccountType: '',
        task: '',
        socialSecurityIndex: '',
        base: '',
        socialSecurityStartMonth: '',
        socialSecurityEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000003',
        customerNumber: '1004',
        customerName: '客户1',
        employeeName: '雇员4',
        checkDate: '2017-01-06',
        personType: '外籍三险',
        handleMethod: '',
        socialSecurityAccountType: '',
        task: '',
        socialSecurityIndex: '',
        base: '',
        socialSecurityStartMonth: '',
        socialSecurityEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000003',
        customerNumber: '1005',
        customerName: '客户1',
        employeeName: '雇员5',
        checkDate: '2017-01-06',
        personType: '本地',
        handleMethod: '',
        socialSecurityAccountType: '',
        task: '',
        socialSecurityIndex: '',
        base: '',
        socialSecurityStartMonth: '',
        socialSecurityEndMonth: '',
        notes: ''
      }
    ],

    operatorTableRepairData: [
      {
        action: '移出',
        tid: 'SOC_XJ_000001',
        customerNumber: '1001',
        customerName: '客户1',
        employeeName: '雇员1',
        checkDate: '2017-01-06',
        personType: '本地',
        handleMethod: '',
        repairDoMonth: '',
        repairBase: '',
        repairStartMonth: '',
        repairEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000002',
        customerNumber: '1002',
        customerName: '客户2',
        employeeName: '雇员2',
        checkDate: '2017-01-06',
        personType: '外地',
        handleMethod: '',
        repairDoMonth: '',
        repairBase: '',
        repairStartMonth: '',
        repairEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000003',
        customerNumber: '1003',
        customerName: '客户1',
        employeeName: '雇员3',
        checkDate: '2017-01-06',
        personType: '外籍五险',
        handleMethod: '',
        repairDoMonth: '',
        repairBase: '',
        repairStartMonth: '',
        repairEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000003',
        customerNumber: '1004',
        customerName: '客户1',
        employeeName: '雇员4',
        checkDate: '2017-01-06',
        personType: '外籍三险',
        handleMethod: '',
        repairDoMonth: '',
        repairBase: '',
        repairStartMonth: '',
        repairEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000003',
        customerNumber: '1005',
        customerName: '客户1',
        employeeName: '雇员5',
        checkDate: '2017-01-06',
        personType: '本地',
        handleMethod: '',
        repairDoMonth: '',
        repairBase: '',
        repairStartMonth: '',
        repairEndMonth: '',
        notes: ''
      }
    ],

    operatorTableChangeData: [
      {
        action: '移出',
        tid: 'SOC_XJ_000001',
        customerNumber: '1001',
        customerName: '客户1',
        employeeName: '雇员1',
        checkDate: '2017-01-06',
        personType: '本地',
        handleMethod: '',
        changeMonth: '',
        changeBase: '',
        changeStartMonth: '',
        changeEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000002',
        customerNumber: '1002',
        customerName: '客户2',
        employeeName: '雇员2',
        checkDate: '2017-01-06',
        personType: '外地',
        handleMethod: '',
        changeMonth: '',
        changeBase: '',
        changeStartMonth: '',
        changeEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000003',
        customerNumber: '1003',
        customerName: '客户1',
        employeeName: '雇员3',
        checkDate: '2017-01-06',
        personType: '外籍五险',
        handleMethod: '',
        changeMonth: '',
        changeBase: '',
        changeStartMonth: '',
        changeEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000003',
        customerNumber: '1004',
        customerName: '客户1',
        employeeName: '雇员4',
        checkDate: '2017-01-06',
        personType: '外籍三险',
        handleMethod: '',
        changeMonth: '',
        changeBase: '',
        changeStartMonth: '',
        changeEndMonth: '',
        notes: ''
      },
      {
        action: '移出',
        tid: 'SOC_XJ_000003',
        customerNumber: '1005',
        customerName: '客户1',
        employeeName: '雇员5',
        checkDate: '2017-01-06',
        personType: '本地',
        handleMethod: '',
        changeMonth: '',
        changeBase: '',
        changeStartMonth: '',
        changeEndMonth: '',
        notes: ''
      }
    ],

    operatorTableOutData: [
      {action: '移出', tid: 'SOC_XJ_000001', companyName: '1001', employeeName: '雇员1', checkOutDate: '2017-01-06', handleMethod: '', endMonth: '', notes: ''},
      {action: '移出', tid: 'SOC_XJ_000002', companyName: '1002', employeeName: '雇员2', checkOutDate: '2017-01-06', handleMethod: '', endMonth: '', notes: ''},
      {action: '移出', tid: 'SOC_XJ_000003', companyName: '1003', employeeName: '雇员3', checkOutDate: '2017-01-06', handleMethod: '', endMonth: '', notes: ''},
      {action: '移出', tid: 'SOC_XJ_000003', companyName: '1004', employeeName: '雇员4', checkOutDate: '2017-01-06', handleMethod: '', endMonth: '', notes: ''},
      {action: '移出', tid: 'SOC_XJ_000003', companyName: '1005', employeeName: '雇员5', checkOutDate: '2017-01-06', handleMethod: '', endMonth: '', notes: ''}
    ],
  }
})
const employeeCommcialOperator = Axios.get('employeeCommcialOperator')

export default {
  employeeCommcialOperator
}
