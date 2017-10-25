/**
 * Created by huangye on 2017/10/25.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/companySocialSecurityProgress2').reply(200, {
  data: {
    companyInfo: {
      customerNumber: 'KH0001',
      customerName: '上海XX信息技术有限公司',
      serviceCenter: '大客户2',
      serviceManager: '王XX'
    },

    operatorMaterialListData: [
      {
        isLink: false,
        material: '材料1',
        materialCommitDate: '2017-7-3 12:33:33',
        materialType: '原件',
        materialReciveDate: '2017-7-5 12:33:33',
        state: '1',
        operator: '',
        notes: ''
      },
      {
        isLink: false,
        material: '材料2',
        materialCommitDate: '2017-7-3 12:33:33',
        materialType: '复印件',
        materialReciveDate: '',
        state: '1',
        operator: '',
        notes: ''
      },
      {
        isLink: true,
        material: '材料3',
        materialCommitDate: '2017-7-3 12:33:33',
        materialType: '扫描件',
        materialReciveDate: '',
        state: '1',
        operator: '',
        notes: ''
      },
      {
        isLink: false,
        material: '材料4',
        materialCommitDate: '2017-7-3 12:33:33',
        materialType: '',
        materialReciveDate: '2017-7-5 12:33:33',
        state: '1',
        operator: '',
        notes: ''
      }
    ],

    chatList: [
      {
        icon: '#',
        name: '社保专员',
        date: '2017-03-02 14:14:32',
        content: '材料不齐全：材料1(备注内容)； 未签收：材料2(备注内容)'
      },
      {
        icon: '#',
        name: '客服专员',
        date: '2017-03-05 13:16:52',
        content: '已阅'
      },
      {
        icon: '#',
        name: '社保专员',
        date: '2017-03-06 13:16:52',
        content: '材料1'
      }
    ]
  }
})
const companySocialSecurityProgress2 = Axios.get('companySocialSecurityProgress2')

export default {
  companySocialSecurityProgress2
}
