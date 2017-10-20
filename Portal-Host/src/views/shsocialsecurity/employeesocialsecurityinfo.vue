<template>
  <div class="smList">
    <Collapse v-model="collapseInfo">
      <Panel name="1">
        客户基本信息
        <div slot="content">
          <Form :label-width=100>
            <Row class="mt20">
              <Col :xs="{span: 3, offset: 1}" :lg="{span: 3, offset: 1}">
                <Form-item label="客户编号：" class="">
                  <label>{{customer.code}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="客户名称：" class="">
                  <label>{{customer.name}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="服务中心：" class="">
                  <label>{{customer.customerServicerCenter}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Form-item label="客服经理：" class="">
                  <label>{{customer.customerServicer}}</label>
                </Form-item>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
      <Panel name="2">
        雇员信息
        <div slot="content">
          <Form :label-width=100>
            <Row class="mt20">
              <Col :xs="{span: 3, offset: 1}" :lg="{span: 3, offset: 1}">
                <Form-item label="雇员编号：" class="">
                  <label>{{employee.code}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="雇员姓名：" class="">
                  <label>{{employee.name}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="证件号码：" class="">
                  <label>{{employee.idNo}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Form-item label="入职日期：" class="">
                  <label>{{employee.checkInTime}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 3, offset: 1}" :lg="{span: 3, offset: 1}">
                <Form-item label="社保序号：" class="">
                  <label>{{employee.socialSecurityNo}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="学历：" class="">
                  <label>{{employee.education}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="企业社保账户：" class="">
                  <label>{{employee.socialSecurityCompanyAcount}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Form-item label="人员分类：" class="">
                  <label>{{employee.personType}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="离职日期：" class="">
                  <label>{{employee.checkOutTime}}</label>
                </Form-item>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
      <Panel name="3">
        社保汇缴信息
        <div slot="content">
          <Form :label-width=100>
            <Row class="mt20">
              <Col :xs="{span: 3, offset: 1}" :lg="{span: 3, offset: 1}">
                <Form-item label="社保状态：" class="">
                  <label>{{ssinfo.state}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="社保缴费基数：" class="">
                  <label>{{ssinfo.payBasePrice}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
                <Form-item label="社保起缴月份：" class="">
                  <label>{{ssinfo.payStartMonth}}</label>
                </Form-item>
              </Col>
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Form-item label="办理月份：" class="">
                  <label>{{ssinfo.doMonth}}</label>
                </Form-item>
              </Col>
            </Row>
            <Row>
              <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
                <Table border :columns="socialSecurityInfoListColumns" :data="socialSecurityInfoListData"></Table>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
      <Panel name="4">
        变动历史
        <div slot="content">
          <Form :label-width=100>
            <Row class="mt20">
              <Col :xs="{span: 14, offset: 1}" :lg="{span: 14, offset: 1}">
                <Table width="100%" border :columns="changeListColumns" :data="changeListData"></Table>
              </Col>
            </Row>
          </Form>
        </div>
      </Panel>
    </Collapse>
    <Row class="mt20">
      <Col :xs="{span: 2, offset: 10}" :lg="{span: 2, offset: 10}">
        <Button type="default" @click="goBack">返回</Button>
      </Col>
    </Row>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'

  export default {
    name:"employeesocialsecurityinfo",
    data() {
      return {
        collapseInfo: [1, 2, 3, 4], //展开栏
        customer: {
          code: 'KH0001',
          name: '上海XX信息技术有限公司',
          customerServicerCenter: '大客户2',
          customerServicer: '张XX'
        }, //客户基本信息

        employee: {
          code: 'GY0001',
          name: '张三',
          idNo: '35220219781124114',
          checkInTime: '2017-1-1',
          socialSecurityNo: '123235564',
          education: '本科',
          socialSecurityCompanyAcount: '中智大库',
          personType: '外地',
          checkOutTime: '2017-1-1'
        }, //雇员信息

        ssinfo: {
          state: '已做',
          payBasePrice: '19000',
          payStartMonth: '201701',
          doMonth: '201706'
        },
        socialSecurityInfoListColumns: [
          {title: '变更类型', key: 'changeType', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.changeType),
              ]);
            }
          },
          {title: '基数', key: 'base', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.base),
              ]);
            }
          },
          {title: '起缴年月', key: 'startTime', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.startTime),
              ]);
            }
          },
          {title: '截至年月', key: 'endTime', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.endTime),
              ]);
            }
          }
        ],
        socialSecurityInfoListData: [
          {changeType: '', base: '19000', startTime: '201605', endTime: ''},
          {changeType: '', base: '18000', startTime: '201505', endTime: '201604'},
          {changeType: '', base: '17000', startTime: '201405', endTime: '201504'},
          {changeType: '补', base: '16000', startTime: '201305', endTime: '201404'}
        ], //社保汇缴信息

        changeListColumns: [
          {title: '任务单编号', key: 'tid', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('a', {
                  on: {
                    click: () => {
                      this.$router.push({
                        name: 'companysocialsecuritynew',
                        query: {operatorType: params.index.toString(), sourceFrom: 'search'}
                      });
                    }
                  }
                }, params.row.tid)
              ]);
            }
          },
          {title: '办理方式', key: 'doMetod', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.doMetod),
              ]);
            }
          },
          {title: '变更内容', key: 'changeContent', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.changeContent),
              ]);
            }
          },
          {title: '任务发起人', key: 'creator', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.creator),
              ]);
            }
          },
          {title: '任务发起日期', key: 'startTime', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.startTime),
              ]);
            }
          },
          {title: '办理结果', key: 'result', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.result),
              ]);
            }
          }
        ],
        changeListData: [
          {tid: 'XK00001', doMetod: '网上申请', changeContent: '新开', creator: 'XXX', startTime: '2015-06-01', result: '已办理'},
          {tid: 'BG00002', doMetod: '柜面办理', changeContent: '调整', creator: 'XXX', startTime: '2015-06-01', result: '不需办理'},
          {tid: 'ZC00003', doMetod: '网上申请', changeContent: '转出', creator: 'XXX', startTime: '2015-07-01', result: '批退'}
        ] //变动历史
      }
    },
    mounted() {

    },
    computed: {

    },
    methods: {
      goBack() {
        this.$router.push({name:'employeesocialsecuritysearch'});
      }
    }
  }
</script>

<style scoped>
  .mt20 {
    margin-top: 20px;
  }
</style>
