import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/leftNavigationList').reply(200,{
  data:[
    {
      id : 1,
      key: "工资单数据源管理",
      icon:"ios-cog",
      children:[
        {cid:"1-1",ckey:"工资单数据源导入",crouter:"dimport"},
        {cid:"1-2",ckey:"工资单数据源查询",crouter:"dsearch"}
      ]
    },
    {
      id : 2,
      key: "工资单任务单管理",
      icon:"ios-calculator",
      children:[
        {cid:"2-1",ckey:"工资单任务单提交",crouter:"ptasksubmit"},
        {cid:"2-2",ckey:"工资单任务单审批",crouter:"ptaskapproval"},
        {cid:"2-3",ckey:"工资单任务单查询",crouter:"ptasksearch"},
      ]
    },
    {
      id : 3,
      key: "工资单打印管理",
      icon:"volume-mute",
      children:[
        {cid:"3-1",ckey:"工资单任务单打印管理",crouter:"ptaskprint"}
      ]
    },
    {
      id : 4,
      key: "工资单发放管理",
      icon:"planet",
      children:[
        {cid:"4-1",ckey:"工资单任务单发放",crouter:"ptaskgrant"}
      ]
    }
  ]
})
const leftNavigationList = Axios.get("/leftNavigationList")

export default {
  leftNavigationList,
}
