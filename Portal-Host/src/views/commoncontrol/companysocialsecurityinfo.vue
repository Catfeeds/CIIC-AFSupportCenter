<template>
  <Form :label-width=120>
    <Row class="mt20">
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="企业社保账号：" class="">
          <label>{{company.companySocialSecurityAccount}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="客户编号：" class="">
          <label>{{company.companyNumber}}</label>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="参保户名称：" class="">
          <label>{{company.companyName}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="社保中心：" class="">
          <label>{{company.socialSecurityCenter}}</label>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="UKey密码：" class="">
          <label>{{company.uKey}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="账户类型：" class="">
          <label>{{company.accountType}}</label>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="客服经理：" class="">
          <label>{{company.companyServicer}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="服务中心：" class="">
          <label>{{company.serviceCenter}}</label>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="企业社保账户状态：" class="">
          <label>{{company.companySocialSecurityState}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="客户社保截至日：" class="">
          <label>{{company.companySocialSecurityEndData}}</label>
        </Form-item>
      </Col>
    </Row>
  </Form>
</template>
<script>
  export default {
    name:"employeesocialsecurityinfo",
    props: {
      company: {
        require: true,
        Type: Object,
        default() {
          return {}
        }
      }
    },
    data() {
      return {

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
