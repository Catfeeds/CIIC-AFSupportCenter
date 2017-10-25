<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        社保支付
        <div slot="content">
          <Form :label-width=180>
            <Row>
              <Col :xs="{span: 8}" :lg="{span: 8}">
                <Form-item label="公司名称：">
                  <label>{{paymentnotice.companyName}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="公司名称：">
                  <label>{{paymentnotice.companySocialSecurityAccount}}</label>
                </Form-item>
              </Col>
            </Row>
            <Table border :columns="noticeInfo.noticeColumns" :data="paymentnotice.noticeData"></Table>
            <Row class="mt20">
              <Col :xs="{span: 8}" :lg="{span: 8}">
                <Form-item label="应缴纳合计（小写）：">
                  <label>{{paymentnotice.shouldPayAmount}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="调整金额（小写）：">
                  <label>{{paymentnotice.changeAmount}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8}" :lg="{span: 8}">
                <Form-item label="申请支付金额合计（小写）：">
                  <label>{{paymentnotice.applyAmountLower}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="调整金额（小写）：">
                  <label>{{paymentnotice.applyAmountUpper}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8}" :lg="{span: 8}">
                <Form-item label="申请支付金额合计（小写）：">
                  <label>{{paymentnotice.notes}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 3, offset: 21}" :lg="{span: 3, offset: 21}">
                <Button type="primary" @click="" >重新汇总</Button>
                <Button type="default" @click="goBack" >返回</Button>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
    </Collapse>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import eventType from '../../store/EventTypes'

  export default {
    data() {
      return{
        collapseInfo: [1], //展开栏
        noticeInfo: {
          noticeColumns: [
            {title: '序号', key: 'index', align: 'center', width: 100,
              render: (h, params) => {
                return h('div', {style: {textAlign: 'right'}}, [
                  h('span', params.row.index),
                ]);
              }
            },
            {title: '项目', key: 'project', align: 'center', width: 240,
              render: (h, params) => {
                return h('div', {style: {textAlign: 'right'}}, [
                  h('span', params.row.project),
                ]);
              }
            },
            {title: '基本养老保险', key: 'basePensionInsurance', align: 'center',
              render: (h, params) => {
                return h('div', {style: {textAlign: 'right'}}, [
                  h('span', params.row.basePensionInsurance),
                ]);
              }
            },
            {title: '基本医疗保险', key: 'baseMedicalInsurance', align: 'center',
              render: (h, params) => {
                return h('div', {style: {textAlign: 'right'}}, [
                  h('span', params.row.baseMedicalInsurance),
                ]);
              }
            },
            {title: '地方附加医疗保险', key: 'areaAddMedicalInsurance', align: 'center',
              render: (h, params) => {
                return h('div', {style: {textAlign: 'right'}}, [
                  h('span', params.row.areaAddMedicalInsurance),
                ]);
              }
            },
            {title: '失业保险', key: 'unemploymentInsurance', align: 'center',
              render: (h, params) => {
                return h('div', {style: {textAlign: 'right'}}, [
                  h('span', params.row.unemploymentInsurance),
                ]);
              }
            },
            {title: '工伤保险', key: 'injuryInsurance', align: 'center',
              render: (h, params) => {
                return h('div', {style: {textAlign: 'right'}}, [
                  h('span', params.row.injuryInsurance),
                ]);
              }
            },
            {title: '生育保险', key: 'fertilityInsurance', align: 'center',
              render: (h, params) => {
                return h('div', {style: {textAlign: 'right'}}, [
                  h('span', params.row.fertilityInsurance),
                ]);
              }
            },
          ],
        }
      }
    },
    mounted() {
      this.setPaymentNotice()
    },
    computed: {
      ...mapGetters('paymentNotice', [
        'paymentnotice'
      ])
    },
    methods: {
      ...mapActions('paymentNotice', {
        setPaymentNotice: eventType.PAYMENTNOTICETYPE
      }),
      goBack() {
        this.$router.push({name: 'socialsecuritypay'})
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
