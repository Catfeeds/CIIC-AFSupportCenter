<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        雇员社保查询
        <div slot="content">
          <Row>
            <i-col :xs="{span: 6, offset: 1}" :lg="{ span: 6, offset: 1}">
              <Form :label-width=120>
                <Form-item label="服务中心：">
                  <Cascader :data="serviceCenterData" trigger="hover"></Cascader>
                </Form-item>
                <Form-item label="企业社保账户分类：">
                  <Button type="ghost" @click="isShowAccountType = true" long>&nbsp;</Button>
                </Form-item>
                <Form-item label="雇员编号：">
                  <Input v-model="employeeNumber" placeholder="请输入..."></Input>
                </Form-item>
                <Form-item label="社保状态：">
                  <Select v-model="sSecurityState" style="width: 100%;">
                    <Option v-for="item in sSecurityStateList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Form>
            </i-col>

            <Col :xs="{span: 6, offset: 1}" :lg="{ span: 6, offset: 1}">
              <Form :label-width=120>
                <Form-item label="客户编号：">
                  <Input v-model="customerNumber" placeholder="请输入..."></Input>
                </Form-item>
                <Form-item label="结算区域：">
                  <Select v-model="region">
                    <Option v-for="item in regionList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
                <Form-item label="雇员姓名：">
                  <Input v-model="employeeName" placeholder="请输入..."></Input>
                </Form-item>
                <Form-item label="人员分类：">
                  <Select v-model="personTypeValue" style="width: 100%;">
                    <Option v-for="item in personTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Form>
            </Col>

            <Col :xs="{span: 6, offset: 1}" :lg="{ span: 6, offset: 1}">
              <Form :label-width=120>
                <Form-item label="客户名称：">
                  <Button type="ghost" @click="isShowCustomerName = true" long>&nbsp;</Button>
                </Form-item>
                <Form-item label="账户类型：">
                  <Select v-model="accountTypeValue" style="width: 100%;">
                    <Option v-for="item in accountTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
                <Form-item label="身份证号：">
                  <Input v-model="idNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Form>
            </Col>
          </Row>
          <Row>
            <Row>
              <Col :xs="{span: 2, offset: 18}" :lg="{span: 2, offset: 18}">
                <Button type="primary" @click="" icon="ios-search">查询</Button>
              </Col>
              <Col :xs="{span: 2}" :lg="{span: 2}">
                <Button type="default" @click="">重置</Button>
              </Col>
            </Row>
          </Row>
        </div>
      </Panel>
    </Collapse>

    <div class="create">
      <Button type="info" @click="exportData" style="margin: 20px 0 10px 0;">导出</Button>
      <Table border :columns="employeeSocialSecurityColumns" :data="employeeSocialSecurityData" ref="employeeSocialSecurityData"></Table>
    </div>
    <Page :total="100" show-sizer show-elevator></Page>


    <!-- 客户名称 模态框 -->
    <Modal
      v-model="isShowCustomerName"
      title="选择客户"
      @on-ok="ok"
      @on-cancel="cancel">
      <customer-modal></customer-modal>
    </Modal>

    <!-- 企业社保账户分类 模态框 -->
    <Modal
      v-model="isShowAccountType"
      title="企业社保账户分类"
      @on-ok="ok"
      @on-cancel="cancel">
      <company-account-search-modal></company-account-search-modal>
    </Modal>
  </div>


</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import customerModal from "../commoncontrol/customermodal.vue"
  import companyAccountSearchModal from "../commoncontrol/companyaccountsearchmodal.vue"
  import ICol from "../../../node_modules/iview/src/components/grid/col";

  export default {
    name:"employeesocialsecuritysearch",
    components: {ICol, customerModal, companyAccountSearchModal},
    data() {
      return {
        collapseInfo: [1, 2, 3], //展开栏
        isShowCustomerName: false, //客户名称Modal
        isShowAccountType: false, //企业社保账户分类Modal

        serviceCenterData: [
          {value: 1, label: '大客户', children: [{value: '1-1', label: '大客户1'}, {value: '1-2', label: '大客户2'}]},
          {value: 2, label: '日本客户'},
          {value: 3, label: '虹桥'},
          {value: 4, label: '浦东'},
          {value: 5, label: '东区1'},
          {value: 6, label: '东区2'}
        ], //服务中心

        employeeNumber: '', //雇员编号

        orderNumber: '', //任务单编号

        orderStartTime: '', //任务开始时间

        customerNumber: '', //客户编号

        regionList: [
          {value: '1', label: '徐汇'},
          {value: '2', label: '长宁'},
          {value: '3', label: '浦东'},
          {value: '4', label: '卢湾'},
          {value: '5', label: '静安'},
          {value: '6', label: '黄浦'}
        ],
        region: '', //结算区域

        employeeName: '', //雇员姓名

        sSecurityState: '',
        sSecurityStateList: [
          {value: '1', label: '已办'},
          {value: '2', label: '已做'},
          {value: '3', label: '转出(失业)'},
        ], //社保状态

        isShowCustomerName: false, //客户名称显示模态框
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
        ],

        accountTypeValue: '',
        accountTypeList: [
          {value: '1', label: '独立户'},
          {value: '2', label: '大库'},
          {value: '3', label: '外包'}
        ], //账户类型

        idNumber: '', //身份证号

        personTypeValue: '',
        personTypeList: [
          {value: '1', label: '本地'},
          {value: '2', label: '外地'},
          {value: '3', label: '外籍三险'},
          {value: '4', label: '外籍五险'}
        ], //人员分类

        employeeSocialSecurityData: [
          {enumber: '10001', ename: '雇员1', eidno: '', estate: '已做', eservice: '大客户', eservicercenter: '', eaccounttype: '独立户', eregion: '徐汇'},
          {enumber: '10002', ename: '雇员2', eidno: '', estate: '已办', eservice: '日本', eservicercenter: '', eaccounttype: '中智大库', eregion: '长宁'},
          {enumber: '10003', ename: '雇员3', eidno: '', estate: '转出', eservice: '虹桥', eservicercenter: '', eaccounttype: '外包', eregion: '徐汇'},
          {enumber: '10004', ename: '雇员4', eidno: '', estate: '已办', eservice: '浦东', eservicercenter: '', eaccounttype: '中智大库', eregion: '徐汇'},
          {enumber: '10005', ename: '雇员5', eidno: '', estate: '已办', eservice: '浦东', eservicercenter: '', eaccounttype: '中智大库', eregion: '徐汇'},
          {enumber: '10006', ename: '雇员6', eidno: '', estate: '已办', eservice: '浦东', eservicercenter: '', eaccounttype: '中智大库', eregion: '徐汇'},
          {enumber: '10007', ename: '雇员7', eidno: '', estate: '已做', eservice: '徐汇', eservicercenter: '', eaccounttype: '中智大库', eregion: '徐汇'},
          {enumber: '10008', ename: '雇员8', eidno: '', estate: '已做', eservice: '徐汇', eservicercenter: '', eaccounttype: '中智大库', eregion: '徐汇'}
        ],
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

    },
    computed: {

    },
    methods: {
      exportData() {
        this.$refs.employeeSocialSecurityData.exportCsv({
          filename: '原始数据'
        });
      },
      showInfo () {
        this.$router.push({name:'employeesocialsecurityinfo'});
      },
      ok () {
        this.$Message.info('点击了确定');
      },
      cancel () {
        this.$Message.info('点击了取消');
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
