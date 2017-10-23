<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        客户基本信息
        <div slot="content">
          <Form :label-width=100>
            <Row class="mt20">
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="客户编号：" class="">
                  <label>{{employeesocialsecurityinfo.customer.code}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="客户名称：" class="">
                  <label>{{employeesocialsecurityinfo.customer.name}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="服务中心：" class="">
                  <label>{{employeesocialsecurityinfo.customer.customerServicerCenter}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="客服经理：" class="">
                  <label>{{employeesocialsecurityinfo.customer.customerServicer}}</label>
                </Form-item>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
      <Panel name="2">
        雇员信息
        <div slot="content">
          <Form :label-width=100>
            <Row class="mt20">
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="雇员编号：" class="">
                  <label>{{employeesocialsecurityinfo.employee.code}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="雇员姓名：" class="">
                  <label>{{employeesocialsecurityinfo.employee.name}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="证件号码：" class="">
                  <label>{{employeesocialsecurityinfo.employee.idNo}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="入职日期：" class="">
                  <label>{{employeesocialsecurityinfo.employee.checkInTime}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="社保序号：" class="">
                  <label>{{employeesocialsecurityinfo.employee.socialSecurityNo}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="学历：" class="">
                  <label>{{employeesocialsecurityinfo.employee.education}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="企业社保账户：" class="">
                  <label>{{employeesocialsecurityinfo.employee.socialSecurityCompanyAcount}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="人员分类：" class="">
                  <label>{{employeesocialsecurityinfo.employee.personType}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="离职日期：" class="">
                  <label>{{employeesocialsecurityinfo.employee.checkOutTime}}</label>
                </Form-item>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
      <Panel name="3">
        社保汇缴信息
        <div slot="content">
          <Form :label-width=100>
            <Row class="mt20">
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="社保状态：" class="">
                  <label>{{employeesocialsecurityinfo.ssinfo.state}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="社保缴费基数：" class="">
                  <label>{{employeesocialsecurityinfo.ssinfo.payBasePrice}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="社保起缴月份：" class="">
                  <label>{{employeesocialsecurityinfo.ssinfo.payStartMonth}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="办理月份：" class="">
                  <label>{{employeesocialsecurityinfo.ssinfo.doMonth}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 14, offset: 1}" :lg="{span: 14, offset: 1}">
                <Table border :columns="socialSecurityInfoListColumns" :data="employeesocialsecurityinfo.socialSecurityInfoListData"></Table>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
      <Panel name="4">
        变动历史
        <div slot="content">
          <Form :label-width=100>
            <Row class="mt20">
              <Col :xs="{span: 14, offset: 1}" :lg="{span: 14, offset: 1}">
                <Table width="100%" border :columns="changeListColumns" :data="employeesocialsecurityinfo.changeListData"></Table>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
    </Collapse>
    <Row class="mt20">
      <Col :xs="{span: 1, offset: 23}" :lg="{span: 1, offset: 23}">
        <Button type="default" @click="goBack">返回</Button>
      </Col>
    </Row>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import * as eventType from '../../store/EventTypes/shsocialsecurity/EmployeeSocialSecurityInfoType'

  export default {
    name:"employeesocialsecurityinfo",
    data() {
      return {
        collapseInfo: [1, 2, 3, 4], //展开栏

        socialSecurityInfoListColumns: [
          {title: '变更类型', key: 'changeType', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.changeType),
              ]);
            }
          },
          {title: '基数', key: 'base', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.base),
              ]);
            }
          },
          {title: '起缴年月', key: 'startTime', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.startTime),
              ]);
            }
          },
          {title: '截至年月', key: 'endTime', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.endTime),
              ]);
            }
          }
        ],

        changeListColumns: [
          {title: '任务单编号', key: 'tid', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('a', {
                  on: {
                    click: () => {
                      this.$router.push({
                        name: 'companysocialsecuritynew',
                        query: {operatorType: params.index.toString(), sourceFrom: 'search'}
                      });
                    }
                  }
                }, params.row.tid)
              ]);
            }
          },
          {title: '办理方式', key: 'doMetod', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.doMetod),
              ]);
            }
          },
          {title: '变更内容', key: 'changeContent', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.changeContent),
              ]);
            }
          },
          {title: '任务发起人', key: 'creator', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.creator),
              ]);
            }
          },
          {title: '任务发起日期', key: 'startTime', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.startTime),
              ]);
            }
          },
          {title: '办理结果', key: 'result', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.result),
              ]);
            }
          }
        ]
      }
    },
    mounted() {
      this.setEmployeeSocialSecurityInfo()
    },
    computed: {
      ...mapGetters([
        'employeesocialsecurityinfo'
      ])
    },
    methods: {
      ...mapActions({
        setEmployeeSocialSecurityInfo: eventType.EMPLOYEESOCIALSECURITYINFO
      }),
      goBack() {
        this.$router.push({name:'employeesocialsecuritysearch'});
      }
    }
  }
</script>

<style scoped>
  .mt20 {
    margin-top: 20px;
  }
</style>
