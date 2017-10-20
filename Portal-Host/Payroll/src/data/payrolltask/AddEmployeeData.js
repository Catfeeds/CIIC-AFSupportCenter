import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/addEmployeeList').reply(200,{
  data:[
    {
      empId : "EM54002",
      empName:"张丹峰",
      department:"研发部",
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
      department:"销售部",
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
      department:"市场部",
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
      department:"公关部",
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
      department:"研发部",
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
const addEmployeeList = Axios.get("/addEmployeeList")

export default {
  addEmployeeList,
}
