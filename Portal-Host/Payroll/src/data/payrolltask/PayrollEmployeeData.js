import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/payrollEmployeeList').reply(200,{
  data:[
    {
      empId : "EM54002",
      empName:"张丹峰",
      management:"邦德集团",
      managementNo:"C0000001",
      salaryperiod:"201608",
      basicSalary:"10000.00",
      taxTotalSalary:"9000.00",
      persnonTax:"600.00",
      calculatebatch:"00001",
      payrolltemplate:"上海邦德模板A"
    },
    {
      empId : "EM54003",
      empName:"王小强",
      management:"邦德集团",
      managementNo:"C0000001",
      salaryperiod:"201608",
      basicSalary:"10000.00",
      taxTotalSalary:"9000.00",
      persnonTax:"600.00",
      calculatebatch:"00001",
      payrolltemplate:"上海邦德模板A"
    },
    {
      empId : "EM54004",
      empName:"孙亮",
      management:"邦德集团",
      managementNo:"C0000001",
      salaryperiod:"201608",
      basicSalary:"10000.00",
      taxTotalSalary:"9000.00",
      persnonTax:"600.00",
      calculatebatch:"00001",
      payrolltemplate:"上海邦德模板A"
    },
    {
      empId : "EM54005",
      empName:"吴楠",
      management:"邦德集团",
      managementNo:"C0000001",
      salaryperiod:"201608",
      basicSalary:"10000.00",
      taxTotalSalary:"9000.00",
      persnonTax:"600.00",
      calculatebatch:"00001",
      payrolltemplate:"上海邦德模板A"
    },
    {
      empId : "EM54006",
      empName:"张晗",
      management:"邦德集团",
      managementNo:"C0000001",
      salaryperiod:"201608",
      basicSalary:"10000.00",
      taxTotalSalary:"9000.00",
      persnonTax:"600.00",
      calculatebatch:"00001",
      payrolltemplate:"上海邦德模板A"
    }
  ]
})
const payrollEmployeeList = Axios.get("/payrollEmployeeList")

export default {
  payrollEmployeeList,
}
