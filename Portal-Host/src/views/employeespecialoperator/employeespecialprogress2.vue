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
        办理所需材料清单
        <div slot="content">
          <Table class="mt20" border :columns="operatorMaterials.operatorMaterialListColumns" :data="operatorMaterials.operatorMaterialListData" ref="employeeSocialSecurityData"></Table>
        </div>
      </Panel>
    </Collapse>

    <Row class="mt20">
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="error" @click="goBack">批退</Button>
      </Col>
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="primary" @click="nextStep">下一步</Button>
      </Col>
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="ghost" @click="goBack" class="ml10">关闭/返回</Button>
      </Col>
      <Col :xs="{span: 2, offset: 15}" :lg="{span: 2, offset: 15}">
        <Button type="primary" @click="isUpload = true">上传扫描件</Button>
      </Col>
      <Col :xs="{span: 2}" :lg="{span: 2}">
        <Button type="primary" @click="goBack">反馈未签收</Button>
      </Col>
      <Col :xs="{span: 2}" :lg="{span: 2}">
        <Button type="primary" @click="nextStep">签收全部材料</Button>
      </Col>
    </Row>

    <chat :chatList="chatList" class="mt20"></chat>

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
  import chat from '../commoncontrol/chathistory/chat.vue'
  import companySocialSecurityInfo from '../commoncontrol/companysocialsecurityinfo.vue'
  import employeeInfo from '../commoncontrol/employeeinfo.vue'
  export default {
    name:"employeespecialprogress2",
    components: {chat, companySocialSecurityInfo, employeeInfo},
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
          operatorMaterialListData: [
            {isLink: false, material: '材料1', materialCommitDate: '2017-7-3 12:33:33', materialType: '原件', materialReciveDate: '2017-7-5 12:33:33', state: '1', operator: '', notes: ''},
            {isLink: false, material: '材料2', materialCommitDate: '2017-7-3 12:33:33', materialType: '复印件', materialReciveDate: '', state: '1', operator: '', notes: ''},
            {isLink: true, material: '材料3', materialCommitDate: '2017-7-3 12:33:33', materialType: '扫描件', materialReciveDate: '', state: '1', operator: '', notes: ''},
            {isLink: false, material: '材料4', materialCommitDate: '2017-7-3 12:33:33', materialType: '', materialReciveDate: '2017-7-5 12:33:33', state: '1', operator: '', notes: ''}
          ]
        },
        isUpload: false,

        chatList: [
          {icon: '#', name: '社保专员', date: '2017-03-02 14:14:32', content: '材料不齐全：材料1(备注内容)； 未签收：材料2(备注内容)'},
          {icon: '#', name: '客服专员', date: '2017-03-05 13:16:52', content: '已阅'},
          {icon: '#', name: '社保专员', date: '2017-03-06 13:16:52', content: '材料1'}
        ]
      }
    },
    mounted() {

    },
    computed: {

    },
    methods: {
      nextStep() {
        this.$router.push({name: 'employeespecialprogress3'});
      },
      goBack() {
        this.$router.push({name: 'employeespecialoperatorview'})
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
  .mt20 {margin-top: 20px;}
  .ml10 {margin-left: 10px}
</style>
