<template>
  <div class="smEdit">
    <Card>
      <Row>
        <Col span="12">
        <Form :model="formItem" :label-width=110 >
          <Form-item label="任务单编号：">
            <span>{{ formItem.taskno}}</span>
          </Form-item>
          <Form-item label="任务单名称：">
            <span>{{ formItem.taskname }}</span>
          </Form-item>
          <Form-item label="管理方名称：">
            <span>{{ formItem.management }}</span>
          </Form-item>
          <Form-item label="薪资周期：">
            <span>{{ formItem.salaryperiod }}</span>
          </Form-item>
          <Form-item label="计算批次：" prop="client">
            <Select v-model="formItem.calculatebatch" multiple style="width:300px">
                <Option v-for="item in calculatebatchTypes" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          </Form-item>
          <Form-item label="工资单模板：" prop="client">
            <Select v-model="formItem.payrolltemplate" clearable style="width:300px" placeholder="请选择">
                <Option v-for="item in payrollTypes" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>

          </Form-item>
          <Form-item label="实际发布日期：" prop="client">
            <DatePicker type="date" placeholder="选择日期" style="width:300px" v-model="formItem.publishdate"></DatePicker>
          </Form-item>
          <Form-item label="备注：" prop="desc">
            <Input type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..." v-model="formItem.remark"></Input>
          </Form-item>
        </Form>
        <div class="floatRightBtns">
          <router-link to="/payrolltasksubmit">
            <Button type="primary">保存</Button>
          </router-link>
          <Button type="success" @click="back">返回</Button>
        </div>
        </Col>
      </Row>
    </Card>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        formItem: {
          taskno:'CAPIT00001',
          taskname:'201608上海德邦A类雇员',
          management:"邦德集团",
          salaryperiod:"201608",
          calculatebatch:['00001','00002'],
          payrolltemplate:"001",
          publishdate:"2016-09-01",
          remark:""
        },
        calculatebatchTypes: [{
            value: '00001',
            label: '00001'
          },
          {
            value: '00002',
            label: '00002'
          },
          {
            value: '00003',
            label: '00003'
          }
        ],
        payrollTypes: [{
            value: '001',
            label: '上海邦德模板A'
          },
          {
            value: '002',
            label: '上海邦德模板B'
          },
          {
            value: '003',
            label: '上海邦德模板C'
          },
          {
            value: '004',
            label: '上海邦德模板D'
          }
        ]}},
    methods: {
      back() {
        this.$local.back();
      }
    },
    mounted() {
    },
    computed: {}
  }
</script>
