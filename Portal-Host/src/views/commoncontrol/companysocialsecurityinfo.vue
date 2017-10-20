<template>
  <Form :label-width=100>
    <Row class="mt20">
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="企业社保账号：" class="">
          <label>{{company.companySocialSecurityAccount}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="客户编号：" class="">
          <label>{{company.companyNumber}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="参保户名称：" class="">
          <label>{{company.companyName}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
        <Form-item label="社保中心：" class="">
          <label>{{company.socialSecurityCenter}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="UKey密码：" class="">
          <label>{{company.uKey}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="账户类型：" class="">
          <label>{{company.accountType}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="客服经理：" class="">
          <label>{{company.companyServicer}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
        <Form-item label="服务中心：" class="">
          <label>{{company.serviceCenter}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 23}" :lg="{span: 23}">
        <Form-item label="企业社保账户状态：" label-width=162 class="">
          <label>{{company.companySocialSecurityState}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 23}" :lg="{span: 23}">
        <Form-item label="客户社保截至日：" label-width=162 class="">
          <label>{{company.companySocialSecurityEndData}}</label>
        </Form-item>
      </Col>
    </Row>
  </Form>
</template>
<script>
  export default {
    name:"employeesocialsecurityinfo",
    data() {
      return {
        company: {
          companySocialSecurityAccount: '12123235',
          companyNumber: '340078',
          companyName: '上海XX信息技术有限公司',
          socialSecurityCenter: '徐汇',
          uKey: '152243',
          accountType: '独立户',
          companySocialSecurityState: '有效\\封存\\终止',
          companySocialSecurityEndData: '本月18日',
          companyServicer: '彭XX',
          serviceCenter: '大客户2'
        } //企业社保账户信息
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
