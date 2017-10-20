<template>
  <Form :label-width=100>
    <Row class="mt20">
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="变更类型：" class="">
          <label>{{socialSecurityEndOperator.changeType}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="办理方式：" class="">
          <Select v-model="socialSecurityEndOperator.doMethodValue" style="width: 100%;">
            <Option v-for="item in socialSecurityEndOperator.doMethodList" :value="item.value" :key="item.value">{{item.label}}</Option>
          </Select>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="社保终止月份：" class="">
          <DatePicker v-model="socialSecurityEndOperator.socialSecurityEndMonth" type="daterange" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
    </Row>
    <Row class="mt20">
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="受理日期：" class="">
          <DatePicker v-model="socialSecurityEndOperator.accpetDate" type="daterange" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="日期：" class="">
          <DatePicker v-model="socialSecurityEndOperator.date" type="daterange" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="完成日期：" class="">
          <DatePicker v-model="socialSecurityEndOperator.finishDate" type="daterange" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
    </Row>
    <Row class="mt20">
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="办理备注：" class="">
          <Input v-model="socialSecurityEndOperator.doNotes" placeholder="请输入..."></Input>
        </Form-item>
      </Col>
    </Row>
    <Row class="mt20">
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="批退备注：" class="">
          <Input v-model="socialSecurityEndOperator.refuseNotes" placeholder="请输入..."></Input>
        </Form-item>
      </Col>
    </Row>
  </Form>
</template>
<script>
  export default {
    name:"socialsecurityendoperator",
    props:{
      operatorType:{
        type: String
      }
    },
    data() {
      return {
        socialSecurityEndOperator: {
          changeType: '终止',
          doMethodValue: '',
          doMethodList: [
            {value: '1', label: '柜面办理'}
          ],
          socialSecurityEndMonth: '',
          accpetDate: '',
          date: '',
          finishDate: '',
          doNotes: '',
          refuseNotes: ''
        }
      }
    },
    mounted() {

    },
    computed: {

    },
    methods: {
      insert(index) {
        this.socialSecurityPayOperator.operatorListData.splice(index, 0, {operator: '', startMonth: '', endMonth: '', base: '', action: '', disabled: false});
      },
      remove (index) {
        this.socialSecurityPayOperator.operatorListData.splice(index, 1);
      }
    }
  }
</script>
<style scoped>
  .mt20 {margin-top: 20px;}
</style>
