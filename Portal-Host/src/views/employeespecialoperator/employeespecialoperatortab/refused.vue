<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        雇员特殊操作
        <div slot="content">
          <Form :label-width=120 ref="operatorSearchData" :model="operatorSearchData">
            <Row class="mt20">
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="服务中心：" prop="serviceCenterValue">
                  <Select v-model="operatorSearchData.serviceCenterValue" style="width: 100%;">
                    <Option v-for="item in operatorSearchData.serviceCenterList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="客户编号：" prop="customerNumber">
                  <Input v-model="operatorSearchData.customerNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="客户名称：" prop="customerName">
                  <Input v-model="operatorSearchData.customerName" @on-focus="operatorSearchData.isShowCustomerName = true" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="账户类型：" prop="accountTypeValue">
                  <Select v-model="operatorSearchData.accountTypeValue" style="width: 100%;">
                    <Option v-for="item in operatorSearchData.accountTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="企业社保账户分类：" prop="companyAccountType">
                  <Input v-model="operatorSearchData.companyAccountType" @on-focus="operatorSearchData.isShowAccountType = true" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="人员分类：" prop="personTypeValue">
                  <Select v-model="operatorSearchData.personTypeValue" style="width: 100%;">
                    <Option v-for="item in operatorSearchData.personTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="雇员编号：" prop="employeeNumber">
                  <Input v-model="operatorSearchData.employeeNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="雇员姓名：" prop="employeeName">
                  <Input v-model="operatorSearchData.employeeName" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="身份证号：" prop="idNumber">
                  <Input v-model="operatorSearchData.idNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="任务单类型：" prop="taskTypeValue">
                  <Select v-model="operatorSearchData.taskTypeValue" style="width: 100%;">
                    <Option v-for="item in operatorSearchData.taskTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="结算中心：" prop="region">
                  <Select v-model="operatorSearchData.region" style="width: 100%;">
                    <Option v-for="item in operatorSearchData.regionList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="是否加急：" prop="emergency">
                  <Select v-model="operatorSearchData.emergency" style="width: 100%;">
                    <Option v-for="item in operatorSearchData.emergencyList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="任务发起时间：" prop="taskStartTime">
                  <DatePicker v-model="operatorSearchData.taskStartTime" type="daterange" placement="bottom" placeholder="选择日期" style="width: 100%;"></DatePicker>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 4, offset: 16}" :lg="{span: 4, offset: 16}">
                <Button type="primary" @click="" icon="ios-search">查询</Button>
                <Button type="default" @click="resetSearchCondition('operatorSearchData')">重置</Button>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
    </Collapse>

    <Form>
      <Row class="mt20">
        <Col :xs="{span: 1}" :lg="{span: 1}">
          <Form-item>
            <Button type="error" @click="isRefuseReason = true">批退</Button>
          </Form-item>
        </Col>
      </Row>

      <Row>
        <Col :xs="{span: 24}" :lg="{span: 24}">
          <Table border :columns="employeeResultColumns" :data="refused.employeeResultData" ref="employeeSocialSecurityData"></Table>
          <Page :total="4" :page-size="5" :page-size-opts="[5, 10]" show-sizer show-total  class="pageSize"></Page>
        </Col>
      </Row>

      <!-- 客户名称 模态框 -->
      <Modal
        v-model="operatorSearchData.isShowCustomerName"
        title="选择客户"
        @on-ok="ok"
        @on-cancel="cancel">
        <customer-modal :customerData="refused.customerData"></customer-modal>
      </Modal>

      <!-- 企业社保账户分类 模态框 -->
      <Modal
        v-model="operatorSearchData.isShowAccountType"
        title="企业社保账户分类"
        @on-ok="ok"
        @on-cancel="cancel">
        <company-account-search-modal :sSocialSecurityTypeData="refused.sSocialSecurityTypeData"></company-account-search-modal>
      </Modal>

      <!-- 批退理由 -->
      <Modal
        v-model="isRefuseReason"
        @on-ok="ok"
        @on-cancel="cancel">
        <Form-item>
          <Input v-model="refuseReason" type="textarea" :rows=4 placeholder="请填写批退备注..."></Input>
        </Form-item>
      </Modal>
    </Form>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import customerModal from '../../commoncontrol/customermodal.vue'
  import companyAccountSearchModal from '../../commoncontrol/companyaccountsearchmodal.vue'
  import eventType from '../../../store/EventTypes'

  export default {
    components: {customerModal, companyAccountSearchModal},
    data() {
      return{
        collapseInfo: [1], //展开栏

        operatorSearchData: {
          serviceCenterValue: '',
          serviceCenterList: [
            {value: 1, label: '大客户', children: [{value: '1-1', label: '大客户1'}, {value: '1-2', label: '大客户2'}]},
            {value: 2, label: '日本客户'},
            {value: 3, label: '虹桥'},
            {value: 4, label: '浦东'},
            {value: 5, label: '东区1'},
            {value: 6, label: '东区2'}
          ], //客服中心
          customerNumber: '', //客户编号
          customerName: '', //客户名称
          isShowCustomerName: false, //客户名称显示模态框
          accountTypeValue: '',
          accountTypeList: [
            {value: '1', label: '独立户'},
            {value: '2', label: '大库'},
            {value: '3', label: '外包'}
          ], //账户类型
          companyAccountType: '', //企业社保账户分类
          isShowAccountType: false, //客户名称显示模态框
          personTypeValue: '',
          personTypeList: [
            {value: '1', label: '本地'},
            {value: '2', label: '外地'},
            {value: '3', label: '外籍三险'},
            {value: '4', label: '外籍五险'}
          ], //人员分类
          employeeNumber: '', //雇员编号
          employeeName: '', //雇员姓名
          idNumber: '', //身份证号
          taskTypeValue: '',
          taskTypeList: [
            {value: '1', label: '新开转入'},
            {value: '2', label: '调整'},
            {value: '3', label: '补缴'},
            {value: '4', label: '转出'}
          ], //人员分类
          regionList: [
            {value: '1', label: '徐汇'},
            {value: '2', label: '长宁'},
            {value: '3', label: '浦东'},
            {value: '4', label: '卢湾'},
            {value: '5', label: '静安'},
            {value: '6', label: '黄浦'}
          ],
          region: '', //结算区域
          emergency: '',
          emergencyList: [
            {value: '1', label: ''},
            {value: '2', label: '加急'}
          ], //是否加急

          taskStartTime: '' //任务发起时间
        },

        isRefuseReason: false,
        refuseReason: '',

        employeeResultColumns: [
          {title: '操作', key: 'action', fixed: 'left', width: 80, align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {type: 'primary', size: 'small'},
                  style: {margin: '0 auto'},
                  on: {
                    click: () => {
                      this.$router.push({name: 'employeespecialprogress2'})
                    }
                  }
                }, '办理'),
              ]);
            }
          },
          {title: '任务单编号', key: 'tid', width: 120, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.tid),
              ]);
            }
          },
          {title: '任务单类型', key: 'type', width: 120, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.type),
              ]);
            }
          },
          {title: '是否加急', key: 'emergency', width: 100, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.emergency),
              ]);
            }
          },
          {title: '雇员', key: 'employee', width: 100, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.employee),
              ]);
            }
          },
          {title: '雇员编号', key: 'employeeId', width: 100, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.employeeId),
              ]);
            }
          },
          {title: '雇员证件号', key: 'employeeCardNumber', width: 200, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.employeeCardNumber),
              ]);
            }
          },
          {title: '企业社保账号', key: 'companySocialSecurityAccount', width: 200, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.companySocialSecurityAccount),
              ]);
            }
          },
          {title: 'UKEY密码', key: 'uKey', width: 200, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.uKey),
              ]);
            }
          },
          {title: '执行日期', key: 'doDate', width: 150, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.doDate),
              ]);
            }
          },
          {title: '客户编号', key: 'customerId', width: 100, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.customerId),
              ]);
            }
          },
          {title: '企业客户', key: 'companyCustomer', width: 100, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.companyCustomer),
              ]);
            }
          },
          {title: '完成截止日期', key: 'finishDate', width: 150, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.finishDate),
              ]);
            }
          },
          {title: '客服中心', key: 'serviceCenter', width: 150, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.serviceCenter),
              ]);
            }
          },
          {title: '客服经理', key: 'serviceManager', width: 150, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.serviceManager),
              ]);
            }
          },
          {title: '雇员分类', key: 'employeeType', width: 100, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.employeeType),
              ]);
            }
          },
          {title: '账户类型', key: 'accountType', width: 120, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.accountType),
              ]);
            }
          },
          {title: '结算区县', key: 'region', width: 100, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.region),
              ]);
            }
          },
          {title: '发起供应商', key: 'sponsor', width: 200, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.sponsor),
              ]);
            }
          },
          {title: '发起人', key: 'initiator', width: 100, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.initiator),
              ]);
            }
          },
          {title: '发起时间', key: 'sponsorTime', width: 150, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.sponsorTime),
              ]);
            }
          },
          {title: '备注', key: 'notes', width: 300, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.notes),
              ]);
            }
          }
        ]
      }
    },
    mounted() {
      this.setRefused()
    },
    computed: {
      ...mapGetters('Refused', [
        'refused'
      ])
    },
    methods: {
      ...mapActions('Refused', {
        setRefused: eventType.REFUSEDTYPE
      }),
      resetSearchCondition(name) {
        this.$refs[name].resetFields()
      },
      routerToCommcialOperator: function(name) {
        this.$router.push({
          name: 'employeecommcialoperator',
          query: {operatorType: name}
        });
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
