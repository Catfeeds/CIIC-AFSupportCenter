<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        社保支付
        <div slot="content">
          <Form ref="payInfo" :model="payInfo" :label-width=120>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="企业社保账户分类：" prop="companySocialSecurityCategoryValue">
                  <Select v-model="payInfo.companySocialSecurityCategoryValue" style="width: 100%;">
                    <Option v-for="item in payInfo.companySocialSecurityCategoryList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="企业社保账户：" prop="companySocialSecurityAccount">
                  <Input v-model="payInfo.companySocialSecurityAccount" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="支付年月：" prop="payDate">
                  <DatePicker v-model="payInfo.payDate" type="daterange" placement="bottom" placeholder="选择日期" style="width: 100%;"></DatePicker>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="支付状态：" prop="payStateValue">
                  <Select v-model="payInfo.payStateValue" style="width: 100%;">
                    <Option v-for="item in payInfo.payStateList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="客户编号：" prop="customerNumber">
                  <Input v-model="payInfo.customerNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="客户名称：" prop="customerName">
                  <Input v-model="payInfo.customerName" @on-focus="isShowCustomerName = true" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 3, offset: 16}" :lg="{span: 3, offset: 16}">
                <Button type="primary" @click="" icon="ios-search">查询</Button>
                <Button type="ghost" @click="resetSearchCondition('payInfo')">重置</Button>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
    </Collapse>

    <Form>
      <Row class="mt20">
        <Col :xs="{span: 10}" :lg="{span: 10}">
          <Button type="default" @click="gotoPay">申请支付</Button>
          <Button type="default" @click="goPaymentNotice">查看付款通知书</Button>
          <Button type="info" @click="" >导出</Button>
        </Col>
      </Row>

      <Row class="mt20">
        <Col :xs="{span: 24}" :lg="{span: 24}">
          <Table border :columns="payColumns" :data="socialsecuritypay.payData"></Table>
          <Page :total="4" :page-size="5" :page-size-opts="[5, 10]" show-sizer show-total  class="pageSize"></Page>
        </Col>
      </Row>
    </Form>

    <!-- 客户名称 模态框 -->
    <Modal
      v-model="isShowCustomerName"
      title="选择客户"
      @on-ok="ok"
      @on-cancel="cancel">
      <customer-modal :customerData="socialsecuritypay.customerData"></customer-modal>
    </Modal>

    <!-- 进度 -->
    <Modal
      v-model="isShowProgress"
      width="680"
      title="查看进度"
      @on-ok="ok"
      @on-cancel="cancel">
      <progress-bar></progress-bar>
    </Modal>

    <!-- 调整 -->
    <Modal
      v-model="changeInfo.isShowChange"
      @on-ok="ok"
      @on-cancel="cancel">
      <Table border :columns="changeInfo.changeColumns" :data="socialsecuritypay.changeData"></Table>
      <Form :label-width=180>
        <Row class="mt20">
          <Col :xs="{span: 2}" :lg="{span: 2}">
            <Button type="primary" @click="changeInfo.isImport = true">导入调整明细</Button>
          </Col>
        </Row>
        <Row class="mt20">
          <Col :xs="{span: 24}" :lg="{span: 24}">
            <Form-item label="调整金（小写）：">
              <Input v-model="changeInfo.changeAmount" placeholder="请输入..."></Input>
            </Form-item>
          </Col>
        </Row>
        <Row>
          <Col :xs="{span: 24}" :lg="{span: 24}">
            <Form-item>
              <Checkbox v-model="changeInfo.isDeductible">抵扣费用是否纳入支付申请</Checkbox>
            </Form-item>
          </Col>
        </Row>
        <Row>
          <Col :xs="{span: 24}" :lg="{span: 24}">
            <Form-item label="申请支付金额合计（小写）：">
              <label>{{changeInfo.applyAmountLower}}</label>
            </Form-item>
          </Col>
        </Row>
        <Row>
          <Col :xs="{span: 24}" :lg="{span: 24}">
            <Form-item label="申请支付金额合计（大写）：">
              <label>{{changeInfo.applyAmountUpper}}</label>
            </Form-item>
          </Col>
        </Row>
        <Row>
          <Col :xs="{span: 24}" :lg="{span: 24}">
            <Form-item label="备注说明：">
              <Input v-model="changeInfo.notes" type="textarea" :rows="5" placeholder="请输入..."></Input>
            </Form-item>
          </Col>
        </Row>
      </Form>
    </Modal>

    <Modal
      v-model="changeInfo.isImport"
      @on-ok="ok"
      @on-cancel="cancel">

      <Form :label-width=120>
        <Row>
          <Col :xs="{span: 24}" :lg="{span: 24}">
            <Upload action="//jsonplaceholder.typicode.com/posts/">
              <Button type="ghost" icon="ios-cloud-upload-outline">上传文件</Button>
            </Upload>
          </Col>
        </Row>
        <Row class="mt20">
          <Col :xs="{span: 24}" :lg="{span: 24}">
            <Form-item label="企业名称：">
              上海中智经济合作技术有限公司
            </Form-item>
          </Col>
        </Row>
      </Form>
    </Modal>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import customerModal from '../commoncontrol/customermodal.vue'
  import progressBar from '../commoncontrol/progress/progressbar.vue'
  import eventType from '../../store/EventTypes'

  const progressStop = 33.3;

  export default {
    components: {customerModal, progressBar},
    data() {
      return{
        collapseInfo: [1], //展开栏
        payInfo: {
          companySocialSecurityCategoryValue: '',
          companySocialSecurityCategoryList: [
            {value: '1', label: '中智大库'},
            {value: '2', label: '中智外包'},
            {value: '3', label: '独立户'}
          ],
          companySocialSecurityAccount: '',
          payDate: '',
          payStateValue: '',
          payStateList: [
            {value: '1', label: '初始'},
            {value: '2', label: '申请中(内部审批中)'},
            {value: '3', label: '内部批退'},
            {value: '4', label: '申请中(财务审批中)'},
            {value: '5', label: '财务批退'},
            {value: '6', label: '支付成功'},
          ],
          customerNumber: '',
          customerName: ''
        },

        isShowCustomerName: false,
        isShowProgress: false,
        progressStop: progressStop,

        changeInfo: {
          isShowChange: false,
          changeColumns: [
            {title: '应缴纳合计', key: 'shouldPayAmount', align: 'center',
              render: (h, params) => {
                return h('div', {style: {textAlign: 'right'}}, [
                  h('span', params.row.shouldPayAmount),
                ]);
              }
            },
            {title: '抵扣费用', key: 'deductibleFee', align: 'center',
              render: (h, params) => {
                return h('div', {style: {textAlign: 'right'}}, [
                  h('span', params.row.deductibleFee),
                ]);
              }
            }
          ],

          changeAmount: '',
          isDeductible: false,
          applyAmountLower: '',
          applyAmountUpper: '',
          notes: '',
          isImport: false
        },

        payColumns: [
          {title: '', key: 'id', width: 55, fixed: 'left', type: 'selection'},
          {title: '客户编号', key: 'customerNumber', width: 120, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.customerNumber),
              ]);
            }
          },
          {title: '客户名称', key: 'customerName', width: 250, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.accountType),
              ]);
            }
          },
          {title: '企业社保账户', key: 'companySocialSecurityAccount', width: 180, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.companySocialSecurityAccount),
              ]);
            }
          },
          {title: '支付年月', key: 'payDate', width: 180, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.payDate),
              ]);
            }
          },
          {title: '支付状态', key: 'payState', width: 180, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.payState),
              ]);
            }
          },
          {title: '操作', key: 'operator', width: 180, align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {type: 'primary', size: 'small'},
                  style: {margin: '0 auto'},
                  on: {
                    click: () => {
                      this.changeInfo.isShowChange = true;
                    }
                  }
                }, '调整'),
                h('Button', {
                  props: {type: 'primary', size: 'small'},
                  style: {margin: '0 auto 0 10px'},
                  on: {
                    click: () => {
                      this.isShowProgress = true;
                    }
                  }
                }, '进度')
              ]);
            }
          },
          {title: '应缴纳金额', key: 'shouldPayAmount', width: 130, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.shouldPayAmount),
              ]);
            }
          },
          {title: '申请支付总金额', key: 'applyPayAmount', width: 130, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.applyPayAmount),
              ]);
            }
          },
          {title: '调整金额', key: 'changeAmount', width: 130, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.changeAmount),
              ]);
            }
          },
          {title: '抵扣费用', key: 'deductibleFee', width: 130, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.deductibleFee),
              ]);
            }
          },
          {title: '企业部分金额', key: 'companyPart', width: 130, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.companyPart),
              ]);
            }
          },
          {title: '雇员部分金额', key: 'employeePart', width: 130, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.employeePart),
              ]);
            }
          },
          {title: '申请人', key: 'applier', width: 120, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.applier),
              ]);
            }
          },
          {title: '申请时间', key: 'applyTime', width: 180, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.applyTime),
              ]);
            }
          },
          {title: '财务支付日期', key: 'payDate', width: 180, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.payDate),
              ]);
            }
          },
          {title: '申请备注', key: 'applyNotes', width: 250, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.applyNotes),
              ]);
            }
          }
        ],
      }
    },
    mounted() {
      this.setSocialSecurityPay()
    },
    computed: {
      ...mapGetters('socialSecurityPay', [
        'socialsecuritypay'
      ])
    },
    methods: {
      ...mapActions('socialSecurityPay', {
        setSocialSecurityPay: eventType.SOCIALSECURITYPAYTYPE
      }),
      resetSearchCondition(name) {
        this.$refs[name].resetFields()
      },
      gotoPay() {
        this.$Notice.success({
          title: '支付申请操作成功！'
        });
      },
      goPaymentNotice() {
        this.$router.push({name: 'paymentnotice'})
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
</style>
