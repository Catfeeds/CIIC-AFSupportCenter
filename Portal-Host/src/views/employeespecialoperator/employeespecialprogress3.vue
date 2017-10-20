<template>
  <Form :label-width=120>
    <Steps :current="currentStep">
      <Step title="材料收集" ref="st1"></Step>
      <Step title="已受理"></Step>
      <Step title="送审中"></Step>
      <Step title="完成"></Step>
    </Steps>
    <Collapse v-model="collapseInfo" class="mt20">
      <Panel name="1">
        企业社保账户信息
        <div slot="content">
          <company-social-security-info></company-social-security-info>
        </div>
      </Panel>
      <Panel name="2">
        雇员信息
        <div slot="content">
          <employee-info :operatorType="'1'"></employee-info>
        </div>
      </Panel>
      <Panel name="3">
        社保终止操作
        <div slot="content">
          <social-security-end-operator></social-security-end-operator>
        </div>
      </Panel>
    </Collapse>

    <Row class="mt20">
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="primary" @click="goBack">确定终止</Button>
      </Col>
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="error" @click="goBack" class="ml30">批退</Button>
      </Col>
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="ghost" @click="goBack" class="ml30">返回</Button>
      </Col>
    </Row>
  </Form>
</template>
<script>
  import chat from '../commoncontrol/chathistory/chat.vue'
  import companySocialSecurityInfo from '../commoncontrol/companysocialsecurityinfo.vue'
  import employeeInfo from '../commoncontrol/employeeinfo.vue'
  import socialSecurityEndOperator from '../commoncontrol/socialsecurityendoperator.vue'
  export default {
    name:"employeespecialprogress3",
    components: {chat, companySocialSecurityInfo, employeeInfo, socialSecurityEndOperator},
    data() {
      return {
        collapseInfo: [1, 2, 3], //展开栏
        currentStep: 2,
        companyInfo: {
          customerNumber: 'KH0001',
          customerName: '上海XX信息技术有限公司',
          serviceCenter: '大客户2',
          serviceManager: '王XX'
        },
        beforeSendInfo: {
          customerSocialSecurityEndDate: '',
          payMethodValue: '',
          payMethodList: [
            {value: '1', label: '我司付款，账单到他司'},
            {value: '2', label: '自己付款，账单到我司'},
            {value: '3', label: '自己付款，账单到他司'},
            {value: '4', label: '我司付款，账单到我司'},
            {value: '5', label: '垫付'},
          ]
        },
        companyOpenAccountOperator: {
          taskValue: '',
          taskList: [
            {value: '1', label: '开户'},
            {value: '2', label: '转入'},
          ], //任务
          joinSafeguardRegister: '', //参保户登记码
          bankCardNumber: '', //牡丹卡号
          pensionMoneyUseCompanyName: '', //养老金用公司名称
          socialSecurityCenterValue: '',
          socialSecurityCenterList: [
            {value: '1', label: ''}
          ], //社保中心
          payBank: '', //付款行
          icbcSearchAccount: '', //工行查询账号
          pensionMoneySingleUserName: '', //养老金独立开户用户名
          pensionMoneySinglePassWord: '', //养老金独立开户密码
          originalSum: '', //初期余额
          originalArrears: '', //初期欠费
          resourceValue: '',
          resourceList: [
            {value: '1', label: '新开'},
            {value: '2', label: 'AF转入(大库转入)'},
            {value: '3', label: '其他供应商转入'}
          ], //来源地
          resourceNotes: '', //来源地备注
          giveMethodValue: '',
          giveMethodList: [
            {value: '1', label: '交客服'},
            {value: '2', label: '传真'},
            {value: '3', label: '邮寄'}
          ], //交予方式
          giveMethodNotes: '', //交予方式备注
          giveProofDate: '', //交予凭证时间
          changeDate: '', //变更时间
          recieveDate: '', //收到日期
          moveInDate: '', //转入日期
          belongToIndustries: '', //所属行业
          sufferedOnTheJobPercentage: '', //企业工伤比例
          sufferedOnTheJobPercentageChangeStartMonth: '', //企业工伤比例开始调整月份
          sendedMaterials: [], //发出材料
          acceptanceDate: '', //受理日期
          sendCheckDate: '', //送审日期
          finishedDate: '', //完成日期
          refuseReason: '' //批退原因
        }
      }
    },
    mounted() {
      this.$refs['st1'].onclick = function() {
          alert(123)
      }
    },
    computed: {

    },
    methods: {
      goBack() {
        this.$router.push({name:'employeespecialoperatorview'});
      }
    }
  }
</script>
<style scoped>
  .mt20 {margin-top: 20px;}
  .ml30 {margin-left: 30px;}
</style>
