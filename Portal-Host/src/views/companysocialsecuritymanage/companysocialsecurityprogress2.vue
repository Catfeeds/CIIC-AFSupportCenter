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
        {{operatorType !== '1' ? '企业社保账户信息' : '公司信息'}}
        <div slot="content">
          <company-social-security-info v-if="operatorType !== '1'"></company-social-security-info>
          <company-info :companyInfo="companysocialsecurityprogress2.companyInfo" v-else></company-info>
        </div>
      </Panel>
      <Panel name="2">
        办理所需材料清单
        <div slot="content">
          <Table class="mt20" border :columns="operatorMaterials.operatorMaterialListColumns" :data="companysocialsecurityprogress2.operatorMaterialListData" ref="employeeSocialSecurityData"></Table>
        </div>
      </Panel>
    </Collapse>

    <Row class="mt20">
      <Col :xs="{span: 5}" :lg="{span: 5}">
        <Button type="error" @click="goBack">批退</Button>
        <Button type="primary" @click="nextStep">下一步</Button>
        <Button type="ghost" @click="goBack">关闭/返回</Button>
      </Col>
      <Col :xs="{span: 5, offset: 14}" :lg="{span: 5, offset: 14}">
        <Button type="primary" @click="isUpload = true">上传扫描件</Button>
        <Button type="primary" @click="goBack">反馈未签收</Button>
        <Button type="primary" @click="nextStep">签收全部材料</Button>
      </Col>
    </Row>

    <chat :chatList="companysocialsecurityprogress2.chatList" class="mt20"></chat>

    <!-- 批退理由 -->
    <Modal
      v-model="isUpload"
      @on-ok="ok"
      @on-cancel="cancel">
      <div style="text-align: center;">
        <Upload action="">
          <Button type="ghost" icon="ios-cloud-upload-outline">上传文件</Button>
        </Upload>
      </div>
    </Modal>
  </Form>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import chat from '../commoncontrol/chathistory/chat.vue'
  import companySocialSecurityInfo from '../commoncontrol/companysocialsecurityinfo.vue'
  import companyInfo from '../commoncontrol/companyinfo.vue'
  import eventType from '../../store/EventTypes'

  export default {
    components: {chat, companySocialSecurityInfo, companyInfo},
    data() {
      return {
        collapseInfo: [1, 2], //展开栏
        operatorType: this.$route.query.operatorType,
        currentStep: 2,

        operatorMaterials: {
          operatorMaterialListColumns: [
            {title: '材料名称', key: 'material', align: 'center', width: 150,
              render: (h, params) => {
                return h('div', {style: {textAlign: 'left'}}, [
                  h(params.row.isLink ? 'a' : 'span', {props: {},
                    on: {
                      click: () => {

                      }
                    }
                  }, params.row.material)
                ])
              }
            },
            {title: '材料提交时间', key: 'materialCommitDate', align: 'center', width: 180,
              render: (h, params) => {
                return h('div', {style: {textAlign: 'left'}}, [
                  h('span', params.row.materialCommitDate),
                ]);
              }
            },
            {title: '材料类型', key: 'materialType', align: 'center', width: 130,
              render: (h, params) => {
                return h('div', {style: {textAlign: 'left'}}, [
                  h(params.row.isLink ? 'a' : 'span', {props: {},
                    on: {
                      click: (event) => {
                        this.isUpload = event.target.nodeName == 'A' ? true : false;
                      }
                    }
                  }, params.row.materialType)
                ])
              }
            },
            {title: '材料收到时间', key: 'materialReciveDate', align: 'center', width: 180,
              render: (h, params) => {
                return h('div', {style: {textAlign: 'left'}}, [
                  h('span', params.row.materialReciveDate),
                ]);
              }
            },
            {title: '状态', key: 'materialReciveDate', align: 'center', width: 200,
              render: (h, params) => {
                return h('div', [
                  h('Select', {props: {value: params.index === 0 || params.index === 3 ? '3' : params.index === 1 ? '1' : '2'}},
                    [
                      h('Option', {props: {value: '1'}},'材料不齐全'),
                      h('Option', {props: {value: '2'}},'未签收'),
                      h('Option', {props: {value: '3'}},'已签收'),
                    ]
                  )]
                );
              }
            },
            {title: '操作', key: 'operator', align: 'center', width: 80,
              render: (h, params) => {
                return h('div', [
                  h(!params.row.isLink ? 'span' : 'Button', {
                    props: {type: 'primary', size: 'small'},
                    style: {margin: '0 auto'},
                    on: {
                      click: () => {

                      }
                    }
                  }, !params.row.isLink ? '' : '上传'),
                ]);
              }
            },
            {title: '备注说明', key: 'notes',
              render: (h, params) => {
                return h('div', [
                  h('i-input', {props: {value: params.row.notes}})
                ]);
              }
            }
          ],

        },
        isUpload: false,
      }
    },
    mounted() {
      this.setCompanySocialSecurityProgress2()
    },
    computed: {
      ...mapGetters('companySocialSecurityProgress2',[
        'companysocialsecurityprogress2'
      ])
    },
    methods: {
      ...mapActions('companySocialSecurityProgress2', {
        setCompanySocialSecurityProgress2: eventType.COMPANYSOCIALSECURITYPROGRESS2TYPE
      }),
      nextStep() {
        if(this.operatorType === '1') {
          this.$router.push({name: 'companysocialsecuritytypeinfo'})
        } else {
          this.operatorType === '2' ? this.$router.push({name: 'companysocialsecuritychangeinfo'}) : this.$router.push({name: 'companysocialsecurityendinfo'})
        }
      },
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
  .mt20 {margin-top: 20px;}
</style>
