<template>
  <div>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="客户编号：" class="">
          <label>{{companyInfo.customerNumber}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="客户名称：" class="">
          <label>{{companyInfo.customerName}}</label>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="服务中心：" class="">
          <label>{{companyInfo.serviceCenter}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="客服经理：" class="">
          <label>{{companyInfo.serviceManager}}</label>
        </Form-item>
      </Col>
    </Row>
  </div>
</template>
<script>
  export default {
    name:"companyinfo",
    props: {
      companyInfo: {
        type: Object
      }
    }
  }
</script>
