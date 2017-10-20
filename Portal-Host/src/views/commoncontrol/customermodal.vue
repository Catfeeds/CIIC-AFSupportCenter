<template>
  <!-- 客户名称 模态框内容 -->
  <Form>
    <Row>
      <Col :xs="{span: 11}" :lg="{span: 11}">
        <Form-item label="客户编号" :label-width=80>
          <Input v-model="mCustomerNumber" icon="ios-search" placeholder="请输入..."></Input>
        </Form-item>
      </Col>
      <Col :xs="{span: 11, offset: 1}" :lg="{span: 11, offset: 1}">
        <Form-item label="客户姓名" :label-width=80>
          <Input v-model="mCustomerName" icon="ios-search" placeholder="请输入..."></Input>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 22, offset: 1}" :lg="{span: 22, offset: 1}">
        <Table :columns="customerColumns" :data="customerData"></Table>
        <Page :current="2" :total="50" simple></Page>
      </Col>
    </Row>
  </Form>
</template>
<script>
export default {
    name:"customerModal",
    data() {
      return {
        mCustomerNumber: '', //客户编号
        mCustomerName: '', //客户姓名
        customerData: [
          {cid: '1', name: '客户1', code: 'KH001', nature: '企业', type: '国家'},
          {cid: '2', name: '客户2', code: 'KH002', nature: '代表处', type: '集体'},
          {cid: '3', name: '客户3', code: 'KH003', nature: '', type: '民营'},
          {cid: '4', name: '客户4', code: 'KH004', nature: '', type: '合资'},
          {cid: '5', name: '客户5', code: 'KH005', nature: '', type: '其他'},
          {cid: '6', name: '客户6', code: 'KH006', nature: '其他', type: '其他'}
        ],
        customerColumns: [
          {title: '操作', key: 'cid', type: 'selection'},
          {title: '客户名称', key: 'name'},
          {title: '公司编号', key: 'code'},
          {title: '企业性质', key: 'nature'},
          {title: '企业类型', key: 'type'}
        ]
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
