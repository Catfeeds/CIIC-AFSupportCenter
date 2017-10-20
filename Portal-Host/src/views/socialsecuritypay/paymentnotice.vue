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
                  <label>{{noticeInfo.companyName}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="公司名称：">
                  <label>{{noticeInfo.companySocialSecurityAccount}}</label>
                </Form-item>
              </Col>
            </Row>
            <Table border :columns="noticeInfo.noticeColumns" :data="noticeInfo.noticeData"></Table>
            <Row class="mt20">
              <Col :xs="{span: 8}" :lg="{span: 8}">
                <Form-item label="应缴纳合计（小写）：">
                  <label>{{noticeInfo.shouldPayAmount}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="调整金额（小写）：">
                  <label>{{noticeInfo.changeAmount}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8}" :lg="{span: 8}">
                <Form-item label="申请支付金额合计（小写）：">
                  <label>{{noticeInfo.applyAmountLower}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="调整金额（小写）：">
                  <label>{{noticeInfo.applyAmountUpper}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8}" :lg="{span: 8}">
                <Form-item label="申请支付金额合计（小写）：">
                  <label>{{noticeInfo.notes}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 4, offset: 20}" :lg="{span: 4, offset: 20}">
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
  import customerModal from '../commoncontrol/customermodal.vue'
  import progressBar from '../commoncontrol/progress/progressbar.vue'
  const progressStop = 33.3;

  export default {
    name: 'socialsecuritypay',
    components: {customerModal, progressBar},
    data() {
      return{
        collapseInfo: [1], //展开栏
        noticeInfo: {
          companyName: '欧莱雅分公司1',
          companySocialSecurityAccount: '1144513312',
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
          noticeData: [
            {index: '1', project: '单位应缴纳社保费', basePensionInsurance: '', baseMedicalInsurance: '', areaAddMedicalInsurance: '', unemploymentInsurance: '', injuryInsurance: '', fertilityInsurance: ''},
            {index: '2', project: '单位应补缴历年社保费', basePensionInsurance: '', baseMedicalInsurance: '', areaAddMedicalInsurance: '', unemploymentInsurance: '', injuryInsurance: '', fertilityInsurance: ''},
            {index: '3', project: '个人应缴纳社会保险费', basePensionInsurance: '', baseMedicalInsurance: '', areaAddMedicalInsurance: '', unemploymentInsurance: '', injuryInsurance: '', fertilityInsurance: ''},
            {index: '4', project: '个人应补缴历月社会保险费', basePensionInsurance: '', baseMedicalInsurance: '', areaAddMedicalInsurance: '', unemploymentInsurance: '', injuryInsurance: '', fertilityInsurance: ''},
            {index: '5', project: '其他应缴纳社会保险费', basePensionInsurance: '', baseMedicalInsurance: '', areaAddMedicalInsurance: '', unemploymentInsurance: '', injuryInsurance: '', fertilityInsurance: ''},
            {index: '6', project: '预缴社保保险费', basePensionInsurance: '', baseMedicalInsurance: '', areaAddMedicalInsurance: '', unemploymentInsurance: '', injuryInsurance: '', fertilityInsurance: ''},
            {index: '7', project: '', basePensionInsurance: '', baseMedicalInsurance: '', areaAddMedicalInsurance: '', unemploymentInsurance: '', injuryInsurance: '', fertilityInsurance: ''},
            {index: '8', project: '单位缓缴社保保险费', basePensionInsurance: '', baseMedicalInsurance: '', areaAddMedicalInsurance: '', unemploymentInsurance: '', injuryInsurance: '', fertilityInsurance: ''},
            {index: '9', project: '缴纳合计（1+2+3+4+5+6-8）', basePensionInsurance: '', baseMedicalInsurance: '', areaAddMedicalInsurance: '', unemploymentInsurance: '', injuryInsurance: '', fertilityInsurance: ''}
          ],
          shouldPayAmount: '',
          changeAmount: '',
          applyAmountLower: '',
          applyAmountUpper: '',
          notes: '滞纳金 232.99元'
        }
      }
    },
    computed: {

    },
    methods: {
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
  .ml10 {margin-left: 10px;}
</style>
