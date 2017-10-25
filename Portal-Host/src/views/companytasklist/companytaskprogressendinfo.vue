<template>
  <Form :label-width=120>
    <Steps :current="currentStep">
      <Step title="材料收集"></Step>
      <Step title="已受理"></Step>
      <Step title="送审中"></Step>
      <Step title="完成"></Step>
    </Steps>
    <Collapse v-model="collapseInfo" class="mt20">
      <Panel name="1">
        企业社保账户信息
        <div slot="content">
          <company-social-security-info :company="companytaskprogresssendinfo.company"></company-social-security-info>
        </div>
      </Panel>
      <Panel name="2">
        前道传递信息
        <div slot="content">
          <Row>
            <Col :xs="{span: 10, offset: 1}" :lg="{span: 10, offset: 1}">
              <label>共{{companytaskprogresssendinfo.chatList.length}}条历史备注</label>
              <chat :chatList="companytaskprogresssendinfo.chatList" class="mt20"></chat>
            </Col>
          </Row>
        </div>
      </Panel>
      <Panel name="3">
        企业终止操作
        <div slot="content">
          <Form :label-width=100>
            <Row class="mt20">
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="受理日期：" class="">
                  <DatePicker v-model="endOperator.accpetDate" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="送审日期：" class="">
                  <DatePicker v-model="endOperator.approvalDate" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="完成日期：" class="">
                  <DatePicker v-model="endOperator.finishDate" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="终止日期：" class="">
                  <DatePicker v-model="endOperator.endDate" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
                </Form-item>
              </Col>
            </Row>
            <Row class="mt20">
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Form-item label="批退原因：" class="">
                  <Input v-model="endOperator.refuseReason" type="textarea" :rows=4 placeholder="请填写批退原因..."></Input>
                </Form-item>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
    </Collapse>

    <Row class="mt20">
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="primary" @click="goBack">办理</Button>
      </Col>
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="error" @click="goBack">批退</Button>
      </Col>
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="ghost" @click="goBack" class="ml10">返回</Button>
      </Col>
    </Row>
  </Form>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import chat from '../commoncontrol/chathistory/chat.vue'
  import companySocialSecurityInfo from '../commoncontrol/companysocialsecurityinfo.vue'
  import eventType from '../../store/EventTypes'

  export default {
    components: {chat, companySocialSecurityInfo},
    data() {
      return {
        collapseInfo: [1, 2, 3], //展开栏
        currentStep: 2,
        endOperator: {
          accpetDate: '',
          approvalDate: '',
          finishDate: '',
          endDate: '',
          refuseReason: ''
        }
      }
    },
    mounted() {
      this.setCompanyTaskProgressSendInfo()
    },
    computed: {
      ...mapGetters('companyTaskProgressSendInfo',[
        'companytaskprogresssendinfo'
      ])
    },
    methods: {
      ...mapActions('companyTaskProgressSendInfo', {
        setCompanyTaskProgressSendInfo: eventType.COMPANYTASKPROGRESSSENDINFOTYPE
      }),
      goBack() {
        this.$router.push({name: 'companytasklist'})
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
  .ml10 {margin-left: 10px}
</style>
