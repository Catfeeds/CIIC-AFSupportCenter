<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        企业社保账户管理
        <div slot="content">
          <Form ref="accountManageInfo" :model="accountManageInfo" :label-width=120>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="养老金用公司名称：" prop="pensionCompanyName">
                  <Input v-model="accountManageInfo.pensionCompanyName" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="账户类型：" prop="accountTypeValue">
                  <Select v-model="accountManageInfo.accountTypeValue" style="width: 100%;">
                    <Option v-for="item in accountManageInfo.accountTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="开户日期：" prop="taskStartTime">
                  <DatePicker v-model="accountManageInfo.taskStartTime" type="daterange" placement="bottom" placeholder="选择日期" style="width: 100%"></DatePicker>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="企业社保账号：" prop="taskNumber">
                  <Input v-model="accountManageInfo.taskNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="状态：" prop="stateValue">
                  <Select v-model="accountManageInfo.stateValue" style="width: 100%;">
                    <Option v-for="item in accountManageInfo.stateList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 1, offset: 17}" :lg="{span: 1, offset: 17}">
                <Button type="primary" @click="" icon="ios-search">查询</Button>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
    </Collapse>

    <Form>
      <Row class="mt20">
        <Col :xs="{span: 1}" :lg="{span: 1}">
          <Form-item class="ml10">
            <Button type="info" @click="">导出</Button>
          </Form-item>
        </Col>
      </Row>

      <Row>
        <Col :xs="{span: 24}" :lg="{span: 24}">
          <Table border :columns="accountManageColumns" :data="companysocialsecuritymanage.accountManageData"></Table>
          <Page :total="4" :page-size="5" :page-size-opts="[5, 10]" show-sizer show-total  class="pageSize"></Page>
        </Col>
      </Row>
    </Form>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import eventType from '../../store/EventTypes'

  export default {
    data() {
      return{
        collapseInfo: [1], //展开栏
        accountManageInfo: {
          pensionCompanyName: '',
          accountTypeValue: '',
          accountTypeList: [
            {value: '1', label: '中智大库'},
            {value: '2', label: '中智独立库'},
            {value: '3', label: '独立户'},
          ],
          openAccountDate: '',
          companySocialSecurityAccount: '',
          stateValue: '',
          stateList: [
            {value: '1', label: '有效'},
            {value: '2', label: '封存'},
            {value: '3', label: '终止'},
          ]
        },

        accountManageColumns: [
          {title: '操作', key: 'action', fixed: 'left', width: 80, align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {type: 'primary', size: 'small'},
                  style: {margin: '0 auto'},
                  on: {
                    click: () => {
                      this.$router.push({name: 'companysocialsecurity'})
                    }
                  }
                }, '查看'),
              ]);
            }
          },
          {title: '养老金用公司名称', key: 'pensionCompanyName', width: 250, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.pensionCompanyName),
              ]);
            }
          },
          {title: '账户类型', key: 'accountType', width: 100, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.accountType),
              ]);
            }
          },
          {title: '状态', key: 'state', width: 120, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.state),
              ]);
            }
          },
          {title: '企业社保账号', key: 'companySocialSecurityAccount', width: 180, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.companySocialSecurityAccount),
              ]);
            }
          },
          {title: '开户\\转入日期', key: 'checkInDate', width: 120, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.checkInDate),
              ]);
            }
          },
          {title: '终止日期', key: 'endDate', width: 120, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.sponsor),
              ]);
            }
          },
          {title: '开户办理人', key: 'openHandler', width: 120, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.openHandler),
              ]);
            }
          },
          {title: '开户办理日期', key: 'openHandleDate', width: 120, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.openHandleDate),
              ]);
            }
          },
          {title: '备注说明', key: 'notes', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.notes),
              ]);
            }
          }
        ],
      }
    },
    mounted() {
      this.setCompanySocialSecurityManage()
    },
    computed: {
      ...mapGetters('companySocialSecurityManage',[
        'companysocialsecuritymanage'
      ])
    },
    methods: {
      ...mapActions('companySocialSecurityManage', {
        setCompanySocialSecurityManage: eventType.COMPANYSOCIALSECURITYMANAGETYPE
      }),
      resetSearchCondition(name) {
        this.$refs[name].resetFields()
      },
      ok () {

      },
      cancel () {

      }
    }
  }
</script>
<style scoped>
  .mt20 {margin-top: 20px;}
  .ml10 {margin-left: 10px;}
</style>
