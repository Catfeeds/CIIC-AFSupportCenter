<template>
    <div class="smList">
        <Collapse v-model="value1" accordion>
            <Panel name="1">
                <div slot="content">
                     <Row>
                        <Col :xs="{ span: 8, offset: 1 }" :lg="{ span: 8, offset: 1 }">
                        <Form :model="formItem" :label-width=100>
                            <Form-item label="任务单编号：">
                                <Select  v-model="formItem.client"
                                    filterable
                                    remote
                                    :remote-method="searchTaskNo"
                                    :loading="loading1">
                                    <Option v-for="option in optionsClient" :value="option.value" :key="option.value">{{option.label}}</Option>
                                </Select>
                            </Form-item>
                            <Form-item label="任务单标题：">
                                <Select
                                    v-model="formItem.client"
                                    filterable
                                    remote
                                    :remote-method="searchTaskTitle"
                                    :loading="loading1">
                                    <Option v-for="option in optionsClient" :value="option.value" :key="option.value">{{option.label}}</Option>
                                </Select>
                            </Form-item>
                            <Form-item label="薪资周期：">
                                <Select
                                    v-model="formItem.name"
                                    filterable
                                    remote
                                    :remote-method="searchSalaryPeriod"
                                    :loading="loading1">
                                    <Option v-for="option in optionsName" :value="option.value" :key="option.value">{{option.label}}</Option>
                                </Select>
                            </Form-item>
                        </Form>
                        </Col>
                        <Col :xs="{ span: 8, offset: 1 }" :lg="{ span: 8, offset: 1}" class="checkBtn">
                        <Form :model="formItem" :label-width=100>
                            <Form-item label="管理方编号：">
                                <Select
                                    v-model="formItem.name"
                                    filterable
                                    remote
                                    :remote-method="searchManagementCode"
                                    :loading="loading1">
                                    <Option v-for="option in optionsName" :value="option.value" :key="option.value">{{option.label}}</Option>
                                </Select>
                            </Form-item>
                            <Form-item label="管理方名称：">
                                <Select
                                    v-model="formItem.name"
                                    filterable
                                    remote
                                    :remote-method="searchManagementName"
                                    :loading="loading1">
                                    <Option v-for="option in optionsName" :value="option.value" :key="option.value">{{option.label}}</Option>
                                </Select>
                            </Form-item>
                            <Form-item label="计算批次：">
                                <Select
                                    v-model="formItem.name"
                                    filterable
                                    remote
                                    :remote-method="searchCalBeatch"
                                    :loading="loading1">
                                    <Option v-for="option in optionsName" :value="option.value" :key="option.value">{{option.label}}</Option>
                                </Select>
                            </Form-item>
                        </Form>
                         <Button type="primary" size="large">查询</Button>
                        </Col>
                    </Row>
                </div>
            </Panel>
        </Collapse>
        <Table border :columns="importColumns" :data="payrolltasksubmitlist"></Table>
        <Page :total="100" show-sizer show-elevator></Page>
    </div>
</template>
<script>

    import {mapActions,mapGetters} from 'vuex'
    import * as eventType from '../../store/EventTypes/PayrollTaskSubmitType'
    import expandRow from '../../components/payrolltask/payrollchildtask.vue'

    export default {
        name:"payrollTaskSubmit",
        data() {
            return {
                optionsClient:[],
                optionsName:[],
                loading1:false,
                formItem: {
                    client:""
                },
                value1: '1',
                importColumns: [
                   {
                        type: 'expand',
                        width: 50,
                        render: (h, params) => {
                            return h(expandRow, {
                                props: {
                                    row: params.row.children
                                }
                            })
                        }
                   },
                   {
                        title: '序号',
                        key: 'id',
                        width: 70
                    },
                    {
                        title: '任务单编号',
                        key: 'taskNo',
                        width: 100,
                        render: (h, params) => {
                            return h('div',{
                                style:{
                                    cursor:'pointer',
                                    textDecoration:'underline'
                                }
                            },params.row.taskNo);
                        }
                    },
                    {
                        title: '任务单标题',
                        key: 'taskTitle',
                        width: 170
                    },
                    {
                        title: '管理方名称',
                        key: 'management',
                        width: 100
                    },
                    {
                        title: '薪资周期',
                        key: 'salaryperiod',
                        width: 90
                    },
                    {
                        title: '计算批次',
                        key: 'calculatebatch',
                        width: 120
                    },
                    {
                        title: '工资单模板',
                        key: 'payrolltemplate',
                        width: 120
                    },
                    {
                        title: '总人数',
                        key: 'total',
                        width: 80
                    },
                    {
                        title: '中方人数',
                        key: 'china',
                        width: 90
                    },
                    {
                        title: '外方人数',
                        key: 'foreign',
                        width: 90
                    },
                    {
                        title: '工资单形式',
                        key: 'payrolltype',
                        width: 100
                    },
                    {
                        title: '状态',
                        key: 'state',
                        width: 80
                    },
                    {
                        title: '实际发布日期',
                        key: 'publishdate',
                        width: 126
                    },
                    {
                        title: '操作',
                        key: 'action',
                        align: 'center',
                        width: 150,
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.toDetail(params.index)
                                        }
                                    }
                                }, '提交'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.invalid(params.index)
                                        }
                                    }
                                }, '失效')
                            ]);
                        }
                      }
                ]
            }
        },
        mounted() {
          this.setList();
        },
        computed: {
          ...mapGetters([
            'payrolltasksubmitlist'
          ]),
        },
        methods: {
           ...mapActions({
              setList:eventType.PAYROLLTASKSUBMIT_SETLIST
            }),
            invalid(index) {
               this.$Modal.confirm({
                  content:'确认要失效吗？',
                  onOk(){
                    this.$Message.info('失效成功!');
                  }
                })
            },
            toDetail(data){
              this.$router.push({
                  name: 'ptasksubmitdetial',
                  params: {
                    data: data
                  }
            });
          },
          searchTaskNo(){},
          searchTaskTitle(){},
          searchSalaryPeriod(){},
          searchManagementCode(){},
          searchManagementName(){},
          searchCalBeatch(){}
        }
    }

</script>
<style scoped>
    .ivu-card {
        background: rgba(246, 246, 246, 1);
    }

    .checkBtn .ivu-btn {
        float: right;
    }
    .smList .ivu-collapse{
      margin: 15px 0;
    }
</style>
