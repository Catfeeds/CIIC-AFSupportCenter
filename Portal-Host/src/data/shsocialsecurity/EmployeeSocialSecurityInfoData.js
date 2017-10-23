/**
 * Created by huangye on 2017/10/23.
 */
import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/employeeSocialSecurityInfo').reply(200, {
  data: {
    customer: {
      code: 'KH0001',
      name: '上海XX信息技术有限公司',
      customerServicerCenter: '大客户2',
      customerServicer: '张XX'
    }, //客户基本信息

    employee: {
      code: 'GY0001',
      name: '张三',
      idNo: '35220219781124114',
      checkInTime: '2017-1-1',
      socialSecurityNo: '123235564',
      education: '本科',
      socialSecurityCompanyAcount: '中智大库',
      personType: '外地',
      checkOutTime: '2017-1-1'
    }, //雇员信息

    ssinfo: {
      state: '已做',
      payBasePrice: '19000',
      payStartMonth: '201701',
      doMonth: '201706'
    },
    socialSecurityInfoListData: [
      {changeType: '', base: '19000', startTime: '201605', endTime: ''},
      {changeType: '', base: '18000', startTime: '201505', endTime: '201604'},
      {changeType: '', base: '17000', startTime: '201405', endTime: '201504'},
      {changeType: '补', base: '16000', startTime: '201305', endTime: '201404'}
    ], //社保汇缴信息

    changeListData: [
      {tid: 'XK00001', doMetod: '网上申请', changeContent: '新开', creator: 'XXX', startTime: '2015-06-01', result: '已办理'},
      {tid: 'BG00002', doMetod: '柜面办理', changeContent: '调整', creator: 'XXX', startTime: '2015-06-01', result: '不需办理'},
      {tid: 'ZC00003', doMetod: '网上申请', changeContent: '转出', creator: 'XXX', startTime: '2015-07-01', result: '批退'}
    ] //变动历史
  }
})
const employeeSocialSecurityInfo = Axios.get('employeeSocialSecurityInfo')

export default {
  employeeSocialSecurityInfo
}
