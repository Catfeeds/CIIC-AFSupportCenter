<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        企业社保账号信息
        <div slot="content">
          <Form :label-width=120>
            <Row class="mt20">
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="参保户登记码：" class="">
                  <label>{{companySocialSecurityInfo.participantRegistrationCode}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="牡丹卡号：" class="">
                  <label>{{companySocialSecurityInfo.ICBCNumber}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="养老金用公司名称：" class="">
                  <label>{{companySocialSecurityInfo.pensionCompanyName}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="结算区县：" class="">
                  <label>{{companySocialSecurityInfo.region}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="付款行：" class="">
                  <label>{{companySocialSecurityInfo.paymentBank}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="付款方式：" class="">
                  <label>{{companySocialSecurityInfo.payMethodValue}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="工行查询账号：" class="">
                  <label>{{companySocialSecurityInfo.ICBCSearchAccount}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="客户社保截至日：" class="">
                  <label>{{companySocialSecurityInfo.companySocialSecurityEndDate}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="养老金独立开户用户名：" class="">
                  <label>{{companySocialSecurityInfo.pensionUsername}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="养老金独立开户密码：" class="">
                  <label>{{companySocialSecurityInfo.pensionPassword}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="初期余额：" class="">
                  <label>{{companySocialSecurityInfo.originalMoney}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="初期欠费：" class="">
                  <label>{{companySocialSecurityInfo.originalArrears}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="来源地：" class="">
                  <label>{{companySocialSecurityInfo.resource}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="来源地备注：" class="">
                  <label>{{companySocialSecurityInfo.resourceNotes}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="交予方式：" class="">
                  <label>{{companySocialSecurityInfo.toMethod}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="交予方式备注：" class="">
                  <label>{{companySocialSecurityInfo.toMethodNotes}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="给凭证时间：" class="">
                  <label>{{companySocialSecurityInfo.toProofTime}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="变更时间：" class="">
                  <label>{{companySocialSecurityInfo.changeTime}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="收到日期：" class="">
                  <label>{{companySocialSecurityInfo.accpetDate}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 7, offset: 1}" :lg="{span: 7, offset: 1}">
                <Form-item label="转入日期：" class="">
                  <label>{{companySocialSecurityInfo.turnInDate}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 20, offset: 1}" :lg="{span: 20, offset: 1}">
                <Form-item label="发出材料：" class="">
                  <CheckboxGroup v-model="companySocialSecurityInfo.sendMaterialValue">
                    <Checkbox v-for="item in companySocialSecurityInfo.sendMaterial" :value="item.label" :key="item.label">
                      <span>{{item.label}}</span>
                    </Checkbox>
                  </CheckboxGroup>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 20, offset: 1}" :lg="{span: 20, offset: 1}">
                <Form-item label="终止日期：" class="">
                  <label>{{companySocialSecurityInfo.endDate}}</label>
                </Form-item>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>

      <Panel name="2">
        子公司信息
        <div slot="content">
          <Table width="841" border :columns="childCompanyColumns" :data="companysocialsecurity.childCompanyData"></Table>
        </div>
      </Panel>

      <Panel name="3">
        历史任务单
        <div slot="content">
          <Table width="841" border :columns="historyTaskColumns" :data="companysocialsecurity.historyTaskData"></Table>
        </div>
      </Panel>

      <Panel name="4">
        工伤比例变更历史
        <div slot="content">
          <Table width="841" border :columns="workInjuryColumns" :data="companysocialsecurity.workInjuryData"></Table>
        </div>
      </Panel>
    </Collapse>
    <Row class="mt20">
      <Col :xs="{span: 1, offset: 23}" :lg="{span: 1, offset: 23}">
        <Button type="default" @click="goBack">返回</Button>
      </Col>
    </Row>

    <!-- 对话备注 模态框 -->
    <Modal
      v-model="isShowNotes"
      @on-ok="ok"
      @on-cancel="cancel">
      <chat :chatList="companysocialsecurity.chatList"></chat>
    </Modal>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import chat from '../commoncontrol/chathistory/chat.vue'
  import eventType from '../../store/EventTypes'

  export default {
    components: {chat},
    data() {
      return {
        collapseInfo: [1, 2, 3, 4], //展开栏
        companySocialSecurityInfo: {
          participantRegistrationCode: '',
          ICBCNumber: '',
          pensionCompanyName: '',
          region: '',
          paymentBank: '',
          payMethodValue: '',
          payMethodList: [
            {value: '1', label: '我司付款，账单到他司'},
            {value: '2', label: '自己付款账单到我司'},
            {value: '3', label: '自己付款账单到他司'},
            {value: '4', label: '我司付款账单到我司'},
            {value: '5', label: '垫付'}
          ],
          ICBCSearchAccount: '',
          companySocialSecurityEndDate: '',
          pensionUsername: '',
          pensionPassword: '',
          originalMoney: '',
          originalArrears: '',
          resource: '',
          resourceNotes: '',
          toMethod: '',
          toMethodNotes: '',
          toProofTime: '',
          changeTime: '',
          accpetDate: '',
          turnInDate: '',
          sendMaterialValue: [],
          sendMaterial: [
            {label: '正式通知书'}, {label: '预生成通知书'}, {label: '收据'}, {label: '银行对账单'}, {label: '汇总表'}, {label: '实时表'}
          ],
          endDate: ''
        }, //企业社保账号信息

        childCompanyColumns: [
          {title: '公司编码', key: 'companyCode', align: 'center', width: 120,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.companyCode),
              ]);
            }
          },
          {title: '公司名称', key: 'companyName', align: 'center', width: 240,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.companyName),
              ]);
            }
          },
          {title: '服务中心', key: 'serviceCenter', align: 'center', width: 240,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.serviceCenter),
              ]);
            }
          },
          {title: '服务团队', key: 'serviceTeam', align: 'center', width: 240,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.serviceTeam),
              ]);
            }
          }
        ],

        historyTaskColumns: [
          {title: '任务单编号', key: 'tid', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('a', {
                  on: {
                    click: () => {
                      this.$router.push({
                        name: 'companysocialsecurityprogress2',
                        query: {operatorType: params.row.taskType === '开户' ? '1' : params.row.taskType === '变更' ? '2' : '4'}
                      });
                    }
                  }
                }, params.row.tid)
              ]);
            }
          },
          {title: '任务类型', key: 'taskType', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.taskType),
              ]);
            }
          },
          {title: '操作人', key: 'operator', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.operator),
              ]);
            }
          },
          {title: '操作时间', key: 'operatorTime', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.operatorTime),
              ]);
            }
          },
          {title: '备注信息', key: 'notes', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'center'}}, [
                h('Button', {
                  props: {type: 'primary', size: 'small'},
                  style: {margin: '0 auto'},
                  on: {
                    click: () => {
                      this.isShowNotes = true
                    }
                  }
                }, '查看'),
              ]);
            }
          }
        ],

        workInjuryColumns: [
          {title: '行业类别', key: 'industryCategory', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.industryCategory),
              ]);
            }
          },
          {title: '企业工伤比例', key: 'companyWorkInjuryPercentage', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.companyWorkInjuryPercentage),
              ]);
            }
          },
          {title: '比例开始月份', key: 'startMonth', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.startMonth),
              ]);
            }
          },
          {title: '比例截止月份', key: 'endMonth', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.endMonth),
              ]);
            }
          },
        ],

        isShowNotes: false,
      }
    },
    mounted() {
      this.setCompanySocialSecurity()
    },
    computed: {
      ...mapGetters('companySocialSecurity',[
        'companysocialsecurity'
      ])
    },
    methods: {
      ...mapActions('companySocialSecurity', {
        setCompanySocialSecurity: eventType.COMPANYSOCIALSECURITYTYPE
      }),
      goBack() {
        this.$router.push({name: 'companysocialsecuritymanage'})
      },
      ok () {

      },
      cancel () {

      }
    }
  }
</script>

<style scoped>
  .mt20 {
    margin-top: 20px;
  }
</style>
