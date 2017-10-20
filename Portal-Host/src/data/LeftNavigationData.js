import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/leftNavigationList').reply(200,{
  data:[
    {
      id : 1,
      key: "上海社保",
      icon:"ios-cog",
      children:[
        {cid:"1-1",ckey:"雇员社保查询",crouter:"employeesocialsecuritysearch"},
        {cid:"1-2",ckey:"雇员日常操作",crouter:"employeeoperatorview"},
        {cid:"1-3",ckey:"雇员特殊操作",crouter:"employeespecialoperatorview"},
        {cid:"1-4",ckey:"企业任务单",crouter:"companytasklist"},
        {cid:"1-5",ckey:"企业社保账户管理",crouter:"companysocialsecuritymanage"},
        {cid:"1-6",ckey:"社保对账",crouter:"socialsecurityreconcilate"},
        {cid:"1-7",ckey:"社保支付",crouter:"socialsecuritypay"},
        {cid:"1-8",ckey:"社保报表",crouter:"socialsecurityreport"},
        {cid:"1-9",ckey:"年度基数申报",crouter:"yearbaseapplicate"},
        {cid:"1-10",ckey:"系统管理",crouter:"systemmanage"},
      ]
    }
  ]
})
const leftNavigationList = Axios.get("/leftNavigationList")

export default {
  leftNavigationList,
}
