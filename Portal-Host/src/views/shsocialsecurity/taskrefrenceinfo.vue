<template>
  <Form :label-width=120 v-if="operatorType === '0'">
    <Row class="mt20">
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="开AF单日期：" class="">
          <label>{{taskNewInfo.afDate}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="存档地：" class="">
          <label>{{taskNewInfo.storePlace}}</label>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="用工状态：" class="">
          <label>{{taskNewInfo.jobState}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="用工日期：" class="">
          <label>{{taskNewInfo.jobDate}}</label>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 13, offset: 1}" :lg="{span: 13, offset: 1}">
        <Table border :columns="taskNewInfoColumns" :data="taskNewInfoData"></Table>
      </Col>
    </Row>
  </Form>
  <Form :label-width=120 v-else-if="operatorType === '1'">
    <Row class="mt20">
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="新社保缴费基数:" class="">
          <label>{{taskChangeInfo.newPay}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="调整起始月份：" class="">
          <label>{{taskChangeInfo.changeStartMonth}}</label>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="调整截至月份：" class="">
          <label>{{taskChangeInfo.changeEndMonth}}</label>
        </Form-item>
      </Col>
    </Row>
  </Form>
  <Form :label-width=120 v-else>
    <Row class="mt20">
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="用工状态:" class="">
          <label>{{taskOutInfo.state}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="离职日期：" class="">
          <label>{{taskOutInfo.leaveDate}}</label>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="社保截止年月：" class="">
          <label>{{taskOutInfo.socialSecurityEndDate}}</label>
        </Form-item>
      </Col>
    </Row>
  </Form>
</template>
<script>
  export default {
    name:"taskrefrenceinfo",
    props:{
      operatorType:{
        type: String
      },
      taskNewInfo: {
        type: Object
      },
      taskChangeInfo: {
        type: Object
      },
      taskOutInfo: {
        type: Object
      },
      taskNewInfoData: {
        type: Array
      }
    },
    data() {
      return {
        taskNewInfoColumns: [
          {title: '基数', key: 'base', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.base),
              ]);
            }
          },
          {title: '起缴月份', key: 'startMonth', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.startMonth),
              ]);
            }
          },
          {title: '截至月份', key: 'endYear', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.endYear),
              ]);
            }
          }
        ], //任务单参考信息 -- 新增
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
