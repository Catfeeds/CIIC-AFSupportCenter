import Axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(Axios)

mock.onGet('/payrollTaskSubmitList').reply(200,{
  data:[
    {
      id : "1",
      taskNo:"APIT00001",
      taskTitle:"201608上海德邦A类雇员",
      management:"邦德集团",
      salaryperiod:"201608",
      calculatebatch:"00001,000002",
      payrolltemplate:"",
      total:"200",
      china:"195",
      foreign:"5",
      payrolltype:"纸质",
      state:"草稿",
      publishdate:"2016-08-30",
      leaf:"parent",
      refNo:"",
      children:[
        {
          id : "1-1",
          taskNo:"CAPIT00001",
          taskTitle:"201608上海德邦A类雇员",
          management:"邦德集团",
          salaryperiod:"201608",
          calculatebatch:"00001,000002",
          payrolltemplate:"上海邦德模板A",
          total:"100",
          china:"95",
          foreign:"5",
          payrolltype:"纸质",
          state:"草稿",
          publishdate:"2016-08-30",
          leaf:"children",
          refNo:"APIT00001"
        },
        {
          id : "1-2",
          taskNo:"CAPIT00002",
          taskTitle:"201608上海德邦A类雇员",
          management:"邦德集团",
          salaryperiod:"201608",
          calculatebatch:"00001,000002",
          payrolltemplate:"上海邦德模板B",
          total:"100",
          china:"100",
          foreign:"0",
          payrolltype:"纸质",
          state:"草稿",
          publishdate:"2016-08-30",
          leaf:"children",
          refNo:"APIT00001"
        }
      ]
    },
    {
      id : "2",
      taskNo:"APIT00002",
      taskTitle:"201608上海德邦B类雇员",
      management:"邦德集团",
      salaryperiod:"201608",
      calculatebatch:"00001,000002",
      payrolltemplate:"",
      total:"300",
      china:"300",
      foreign:"0",
      payrolltype:"纸质",
      state:"草稿",
      publishdate:"2016-09-01",
      leaf:"parent",
      refNo:"",
      children:[
        {
          id : "2-1",
          taskNo:"CAPIT00003",
          taskTitle:"201608上海德邦B类雇员",
          management:"邦德集团",
          salaryperiod:"201608",
          calculatebatch:"00001,000002",
          payrolltemplate:"上海邦德模板A",
          total:"100",
          china:"100",
          foreign:"0",
          payrolltype:"纸质",
          state:"草稿",
          publishdate:"2016-09-01",
          leaf:"children",
          refNo:"APIT00002"
        },
        {
          id : "2-2",
          taskNo:"CAPIT00004",
          taskTitle:"201608上海德邦A类雇员",
          management:"邦德集团",
          salaryperiod:"201608",
          calculatebatch:"00001,000002",
          payrolltemplate:"上海邦德模板B",
          total:"200",
          china:"200",
          foreign:"0",
          payrolltype:"纸质",
          state:"草稿",
          publishdate:"2016-08-30",
          leaf:"children",
          refNo:"APIT00001"
        }
      ]
    },


    {
      id : "3",
      taskNo:"APIT00003",
      taskTitle:"201608上海德邦C类雇员",
      management:"邦德集团",
      salaryperiod:"201608",
      calculatebatch:"00001,000002",
      payrolltemplate:"",
      total:"150",
      china:"100",
      foreign:"50",
      payrolltype:"纸质",
      state:"草稿",
      publishdate:"2016-08-30",
      leaf:"parent",
      refNo:"",
      children:[
        {
          id : "3-1",
          taskNo:"CAPIT00005",
          taskTitle:"201608上海德邦C类雇员",
          management:"邦德集团",
          salaryperiod:"201608",
          calculatebatch:"00001,000002",
          payrolltemplate:"上海邦德模板A",
          total:"100",
          china:"50",
          foreign:"50",
          payrolltype:"纸质",
          state:"草稿",
          publishdate:"2016-08-30",
          leaf:"children",
          refNo:"APIT00003"
        },
        {
          id : "3-2",
          taskNo:"CAPIT00006",
          taskTitle:"201608上海德邦C类雇员",
          management:"邦德集团",
          salaryperiod:"201608",
          calculatebatch:"00001,000002",
          payrolltemplate:"上海邦德模板B",
          total:"50",
          china:"50",
          foreign:"0",
          payrolltype:"纸质",
          state:"草稿",
          publishdate:"2016-08-30",
          leaf:"children",
          refNo:"APIT00003"
        }
      ]
    },
    {
      id : "4",
      taskNo:"APIT00004",
      taskTitle:"201608上海德邦D类雇员",
      management:"邦德集团",
      salaryperiod:"201608",
      calculatebatch:"00001,000002",
      payrolltemplate:"",
      total:"400",
      china:"400",
      foreign:"0",
      payrolltype:"纸质",
      state:"草稿",
      publishdate:"2016-09-01",
      leaf:"parent",
      refNo:"",
      children:[
        {
          id : "4-1",
          taskNo:"CAPIT00007",
          taskTitle:"201608上海德邦D类雇员",
          management:"邦德集团",
          salaryperiod:"201608",
          calculatebatch:"00001,000002",
          payrolltemplate:"上海邦德模板A",
          total:"200",
          china:"200",
          foreign:"0",
          payrolltype:"纸质",
          state:"草稿",
          publishdate:"2016-09-01",
          leaf:"children",
          refNo:"APIT00004"
        },
        {
          id : "4-2",
          taskNo:"CAPIT00008",
          taskTitle:"201608上海德邦D类雇员",
          management:"邦德集团",
          salaryperiod:"201608",
          calculatebatch:"00001,000002",
          payrolltemplate:"上海邦德模板B",
          total:"200",
          china:"200",
          foreign:"0",
          payrolltype:"纸质",
          state:"草稿",
          publishdate:"2016-08-30",
          leaf:"children",
          refNo:"APIT00004"
        }
      ]
    }
  ]
})
const payrollTaskSubmitList = Axios.get("/payrollTaskSubmitList")

export default {
  payrollTaskSubmitList,
}
