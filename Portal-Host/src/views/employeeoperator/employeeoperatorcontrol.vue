<template>
  <div>
    <Form :label-width=120>
      <Collapse v-model="collapseInfo">
        <Panel name="1">
          雇员日常操作
          <div slot="content" >
            <operator-search></operator-search>
          </div>
        </Panel>
      </Collapse>
    </Form>

    <Form>
      <Row style="margin-top: 40px;" v-show="isCperator === 1 || isCperator === 2">
        <Col :xs="{span: 2}" :lg="{span: 2}">
          <Form-item>
            <Dropdown @on-click="routerToCommcialOperator" style="width: 100%;">
              <Button type="primary" long>
                办理
                <Icon type="arrow-down-b"></Icon>
              </Button>
              <DropdownMenu slot="list">
                <DropdownItem name="1">雇员新进、转入</DropdownItem>
                <DropdownItem name="2">雇员补缴</DropdownItem>
                <DropdownItem name="3">雇员调整</DropdownItem>
                <DropdownItem name="4">雇员转出</DropdownItem>
              </DropdownMenu>
            </Dropdown>
          </Form-item>
        </Col>
        <Col :xs="{span: 1}" :lg="{span: 1}">
          <Form-item class="ml10">
            <Button type="error" @click="isRefuseReason = true">批退</Button>
          </Form-item>
        </Col>
        <Col :xs="{span: 1}" :lg="{span: 1}">
          <Form-item class="ml10">
            <Button type="default" @click="">导出</Button>
          </Form-item>
        </Col>
      </Row>

      <Row style="margin-top: 20px;">
        <Col :xs="{span: 24}" :lg="{span: 24}">
          <Table border :columns="employeeResultColumns" :data="employeeResultData" ref="employeeSocialSecurityData"></Table>
          <Page :total="4" :page-size="5" :page-size-opts="[5, 10]" show-sizer show-total  class="pageSize"></Page>
        </Col>
      </Row>

      <!-- 批退理由 -->
      <Modal
        v-model="isRefuseReason"
        @on-ok="ok"
        @on-cancel="cancel">
        <p>
          <Input v-model="refuseReason" type="textarea" :rows=4 placeholder="请填写批退备注..."></Input>
        </p>
      </Modal>
    </Form>
  </div>
</template>
<script>
  import operatorSearch from "../commoncontrol/operatorsearch.vue"

  export default {
    name: 'employeeoperatorcontrol',
    props:{
      isCperator:{
        index: Number
      }
    },
    components: {operatorSearch},
    data() {
      return{
        collapseInfo: [1], //展开栏

        operatorValue: '',
        operatorList: [
          {value: '1', label: '雇员新进、转入'},
          {value: '2', label: '雇员补缴'},
          {value: '3', label: '雇员调整'},
          {value: '4', label: '雇员转出'}
        ], //办理

        isRefuseReason: false,
        refuseReason: '',

        employeeResultColumns: [
          {
            title: '操作',
            key: 'action',
            fixed: 'left',
            width: 80,
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {type: 'primary', size: 'small'},
                  style: {margin: '0 auto'},
                  on: {
                    click: () => {
                      switch(params.row.type) {
                        case '新进转入':
                          this.$router.push({name: 'companysocialsecuritynew', query: {operatorType: '0', sourceFrom: 'operator'}});
                          break;
                        case '调整':
                          this.$router.push({name: 'companysocialsecuritynew', query: {operatorType: '1', sourceFrom: 'operator'}});
                          break;
                        case '转出':
                          this.$router.push({name: 'companysocialsecuritynew', query: {operatorType: '2', sourceFrom: 'operator'}});
                          break;
                        case '补缴':
                          this.$router.push({name: 'companysocialsecuritynew', query: {operatorType: '4', sourceFrom: 'operator'}});
                          break;
                      }
                    }
                  }
                }, this.isCperator === 1 || this.isCperator === 2 ? '办理' : this.isCperator === 4 ? '查看' : params.row.emergency === '是' ? '查看' : '修改'),
              ]);
            }
          },
          {title: '任务单编号', key: 'tid', width: 120, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.tid),
              ]);
            }
          },
          {title: '任务单类型', key: 'type', width: 120, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.type),
              ]);
            }
          },
          {title: '是否加急', key: 'emergency', width: 100, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.emergency),
              ]);
            }
          },
          {title: '雇员', key: 'employee', width: 100, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.employee),
              ]);
            }
          },
          {title: '雇员编号', key: 'employeeId', width: 100, fixed: 'left', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.employeeId),
              ]);
            }
          },
          {title: '雇员证件号', key: 'employeeCardNumber', width: 200, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.employeeCardNumber),
              ]);
            }
          },
          {title: '企业社保账号', key: 'companySocialSecurityAccount', width: 200, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.companySocialSecurityAccount),
              ]);
            }
          },
          {title: 'UKEY密码', key: 'uKey', width: 200, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.uKey),
              ]);
            }
          },
          {title: '执行日期', key: 'doDate', width: 150, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.doDate),
              ]);
            }
          },
          {title: '客户编号', key: 'customerId', width: 100, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.customerId),
              ]);
            }
          },
          {title: '客户名称', key: 'customerName', width: 200, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.customerName),
              ]);
            }
          },
          {title: '完成截止日期', key: 'finishDate', width: 150, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.finishDate),
              ]);
            }
          },
          {title: '发起供应商', key: 'sponsor', width: 200, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.sponsor),
              ]);
            }
          },
          {title: '发起人', key: 'initiator', width: 100, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.initiator),
              ]);
            }
          },
          {title: '发起时间', key: 'sponsorTime', width: 150, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.sponsorTime),
              ]);
            }
          },
          {title: '备注', key: 'notes', width: 300, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.notes),
              ]);
            }
          }
        ],
        employeeResultData: [
          {action: '', tid: 'SS_KH_0001', type: '新进转入', emergency: '是', employee: '雇员9', employeeId: 'GY009', employeeCardNumber: '', companySocialSecurityAccount: '', uKey: '', doDate: '', customerId: '', customerName: '客户1', finishDate: '', sponsor: '中智上海', initiator: '前客服1', sponsorTime: '2017/06/01 10:05:29', notes: ''},
          {action: '', tid: 'SS_ZC_0001', type: '新进转入', emergency: '', employee: '雇员0', employeeId: 'GY000', employeeCardNumber: '', companySocialSecurityAccount: '', uKey: '', doDate: '', customerId: '', customerName: '客户1', finishDate: '', sponsor: '', initiator: '', sponsorTime: '', notes: ''},
          {action: '', tid: 'SS_XJ_0001', type: '新进转入', emergency: '是', employee: '雇员1', employeeId: 'GY001', employeeCardNumber: '', companySocialSecurityAccount: '', uKey: '', doDate: '', customerId: '', customerName: '客户3', finishDate: '', sponsor: '', initiator: '', sponsorTime: '', notes: ''},
          {action: '', tid: 'SS_ZC_0001', type: '调整', emergency: '', employee: '雇员2', employeeId: 'GY002', employeeCardNumber: '', companySocialSecurityAccount: '', uKey: '', doDate: '', customerId: '', customerName: '客户3', finishDate: '', sponsor: '', initiator: '', sponsorTime: '', notes: ''},
          {action: '', tid: 'SS_BJ_0001', type: '补缴', emergency: '是', employee: '雇员3', employeeId: 'GY003', employeeCardNumber: '', companySocialSecurityAccount: '', uKey: '', doDate: '', customerId: '', customerName: '客户3', finishDate: '', sponsor: '', initiator: '', sponsorTime: '', notes: ''},
          {action: '', tid: 'SS_ZZ_0001', type: '转出', emergency: '', employee: '雇员4', employeeId: 'GY004', employeeCardNumber: '', companySocialSecurityAccount: '', uKey: '', doDate: '', customerId: '', customerName: '客户3', finishDate: '', sponsor: '', initiator: '', sponsorTime: '', notes: ''},
          {action: '', tid: 'SS_TZ_0001', type: '转出', emergency: '', employee: '雇员5', employeeId: 'GY005', employeeCardNumber: '', companySocialSecurityAccount: '', uKey: '', doDate: '', customerId: '', customerName: '客户3', finishDate: '', sponsor: '', initiator: '', sponsorTime: '', notes: ''},
          {action: '', tid: 'SS_TZ_0001', type: '转出', emergency: '是', employee: '雇员6', employeeId: 'GY006', employeeCardNumber: '', companySocialSecurityAccount: '', uKey: '', doDate: '', customerId: '', customerName: '客户3', finishDate: '', sponsor: '', initiator: '', sponsorTime: '', notes: ''},
          {action: '', tid: 'SS_TQ_0001', type: '转出', emergency: '', employee: '雇员8', employeeId: 'GY008', employeeCardNumber: '', companySocialSecurityAccount: '', uKey: '', doDate: '', customerId: '', customerName: '客户3', finishDate: '', sponsor: '', initiator: '', sponsorTime: '', notes: ''}
        ]
      }
    },
    computed: {

    },
    methods: {
      routerToCommcialOperator: function(name) {
        this.$router.push({
          name: 'employeecommcialoperator',
          query: {operatorType: name}
        });
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
  .ml10 {margin-left: 10px;}
</style>
