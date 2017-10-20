<template>
      <div>
            <Modal v-model="show" title="增加人员" ok-text="新增" cancel-text="取消" width="1000">
              <div class="addNewEmp">
                <Row class="clientFixed">
                   <Col :xs="{ span: 8, offset: 1 }">
                  <Form :model="formItem" :label-width=100 label-position="left">
                    <Form-item label="管理方编号：">
                        <Select
                            v-model="formItem.name"
                            filterable
                            remote
                            :remote-method="searchManagementCode"
                            >
                            <Option v-for="option in optionsName" :value="option.value" :key="option.value">{{option.label}}</Option>
                        </Select>
                    </Form-item>
                       <Form-item label="所属部门：">
                      <Select placeholder="请选择">
                          <Option value="研发部">研发部</Option>
                          <Option value="销售部">销售部</Option>
                          <Option value="市场部">市场部</Option>
                          <Option value="公关部">公关部</Option>
                      </Select>
                    </Form-item>
                  </Form>
                  </Col>
                   <Col :xs="{ span: 8, offset: 1 }">
                   <Form :model="formItem" :label-width=100 label-position="left">
                     <Form-item label="管理方名称：">
                        <Select
                            v-model="formItem.name"
                            filterable
                            remote
                            :remote-method="searchManagementName"
                            >
                            <Option v-for="option in optionsName" :value="option.value" :key="option.value">{{option.label}}</Option>
                        </Select>
                    </Form-item>
                    <Form-item label="雇员名称：">
                      <Input v-model="formItem.input" placeholder="请输入"></Input>
                    </Form-item>
                  </Form>
                   </Col>
                   <Col :xs="{ span: 4, offset: 1 }" >
                      <Button type="primary" size="large">查询</Button>
                   </Col>
                </Row>
              </div>

              <Table border :columns="empColumns" :data="addemployeelist"></Table>
              <Page :total="100" show-sizer show-elevator></Page>
          </Modal>
      </div>
</template>
<script>

import {mapActions,mapGetters} from 'vuex'
import * as eventType from '../../store/EventTypes/PayrollTaskSubmitDetialType'

export default {
  props:['showModal'],
  data(){
    return {
        show:false,
        optionsName:[],
        formItem: {
          name:"",
          input:""
        },
        empColumns: [{
            type: 'selection',
            width: 60,
            sortable: true,
            align: 'center'
          },
          {
            title: '雇员名称',
            sortable: true,
            key: 'empName'
          },
          {
            title: '雇员编码',
            sortable: true,
            key: 'empId'
          },
          {
            title: '管理方',
            sortable: true,
            key: 'management'
          },
          {
            title: '部门',
            sortable: true,
            key: 'department'
          },
          {
            title: '薪资周期',
            sortable: true,
            key: 'salaryperiod',
            width: 150,
            align: 'center'
          }
          // {
          //   title: '实际基本工资',
          //   sortable: true,
          //   key: 'basicSalary',
          //   width: 150,
          //   align: 'center'
          // },
          //  {
          //   title: '应税工资合计',
          //   sortable: true,
          //   key: 'taxTotalSalary',
          //   width: 150,
          //   align: 'center'
          // },
          // {
          //   title: '个人所得税',
          //   sortable: true,
          //   key: 'persnonTax',
          //   width: 150,
          //   align: 'center'
          // },
          // {
          //   title: '工资单模板',
          //   sortable: true,
          //   key: 'payrolltemplate',
          //   width: 150,
          //   align: 'center'
          // }
        ]
      }
  },
  mounted() {
    this.setList();
  },
  computed: {
    ...mapGetters([
      'addemployeelist'
    ]),
  },
  methods: {
    ...mapActions({
      setList:eventType.PAYROLLTADDEMPLOYEE_SETLIST
    }),
    remove(index) {
        this.data6.splice(index, 1);
    },
    searchManagementCode(){},
    searchManagementName(){}
  },
  watch: {
      showModal() {
          this.show = this.showModal
      },
      show() {
          this.$emit("closeModal", this.show)
      }
  }
}
</script>
