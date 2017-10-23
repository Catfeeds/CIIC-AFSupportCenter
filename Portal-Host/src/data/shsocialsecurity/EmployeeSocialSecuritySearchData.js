/**
 * Created by huangye on 2017/10/20.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/employeeSocialSecuritySearch').reply(200, {
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

    employeeSocialSecurityData: [
      {
        enumber: '10001',
        ename: '雇员1',
        eidno: '',
        estate: '已做',
        eservice: '大客户',
        eservicercenter: '',
        eaccounttype: '独立户',
        eregion: '徐汇'
      },
      {
        enumber: '10002',
        ename: '雇员2',
        eidno: '',
        estate: '已办',
        eservice: '日本',
        eservicercenter: '',
        eaccounttype: '中智大库',
        eregion: '长宁'
      },
      {
        enumber: '10003',
        ename: '雇员3',
        eidno: '',
        estate: '转出',
        eservice: '虹桥',
        eservicercenter: '',
        eaccounttype: '外包',
        eregion: '徐汇'
      },
      {
        enumber: '10004',
        ename: '雇员4',
        eidno: '',
        estate: '已办',
        eservice: '浦东',
        eservicercenter: '',
        eaccounttype: '中智大库',
        eregion: '徐汇'
      },
      {
        enumber: '10005',
        ename: '雇员5',
        eidno: '',
        estate: '已办',
        eservice: '浦东',
        eservicercenter: '',
        eaccounttype: '中智大库',
        eregion: '徐汇'
      },
      {
        enumber: '10006',
        ename: '雇员6',
        eidno: '',
        estate: '已办',
        eservice: '浦东',
        eservicercenter: '',
        eaccounttype: '中智大库',
        eregion: '徐汇'
      },
      {
        enumber: '10007',
        ename: '雇员7',
        eidno: '',
        estate: '已做',
        eservice: '徐汇',
        eservicercenter: '',
        eaccounttype: '中智大库',
        eregion: '徐汇'
      },
      {
        enumber: '10008',
        ename: '雇员8',
        eidno: '',
        estate: '已做',
        eservice: '徐汇',
        eservicercenter: '',
        eaccounttype: '中智大库',
        eregion: '徐汇'
      }
    ],
  }
})
const employeeSocialSecuritySearch = Axios.get('employeeSocialSecuritySearch')

export default {
  employeeSocialSecuritySearch
}
