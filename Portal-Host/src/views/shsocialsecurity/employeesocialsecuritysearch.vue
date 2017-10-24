<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        雇员社保查询
        <div slot="content">
          <Form :label-width=120 ref="searchCondition" :model="searchCondition">
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="服务中心：" prop="serviceCenterValue">
                  <Cascader :data="serviceCenterData" v-model="searchCondition.serviceCenterValue" trigger="hover"></Cascader>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="客户编号：" prop="customerNumber">
                  <Input v-model="searchCondition.customerNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="客户名称：" prop="customerName">
                  <Input v-model="searchCondition.customerName" @on-focus="isShowCustomerName = true" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="企业社保账户分类：" prop="companyAccountType">
                  <Input v-model="searchCondition.companyAccountType" @on-focus="isShowAccountType = true" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="结算区域：" prop="region">
                  <Select v-model="searchCondition.region">
                    <Option v-for="item in regionList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="账户类型：" prop="accountTypeValue">
                  <Select v-model="searchCondition.accountTypeValue" style="width: 100%;">
                    <Option v-for="item in accountTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="雇员编号：" prop="employeeNumber">
                  <Input v-model="searchCondition.employeeNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="雇员姓名：" prop="employeeName">
                  <Input v-model="searchCondition.employeeName" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="身份证号：" prop="idNumber">
                  <Input v-model="searchCondition.idNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="社保状态：" prop="sSecurityState">
                  <Select v-model="searchCondition.sSecurityState" style="width: 100%;">
                    <Option v-for="item in sSecurityStateList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{ span: 8, offset: 1}">
                <Form-item label="人员分类：" prop="personTypeValue">
                  <Select v-model="searchCondition.personTypeValue" style="width: 100%;">
                    <Option v-for="item in personTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 3, offset: 16}" :lg="{ span: 3, offset: 16}">
                <Button type="primary" @click="" icon="ios-search">查询</Button>
                <Button type="default" @click="resetSearchCondition('searchCondition')">重置</Button>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
    </Collapse>

    <div class="create">
      <Button type="info" @click="exportData" style="margin: 20px 0 10px 0;">导出</Button>
      <Table border :columns="employeeSocialSecurityColumns" :data="employeesocialsecuritysearch.employeeSocialSecurityData" ref="employeeSocialSecurityData"></Table>
    </div>
    <Page :total="100" show-sizer show-elevator></Page>


    <!-- 客户名称 模态框 -->
    <Modal
      v-model="isShowCustomerName"
      title="选择客户"
      width="720"
      @on-ok="ok"
      @on-cancel="cancel">
      <customer-modal :customerData="employeesocialsecuritysearch.customerData"></customer-modal>
    </Modal>

    <!-- 企业社保账户分类 模态框 -->
    <Modal
      v-model="isShowAccountType"
      title="企业社保账户分类"
      width="720"
      @on-ok="ok"
      @on-cancel="cancel">
      <company-account-search-modal :sSocialSecurityTypeData="employeesocialsecuritysearch.sSocialSecurityTypeData"></company-account-search-modal>
    </Modal>
  </div>


</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import customerModal from "../commoncontrol/customermodal.vue"
  import companyAccountSearchModal from "../commoncontrol/companyaccountsearchmodal.vue"
  import ICol from "../../../node_modules/iview/src/components/grid/col";
  import EventTypes from '../../store/EventTypes'

  export default {
    name:"employeesocialsecuritysearch",
    components: {ICol, customerModal, companyAccountSearchModal},
    data() {
      return {
        collapseInfo: [1, 2, 3], //展开栏
        searchCondition: {
          serviceCenterValue: [],
          customerNumber: '', //客户编号
          customerName: '', //客户名称
          companyAccountType: '', //企业社保账户分类
          region: '', //结算区域
          accountTypeValue: '',
          employeeNumber: '', //雇员编号
          employeeName: '', //雇员姓名
          idNumber: '', //身份证号
          sSecurityState: '',
          personTypeValue: ''
        },

        isShowCustomerName: false, //客户名称Modal
        isShowAccountType: false, //企业社保账户分类Modal

        orderNumber: '', //任务单编号
        orderStartTime: '', //任务开始时间

        serviceCenterData: [
          {value: 1, label: '大客户', children: [{value: '1-1', label: '大客户1'}, {value: '1-2', label: '大客户2'}]},
          {value: 2, label: '日本客户'},
          {value: 3, label: '虹桥'},
          {value: 4, label: '浦东'},
          {value: 5, label: '东区1'},
          {value: 6, label: '东区2'}
        ], //服务中心

        regionList: [
          {value: '1', label: '徐汇'},
          {value: '2', label: '长宁'},
          {value: '3', label: '浦东'},
          {value: '4', label: '卢湾'},
          {value: '5', label: '静安'},
          {value: '6', label: '黄浦'}
        ],

        sSecurityStateList: [
          {value: '1', label: '已办'},
          {value: '2', label: '已做'},
          {value: '3', label: '转出(失业)'},
        ], //社保状态

        isShowCustomerName: false, //客户名称显示模态框
        mCustomerNumber: '', //客户编号
        mCustomerName: '', //客户姓名

        accountTypeList: [
          {value: '1', label: '独立户'},
          {value: '2', label: '大库'},
          {value: '3', label: '外包'}
        ], //账户类型

        personTypeList: [
          {value: '1', label: '本地'},
          {value: '2', label: '外地'},
          {value: '3', label: '外籍三险'},
          {value: '4', label: '外籍五险'}
        ], //人员分类

        employeeSocialSecurityColumns: [
          {
            title: '操作',
            key: 'action',
            align: 'center',
            width: 120,
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {type: 'primary', size: 'small'},
                  style: {margin: '0 auto'},
                  on: {
                    click: () => {
                      this.showInfo(params.index)
                    }
                  }
                }, '查看'),
              ]);
            }
          },
          {title: '雇员编码', key: 'enumber', align: 'center', width: 120,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.enumber),
              ]);
            }
          },
          {title: '雇员姓名', key: 'ename', align: 'center', width: 140,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.ename),
              ]);
            }
          },
          {title: '证件号', key: 'eidno', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.eidno),
              ]);
            }
          },
          {title: '状态', key: 'estate', align: 'center', width: 120,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.eidno),
              ]);
            }
          },
          {title: '客服中心', key: 'eservicercenter', align: 'center', width: 250,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.eidno),
              ]);
            }
          },
          {title: '客服经理', key: 'eservicer', align: 'center', width: 120,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.eservicer),
              ]);
            }
          },
          {title: '账户类型', key: 'eaccounttype', align: 'center', width: 120,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.eaccounttype),
              ]);
            }
          },
          {title: '结算区县', key: 'eregion', align: 'center', width: 200,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.eregion),
              ]);
            }
          }
        ]
      }
    },
    mounted() {
      this.setEmployeeSocialSecuritySearch()
    },
    computed: {
      ...mapGetters('employeeSocialSecuritySearch',[
        'employeesocialsecuritysearch'
      ])
    },
    methods: {
      ...mapActions('employeeSocialSecuritySearch',{
        setEmployeeSocialSecuritySearch: EventTypes.EMPLOYEESOCIALSECURITYSEARCH
      }),
      exportData() {
        this.$refs.employeeSocialSecurityData.exportCsv({
          filename: '原始数据'
        });
      },
      resetSearchCondition(name) {
        this.$refs[name].resetFields()
      },
      showInfo () {
        this.$router.push({name:'employeesocialsecurityinfo'});
      },
      ok () {

      },
      cancel () {

      }
    }
  }

</script>
<style scoped>
  .ivu-card {
    background: rgba(246, 246, 246, 1);
  }

  .checkBtn .ivu-btn {
    float: right;
  }
</style>
