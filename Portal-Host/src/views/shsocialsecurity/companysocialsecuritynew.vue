<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        企业社保账户信息
        <div slot="content">
          <company-social-security-info :company="companysocialsecuritynew.company"></company-social-security-info>
        </div>
      </Panel>
      <Panel name="2">
        雇员信息
        <div slot="content">
          <employee-info :operatorType='operatorType' :employee="companysocialsecuritynew.employee"></employee-info>
        </div>
      </Panel>
      <Panel name="3">
        任务单参考信息
        <div slot="content">
          <task-refrence-info :operatorType='operatorType' :taskNewInfo="companysocialsecuritynew.taskNewInfo" :taskNewInfoData="companysocialsecuritynew.taskNewInfoData" :taskChangeInfo="companysocialsecuritynew.taskChangeInfo" :taskOutInfo="companysocialsecuritynew.taskOutInfo"></task-refrence-info>
        </div>
      </Panel>
      <Panel name="4">
        <span v-if="operatorType === '0'">社保汇缴操作</span><span v-else-if="operatorType === '1'">社保调整操作</span><span v-else>社保转出操作</span>
        <div slot="content">
          <social-security-operator :operatorType='operatorType'></social-security-operator>
        </div>
      </Panel>
    </Collapse>
    <Row class="mt20">
      <Col :xs="{span: 1, offset: 17}" :lg="{span: 1, offset: 17}" v-show="operatorType !== '2'">
        <Button type="primary" @click="">暂存</Button>
      </Col>
      <Col :xs="{span: 1}" :lg="{span: 1}" v-if="operatorType !== '2'">
        <Button type="success" @click="instance('success')">办理</Button>
      </Col>
      <Col :xs="{span: 1, offset: 17}" :lg="{span: 1, offset: 17}" v-else>
        <Button type="success" @click="instance('success')">办理</Button>
      </Col>
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="error" @click="">批退</Button>
      </Col>
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="ghost" @click="goBack">返回</Button>
      </Col>
    </Row>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import companySocialSecurityInfo from '../commoncontrol/companysocialsecurityinfo.vue'
  import employeeInfo from '../commoncontrol/employeeinfo.vue'
  import taskRefrenceInfo from './taskrefrenceinfo.vue'
  import socialSecurityOperator from '../commoncontrol/socialsecurityoperator.vue'
  import * as eventType from '../../store/EventTypes/shsocialsecurity/CompanySocialSecurityNewType'

  export default {
    name:"companysocialsecuritynew",
    components: {companySocialSecurityInfo, employeeInfo, taskRefrenceInfo, socialSecurityOperator},
    data() {
      return {
        operatorType: this.$route.query.operatorType,
        sourceFrom: this.$route.query.sourceFrom,
        collapseInfo: [1, 2, 3, 4] //展开栏
      }
    },
    mounted() {
      this.setCompanySocialSecurityNew()
    },
    computed: {
      ...mapGetters([
        'companysocialsecuritynew'
      ])
    },
    methods: {
      ...mapActions({
        setCompanySocialSecurityNew: eventType.COMPANYSOCIALSECURITYNEWTYPE
      }),
      goBack() {
        this.sourceFrom !== 'search' ? this.$router.push({name:'employeeoperatorview'}) : this.$router.push({name: 'employeesocialsecurityinfo'});
      },
      instance (type) {
        const title = '办理操作成功！';
        const content = '<p>点击确定后，将自动返回查询列表</p>';
        switch (type) {
          case 'info':
            this.$Modal.info({
              title: title,
              content: content
            });
            break;
          case 'success':
            this.$Modal.success({
              title: title,
              content: content
            });
            break;
          case 'warning':
            this.$Modal.warning({
              title: title,
              content: content
            });
            break;
          case 'error':
            this.$Modal.error({
              title: title,
              content: content
            });
            break;
        }
      }
    }
  }
</script>

<style scoped>
  .mt20 {
    margin-top: 20px;
  }
</style>
