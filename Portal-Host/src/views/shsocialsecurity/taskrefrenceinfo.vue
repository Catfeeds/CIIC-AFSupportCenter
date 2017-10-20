<template>
  <Form :label-width=100 v-if="operatorType === '0'">
    <Row class="mt20">
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="开AF单日期：" class="">
          <label>{{taskNewInfo.afDate}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="存档地：" class="">
          <label>{{taskNewInfo.storePlace}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="用工状态：" class="">
          <label>{{taskNewInfo.jobState}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 6, offset: 1}" :lg="{span: 6, offset: 1}">
        <Form-item label="用工日期：" class="">
          <label>{{taskNewInfo.jobDate}}</label>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
        <Table border :columns="taskNewInfoColumns" :data="taskNewInfoData"></Table>
      </Col>
    </Row>
  </Form>
  <Form :label-width=100 v-else-if="operatorType === '1'">
    <Row class="mt20">
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="新社保缴费基数:" class="">
          <label>{{taskChangeInfo.newPay}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="调整起始月份：" class="">
          <label>{{taskChangeInfo.changeStartMonth}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="调整截至月份：" class="">
          <label>{{taskChangeInfo.changeEndMonth}}</label>
        </Form-item>
      </Col>
    </Row>
  </Form>
  <Form :label-width=100 v-else>
    <Row class="mt20">
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="用工状态:" class="">
          <label>{{taskOutInfo.state}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
        <Form-item label="离职日期：" class="">
          <label>{{taskOutInfo.leaveDate}}</label>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
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
      }
    },
    data() {
      return {
        taskNewInfo: {
          afDate: '2017-05-06',
          storePlace: '外来从业人员',
          jobState: '已用工',
          jobDate: '2017-06-1'
        },
        taskNewInfoData: [
          {base: '19000', startMonth: '201701', endYear: ''},
          {base: '', startMonth: '', endYear: ''}
        ],
        taskNewInfoColumns: [
          {title: '基数', key: 'base'},
          {title: '起缴月份', key: 'startMonth'},
          {title: '截至月份', key: 'endYear'}
        ], //任务单参考信息 -- 新增

        taskChangeInfo: {
          newPay: '19000',
          changeStartMonth: '201701',
          changeEndMonth: '201706'
        }, //任务单参考信息 -- 调整

        taskOutInfo: {
          state: '已退工',
          leaveDate: '2017-6-24',
          socialSecurityEndDate: '201706'
        }
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
