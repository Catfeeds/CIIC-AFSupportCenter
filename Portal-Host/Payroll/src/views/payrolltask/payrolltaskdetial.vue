<template>
  <div class="smEdit">
      <Card>
        <Row>
          <Col :xs="{ span: 8, offset: 1 }" :lg="{ span: 8, offset: 1 }">
            <Form :model="formItem" :label-width=100>
              <Form-item label="任务单编号：">
                <span>{{ formItem.taskNo}}</span>
              </Form-item>
              <Form-item label="管理方编号：">
                <span>{{ formItem.managementNo }}</span>
              </Form-item>
              <Form-item label="薪资周期：">
                <span>{{ formItem.salaryperiod }}</span>
              </Form-item>
              <Form-item label="中方人数：">
                <span>{{ formItem.china }}</span>
              </Form-item>
              <Form-item label="工资单形式：">
                <span>{{ formItem.payrolltype }}</span>
              </Form-item>
            </Form>
          </Col>
          <Col :xs="{ span: 8, offset: 1 }" :lg="{ span: 8, offset: 1}">
            <Form :model="formItem" :label-width=100>
              <Form-item label="任务单标题：">
                <span>{{ formItem.taskTitle}}</span>
              </Form-item>
              <Form-item label="管理方名称：">
                <span>{{ formItem.management }}</span>
              </Form-item>
              <Form-item label="总人数：">
                <span>{{ formItem.total }}</span>
              </Form-item>
              <Form-item label="外方人数：">
                <span>{{ formItem.foreign }}</span>
              </Form-item>
            </Form>
          </Col>
      </Row>
    </Card>
    <div class="buttons">
      <div class="returnDiv" style="display:inline-block">
        <Button type="primary" class="addEmp" @click="back">返回</Button>
      </div>
    </div>
    <Table border :columns="empColumns" :data="payrollemployeelist" class="empTable"></Table>
    <Page :total="100" show-sizer show-elevator></Page>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import * as eventType from '../../store/EventTypes/PayrollTaskSubmitDetialType'

  export default {
    data() {
      return {
        selectedItems: [],
        formItem: {
          taskNo:"CAPIT00001",
          taskTitle:"201608上海德邦A类雇员",
          management:"邦德集团",
          managementNo:"C00000001",
          salaryperiod:"201608",
          calculatebatch:"00001,000002",
          payrolltemplate:"上海邦德模板A",
          total:"100",
          china:"95",
          foreign:"5",
          payrolltype:"纸质"
        },
        empColumns: [
          {
            title: '雇员编码',
            sortable: true,
            key: 'empId'
          },
          {
            title: '雇员名称',
            sortable: true,
            key: 'empName'
          },
          {
            title: '管理方',
            sortable: true,
            key: 'management'
          },
          {
            title: '薪资周期',
            sortable: true,
            key: 'salaryperiod',
            width: 150,
            align: 'center'
          },
          {
            title: '实际基本工资',
            sortable: true,
            key: 'basicSalary',
            width: 150,
            align: 'center'
          },
           {
            title: '应税工资合计',
            sortable: true,
            key: 'taxTotalSalary',
            width: 150,
            align: 'center'
          },
          {
            title: '个人所得税',
            sortable: true,
            key: 'persnonTax',
            width: 150,
            align: 'center'
          },
          {
            title: '工资单模板',
            sortable: true,
            key: 'payrolltemplate',
            width: 150,
            align: 'center'
          }
        ]
      }
    },
    methods: {
     ...mapActions({
        setList:eventType.PAYROLLTEMPLOYEE_SETLIST
      }),
      back() {
        this.$local.back();
      }
    },
    mounted() {
      this.setList();
    },
    computed: {
    ...mapGetters([
      'payrollemployeelist'
    ]),
  }
  }
</script>
<style scoped>
  .ivu-card {
    margin-bottom: 20px;
  }

  .buttons {
    margin: 15px 0;
  }

  .smEdit .ivu-btn {
    clear: both;
  }

  .addNewEmp {
    margin: 20px 0;
    background: #eee;
    padding: 20px;
  }

  .addNewEmp .ivu-form-item {
    width: 30%;
    display: inline-block;
    margin-right: 20px;
  }

  .clientFixed {
    margin-bottom: 20px;
    width: 90%;
  }

  .rows {
    width: 45%;
    float: left;
  }

</style>
