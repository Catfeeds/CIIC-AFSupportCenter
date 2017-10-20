import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/payrollDataSourceImportList').reply(200,{
  data:[
    {
      id : 1,
      importFile:"201608上海德邦外部员工薪资结果",
      total:"200",
      operator:"张三",
      operattime:"2016-08-20",
      state:"草稿"
    },
    {
      id : 2,
      importFile:"201608深圳德邦外部员工薪资结果",
      total:"300",
      operator:"张三",
      operattime:"2016-08-20",
      state:"已生效"
    },
    {
      id : 3,
      importFile:"201608北京德邦外部员工薪资结果",
      total:"400",
      operator:"张三",
      operattime:"2016-08-20",
      state:"无效"
    },
    {
      id : 4,
      importFile:"201608杭州德邦外部员工薪资结果",
      total:"200",
      operator:"张三",
      operattime:"2016-08-20",
      state:"草稿"
    },
    {
      id : 5,
      importFile:"201608广州德邦外部员工薪资结果",
      total:"500",
      operator:"张三",
      operattime:"2016-08-20",
      state:"已生效"
    },
    {
      id : 6,
      importFile:"201608西安德邦外部员工薪资结果",
      total:"150",
      operator:"张三",
      operattime:"2016-08-20",
      state:"无效"
    }
  ]
})
const payrollDataSourceImportList = Axios.get("/payrollDataSourceImportList")

export default {
  payrollDataSourceImportList,
}
