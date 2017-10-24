<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        企业任务单
        <div slot="content">
          <Form :label-width=120>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="服务中心：">
                  <Select v-model="companyTaskInfo.serviceCenterValue" style="width: 100%;" disabled>
                    <Option v-for="item in companyTaskInfo.serviceCenterList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="客户编号：">
                  <Input v-model="companyTaskInfo.customerNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="客户名称：">
                  <Button type="ghost" @click="companyTaskInfo.customerName = true" long>&nbsp;</Button>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="账户类型：">
                  <Select v-model="companyTaskInfo.accountTypeValue" style="width: 100%;">
                    <Option v-for="item in companyTaskInfo.accountTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="结算区县：">
                  <Select v-model="companyTaskInfo.regionValue" style="width: 100%;">
                    <Option v-for="item in companyTaskInfo.regionList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="任务单编号：">
                  <Input v-model="companyTaskInfo.taskNumber" placeholder="请输入..."></Input>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="任务单类型：">
                  <Select v-model="companyTaskInfo.taskTypeValue" style="width: 100%;">
                    <Option v-for="item in companyTaskInfo.taskTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="任务发起时间：">
                  <DatePicker v-model="companyTaskInfo.taskStartTime" type="daterange" placement="bottom" placeholder="选择日期" style="width: 100%"></DatePicker>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
                <Form-item label="处理状态：">
                  <Select v-model="companyTaskInfo.handleStateValue" style="width: 100%;">
                    <Option v-for="item in companyTaskInfo.handleStateList" :value="item.value" :key="item.value">{{item.label}}</Option>
                  </Select>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Row>
                <Col :xs="{span: 4, offset: 16}" :lg="{span: 4, offset: 16}">
                  <Button type="primary" @click="" icon="ios-search">查询</Button>
                  <Button type="default" @click="">重置</Button>
                </Col>
              </Row>
            </Row>
          </Form>
        </div>
      </Panel>
    </Collapse>

    <Form>
      <Row v-show="isCperator === 1 || isCperator === 2" class="mt20">
        <Col :xs="{span: 2}" :lg="{span: 2}">
          <Button type="error" @click="isRefuseReason = true">批退</Button>
          <Button type="info" @click="">导出</Button>
        </Col>
      </Row>

      <Row class="mt20">
        <Col :xs="{span: 24}" :lg="{span: 24}">
          <Table border :columns="taskColumns" :data="taskData"></Table>
          <Page :total="4" :page-size="5" :page-size-opts="[5, 10]" show-sizer show-total  class="pageSize"></Page>
        </Col>
      </Row>

      <!-- 批退理由 -->
      <Modal
        v-model="isRefuseReason"
        @on-ok="ok"
        @on-cancel="cancel">
        <Form>
          <p>
            <Form-item>
              <Input v-model="refuseReason" type="textarea" :rows=4 placeholder="请填写批退备注..."></Input>
            </Form-item>
          </p>
        </Form>
      </Modal>
    </Form>
  </div>
</template>
<script>
  import operatorSearch from "../commoncontrol/operatorsearch.vue"

  export default {
    name: 'companytaskcontrol',
    props:{
      isCperator: {
        index: Number
      },
      taskData: {
        type: Array
      }
    },
    components: {operatorSearch},
    data() {
      return{
        collapseInfo: [1], //展开栏
        companyTaskInfo: {
          serviceCenterValue: '',
          serviceCenterList: [],
          customerNumber: '',
          customerName: '',
          accountTypeValue: '',
          accountTypeList: [
            {value: '1', label: '独立库'},
            {value: '2', label: '大库'},
            {value: '3', label: '外包'}
          ],
          regionValue: '',
          regionList: [
            {value: '1', label: '徐汇'},
            {value: '2', label: '长宁'},
            {value: '3', label: '浦东'},
            {value: '4', label: '卢湾'},
            {value: '5', label: '静安'},
            {value: '6', label: '黄浦'}
          ],
          taskNumber: '',
          taskTypeValue: '',
          taskTypeList: [
            {value: '1', label: '新开转入'},
            {value: '2', label: '调整'},
            {value: '3', label: '补缴'},
            {value: '', label: '...'}
          ],
          taskStartTime: '',
          handleStateValue: '',
          handleStateList: [
            {value: '1', label: '已受理'},
            {value: '2', label: '已送审'},
            {value: '3', label: '已完成'}
          ],
        },

        isRefuseReason: false,
        refuseReason: '',

        taskColumns: [
          {title: '操作', key: 'action', fixed: 'left', width: 80, align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {type: 'primary', size: 'small'},
                  style: {margin: '0 auto'},
                  on: {
                    click: () => {
                      switch(params.row.type) {
                        case '开户':
                          this.$router.push({name: 'companytaskprogress2', query: {operatorType: '1'}})
                          break;
                        case '转移':
                          this.$router.push({name: 'companytaskprogress2', query: {operatorType: '2'}})
                          break;
                        case '变更':
                          this.$router.push({name: 'companytaskprogress2', query: {operatorType: '3'}})
                          break;
                        case '终止':
                          this.$router.push({name: 'companytaskprogress2', query: {operatorType: '4'}})
                          break;
                        default:
                          break;
                      }
                    }
                  }
                }, this.isCperator === 1 || this.isCperator === 2 ? '办理' : this.isCperator === 4 ? '查看' : params.row.emergency === '是' ? '查看' : '修改'),
              ]);
            }
          },
          {title: '任务单编号', key: 'tid', width: 150, fixed: 'left', align: 'center',
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
          {title: '客户编号', key: 'customerId', width: 150, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.customerId),
              ]);
            }
          },
          {title: '企业客户', key: 'companyCustomer', width: 100, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.companyCustomer),
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
          {title: '发起人', key: 'initiator', width: 120, align: 'center',
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
          {title: '备注', key: 'notes', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.notes),
              ]);
            }
          }
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
  .mt20 {margin-top: 20px;}
  .ml10 {margin-left: 10px;}
</style>
