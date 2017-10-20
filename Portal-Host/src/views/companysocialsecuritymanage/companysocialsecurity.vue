<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        企业社保账号信息
        <div slot="content">
          <Form :label-width=120>
            <Row class="mt20">
              <Col :xs="{span: 3, offset: 1}" :lg="{span: 3, offset: 1}">
                <Form-item label="参保户登记码：" class="">
                  <label>{{companySocialSecurityInfo.participantRegistrationCode}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="牡丹卡号：" class="">
                  <label>{{companySocialSecurityInfo.ICBCNumber}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="养老金用公司名称：" class="">
                  <label>{{companySocialSecurityInfo.pensionCompanyName}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Form-item label="结算区县：" class="">
                  <label>{{companySocialSecurityInfo.region}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row class="mt20">
              <Col :xs="{span: 3, offset: 1}" :lg="{span: 3, offset: 1}">
                <Form-item label="付款行：" class="">
                  <label>{{companySocialSecurityInfo.paymentBank}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="付款方式：" class="">
                  <Select v-model="companySocialSecurityInfo.payMethodValue" disabled style="width: 100%;">
                    <Option v-for="item in companySocialSecurityInfo.payMethodList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="工行查询账号：" class="">
                  <label>{{companySocialSecurityInfo.ICBCSearchAccount}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Form-item label="客户社保截至日：" class="">
                  <label>{{companySocialSecurityInfo.companySocialSecurityEndDate}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row class="mt20">
              <Col :xs="{span: 3, offset: 1}" :lg="{span: 3, offset: 1}">
                <Form-item label="养老金独立开户用户名：" class="">
                  <label>{{companySocialSecurityInfo.pensionUsername}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="养老金独立开户密码：" class="">
                  <label>{{companySocialSecurityInfo.pensionPassword}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="初期余额：" class="">
                  <label>{{companySocialSecurityInfo.originalMoney}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Form-item label="初期欠费：" class="">
                  <label>{{companySocialSecurityInfo.originalArrears}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row class="mt20">
              <Col :xs="{span: 3, offset: 1}" :lg="{span: 3, offset: 1}">
                <Form-item label="来源地：" class="">
                  <label>{{companySocialSecurityInfo.resource}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="来源地备注：" class="">
                  <label>{{companySocialSecurityInfo.resourceNotes}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="交予方式：" class="">
                  <label>{{companySocialSecurityInfo.toMethod}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Form-item label="交予方式备注：" class="">
                  <label>{{companySocialSecurityInfo.toMethodNotes}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row class="mt20">
              <Col :xs="{span: 3, offset: 1}" :lg="{span: 3, offset: 1}">
                <Form-item label="给凭证时间：" class="">
                  <label>{{companySocialSecurityInfo.toProofTime}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="变更时间：" class="">
                  <label>{{companySocialSecurityInfo.changeTime}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="收到日期：" class="">
                  <label>{{companySocialSecurityInfo.accpetDate}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Form-item label="转入日期：" class="">
                  <label>{{companySocialSecurityInfo.turnInDate}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row class="mt20">
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
            <Row class="mt20">
              <Col :xs="{span: 3, offset: 1}" :lg="{span: 3, offset: 1}">
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
          <Table width="841" border :columns="childCompanyColumns" :data="childCompanyData"></Table>
        </div>
      </Panel>

      <Panel name="3">
        历史任务单
        <div slot="content">
          <Table width="841" border :columns="historyTaskColumns" :data="historyTaskData"></Table>
        </div>
      </Panel>

      <Panel name="4">
        工伤比例变更历史
        <div slot="content">
          <Table width="841" border :columns="workInjuryColumns" :data="workInjuryData"></Table>
        </div>
      </Panel>
    </Collapse>
    <Row class="mt20">
      <Col :xs="{span: 2, offset: 10}" :lg="{span: 2, offset: 10}">
        <Button type="default" @click="goBack">返回</Button>
      </Col>
    </Row>

    <!-- 对话备注 模态框 -->
    <Modal
      v-model="isShowNotes"
      @on-ok="ok"
      @on-cancel="cancel">
      <chat :chatList="chatList"></chat>
    </Modal>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import chat from '../commoncontrol/chathistory/chat.vue'

  export default {
    name:"companysocialsecurity",
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
        childCompanyData: [
          {companyCode: 'KH00001', companyName: '上海XX信息技术有限公司', serviceCenter: '大客户2', serviceTeam: '龚艳社保组'},
          {companyCode: 'KH00002', companyName: '广州XX信息技术有限公司', serviceCenter: '大客户2', serviceTeam: '龚艳社保组'}
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
        historyTaskData: [
          {tid: 'XK00001', taskType: '开户', operator: '龚艳', operatorTime: '2015-06-01', notes: ''},
          {tid: 'BG00001', taskType: '变更', operator: '龚艳', operatorTime: '2016-06-01', notes: ''},
          {tid: 'BG00002', taskType: '终止', operator: '龚艳', operatorTime: '2015-06-01', notes: ''}
        ], //变动历史

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
        workInjuryData: [
          {industryCategory: '一', companyWorkInjuryPercentage: '0.02', startMonth: '201406', endMonth: '201704'},
          {industryCategory: '二', companyWorkInjuryPercentage: '0.03', startMonth: '201705', endMonth: ''}
        ],
        isShowNotes: false,
        chatList: [
          {icon: '', name: '客服', date: '2017-03-02 14:14:32', content: '【发起】该客户要求本月所有员工都缴纳社保。'}
        ]
      }
    },
    mounted() {

    },
    computed: {

    },
    methods: {
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
