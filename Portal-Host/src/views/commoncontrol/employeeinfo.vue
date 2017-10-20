<template>
  <Form :label-width=100>
    <Row class="mt20">
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="雇员编号：" class="">
          <label>{{employee.code}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="雇员姓名：" class="">
          <label>{{employee.name}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="证件号码：" class="">
          <label>{{employee.idNo}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
        <Form-item label="入职日期：" class="">
          <label>{{employee.checkInTime}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="学历：" class="">
          <label>{{employee.education}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="人员分类：" class="">
          <label>{{employee.personType}}</label>
        </Form-item>
      </Col>
      <!-- 仅新增 -->
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}" v-show="operatorType === '0'">
        <Form-item label="社保账号分类：" class="">
          <label>{{employee.socialSecurityType}}</label>
        </Form-item>
      </Col>
      <!-- 调整和转出 -->
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}" v-show="operatorType === '1' || operatorType === '2'">
        <Form-item label="社保序号：" class="">
          <label>{{employee.socialSecurityIndex}}</label>
        </Form-item>
      </Col>
      <!-- 调整和转出 -->
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}" v-show="operatorType === '1' || operatorType === '2'">
        <Form-item label="企业社保账号：" class="">
          <label>{{employee.companySocialSecurityAccount}}</label>
        </Form-item>
      </Col>
      <!-- 仅新增 -->
      <Col :xs="{span: 23}" :lg="{span: 23}" v-show="operatorType == '0'">
        <Form-item label="户口地址：" :label-width=162 class="">
          <label>{{employee.residenceAddress}}</label>
        </Form-item>
      </Col>
      <!-- 仅新增 -->
      <Col :xs="{span: 23}" :lg="{span: 23}" v-show="operatorType == '0'">
        <Form-item label="联系地址：" :label-width=162 class="">
          <label>{{employee.contactAddress}}</label>
        </Form-item>
      </Col>
    </Row>
  </Form>
</template>
<script>
  export default {
    name:"employeeinfo",
    props:{
      operatorType:{
        type: String
      }
    },
    data() {
      return {
        employee: {
          code: 'GY0001',
          name: '张三',
          idNo: '35220219781124114',
          checkInTime: '2017-1-1',
          education: '本科',
          socialSecurityIndex: '123235564',
          companySocialSecurityAccount: '中智大库',
          residenceAddress: 'XXX路X号xxx',
          contactAddress: 'XXX路X号xxx',
          personType: '外地',
          socialSecurityType: '中智大库'
        } //雇员信息
      }
    },
    mounted() {

    },
    computed: {

    },
    methods: {

    }
  }
</script>
