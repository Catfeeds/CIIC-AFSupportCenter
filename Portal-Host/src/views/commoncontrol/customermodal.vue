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
      <Col :xs="{span: 1, offset: 20}" :lg="{span: 1, offset: 20}">
        <Button type="primary" @click="" icon="ios-search">查询</Button>
      </Col>
    </Row>
    <Row>
      <Table border="" :columns="customerColumns" :data="customerData" class="mt20"></Table>
      <Page :current="2" :total="50" simple></Page>
    </Row>
  </Form>
</template>
<script>
export default {
    name:"customerModal",
    props: {
      customerData: {
        require: true,
        type: Array
      }
    },
    data() {
      return {
        mCustomerNumber: '', //客户编号
        mCustomerName: '', //客户姓名
        customerColumns: [
          {title: '操作', key: 'id', type: 'selection', width: 60, align: 'center'},
          {title: '客户名称', key: 'name', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.name),
              ]);
            }
          },
          {title: '公司编号', key: 'code', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.code),
              ]);
            }
          },
          {title: '企业性质', key: 'nature', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.nature),
              ]);
            }
          },
          {title: '企业类型', key: 'type', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.type),
              ]);
            }
          },
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
<style scoped>
  .mt20 {margin-top: 20px;}
</style>
