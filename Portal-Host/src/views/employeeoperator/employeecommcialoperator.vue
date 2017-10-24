<template>
  <Form :label-width=120>
    <Row class="mt20">
      <Col :xs="{span: 1}" :lg="{span: 1}">
        <Button type="dashed" @click="">添加</Button>
      </Col>
      <Col :xs="{span: 2}" :lg="{span: 2}">
        <Button type="error" @click="">删除</Button>
      </Col>
    </Row>

    <Table class="mt20" border :columns="operatorTableNewColumns" :data="employeecommcialoperator.operatorTableNewData" ref="employeeSocialSecurityData" v-if="operatorType === '1'"></Table>
    <Table class="mt20" border :columns="operatorTableRepairColumns" :data="employeecommcialoperator.operatorTableRepairData" ref="employeeSocialSecurityData" v-else-if="operatorType === '2'"></Table>
    <Table class="mt20" border :columns="operatorTableChangeColumns" :data="employeecommcialoperator.operatorTableChangeData" ref="employeeSocialSecurityData" v-else-if="operatorType === '3'"></Table>
    <Table class="mt20" width="1271" border :columns="operatorTableOutColumns" :data="employeecommcialoperator.operatorTableOutData" ref="employeeSocialSecurityData" v-else></Table>

    <Row class="mt20">
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="办理方式：">
          <Select v-model="handleValue" style="width: 100%;">
            <Option v-for="item in handleList" :value="item.value" :key="item.value">{{item.label}}</Option>
          </Select>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-if="operatorType === '4'">
        <Form-item label="截止月份：">
          <DatePicker v-model="socialSecurityEndMonth" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-if="operatorType === '1'">
        <Form-item label="社保账号分类：">
          <Select v-model="socialSecurityAccountTypeValue" style="width: 100%;">
            <Option v-for="item in socialSecurityAccountTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
          </Select>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-else-if="operatorType === '2'">
        <Form-item label="补缴基数：">
          <Input v-model="socialSecurityRepairBase" placeholder="请输入..."></Input>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-else-if="operatorType === '3'">
        <Form-item label="新社保缴费基数:">
          <Input v-model="socialSecurityChangeBase" placeholder="请输入..."></Input>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-if="operatorType === '1'">
        <Form-item label="任务：">
          <Select v-model="taskValue" style="width: 100%;">
            <Option v-for="item in taskList" :value="item.value" :key="item.value">{{item.label}}</Option>
          </Select>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-else-if="operatorType === '2'">
        <Form-item label="补缴起始月份：">
          <DatePicker v-model="socialSecurityRepairStartMonth" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-else-if="operatorType === '3'">
        <Form-item label="调整起始月份：">
          <DatePicker v-model="socialSecurityChangeStartMonth" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-if="operatorType === '1'">
        <Form-item label="社保序号：">
          <Input v-model="socialSecurityIndex" placeholder="请输入..."></Input>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-else-if="operatorType === '2'">
        <Form-item label="补缴截止月份：">
          <DatePicker v-model="socialSecurityRepairEndMonth" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-else-if="operatorType === '3'">
        <Form-item label="调整截止月份：">
          <DatePicker v-model="socialSecurityChangeEndMonth" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-if="operatorType === '1'">
        <Form-item label="起缴月份：">
          <DatePicker v-model="socialSecurityStartMonth" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-else-if="operatorType === '2'">
        <Form-item label="补缴办理月份：">
          <DatePicker v-model="socialSecurityRepairDoMonth" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-else-if="operatorType === '3'">
        <Form-item label="调整办理月份：">
          <DatePicker v-model="socialSecurityChangeDoMonth" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}" v-if="operatorType === '1'">
        <Form-item label="截止月份：">
          <DatePicker v-model="socialSecurityEndMonth" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 17, offset: 1}" :lg="{span: 17, offset: 1}">
        <Form-item label="批退原因：">
          <Input v-model="refuseReason" type="textarea" :rows=4 placeholder="请输入..."></Input>
        </Form-item>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 4, offset: 16}" :lg="{span: 4, offset: 16}">
        <Button type="primary" @click="">批量提交</Button>
        <Button type="ghost" @click="goback">返回</Button>
      </Col>
    </Row>
  </Form>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import * as eventType from '../../store/EventTypes/employeeoperator/EmployeeCommcialOperatorType'

  export default {
    name:"employeecommcialoperator",
    data() {
      return {
        operatorType: this.$route.query.operatorType,
        operatorTableNewColumns: [
          {type: 'selection', align: 'center', width: 60, fixed: 'left'},
          {title: '操作', key: 'action', align: 'center', width: 80, fixed: 'left',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('a', {props: {},
                  on: {
                    click: () => {

                    }
                  }
                }, '移出')
              ])
            }
          },
          {title: '任务单编号', key: 'tid', align: 'center', width: 150, fixed: 'left',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.tid),
              ]);
            }
          },
          {title: '客户编号', width: 120, key: 'customerNumber', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.customerNumber),
              ]);
            }
          },
          {title: '客户名称', width: 120, key: 'customerName', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.customerName),
              ]);
            }
          },
          {title: '雇员姓名', width: 120, key: 'employeeName', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.employeeName),
              ]);
            }
          },
          {title: '入职日期', width: 120, key: 'checkDate', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.checkDate),
              ]);
            }
          },
          {title: '人员分类', width: 120, key: 'personType', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.personType),
              ]);
            }
          },
          {title: '处理方式', key: 'handleMethod', align: 'center', width: 180,
            render: (h, params) => {
              return h('div', [
                  h('Select', {props: {value: ''}},
                    [
                      h('Option', {props: {value: '1'}},'网上申报'),
                      h('Option', {props: {value: '2'}},'柜面办理'),
                    ]
                  )
                ]
              );
            }
          },
          {title: '企业社保账户分类', key: 'socialSecurityAccountType', align: 'center', width: 180,
            render: (h, params) => {
              return h('div', [
                  h('Select', {props: {value: ''}},
                    [
                      h('Option', {props: {value: '1'}}, '中智大库1'),
                      h('Option', {props: {value: '2'}}, '中智大库2'),
                      h('Option', {props: {value: '3'}}, '独立户客户1'),
                      h('Option', {props: {value: '4'}}, '独立户客户2'),
                    ]
                  )
                ]
              );
            }
          },
          {title: '任务', key: 'task', align: 'center', width: 180,
            render: (h, params) => {
              return h('div', [
                  h('Select', {props: {value: ''}},
                    [
                      h('Option', {props: {value: '1'}}, '新进'),
                      h('Option', {props: {value: '2'}}, '转入'),
                      h('Option', {props: {value: '3'}}, '新进转出'),
                      h('Option', {props: {value: '4'}}, '转入转出'),
                    ]
                  )
                ]
              );
            }
          },
          {title: '社保序号', key: 'socialSecurityIndex', align: 'center', width: 180, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('i-input', {props: {value: params.row.socialSecurityIndex}})
              ]);
            }
          },
          {title: '基数', key: 'base', align: 'center', width: 180, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('i-input', {props: {value: params.row.base}})
              ]);
            }
          },
          {title: '社保起始月份', key: 'socialSecurityStartMonth', align: 'center', width: 180, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('DatePicker', {props: {value: params.row.socialSecurityStartMonth}})
              ]);
            }
          },
          {title: '社保截止月份', key: 'socialSecurityEndMonth', align: 'center', width: 180, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('DatePicker', {props: {value: params.row.socialSecurityEndMonth}})
              ]);
            }
          },
          {title: '备注', key: 'notes', align: 'center', width: 260, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('i-input', {props: {value: params.row.notes}})
              ]);
            }
          }
        ],

        operatorTableRepairColumns: [
          {type: 'selection', align: 'center', width: 60, fixed: 'left'},
          {title: '操作', key: 'action', align: 'center', width: 80, fixed: 'left',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('a', {props: {},
                  on: {
                    click: () => {

                    }
                  }
                }, '移出')
              ])
            }
          },
          {title: '任务单编号', key: 'tid', width: 150, align: 'center', fixed: 'left',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.tid),
              ]);
            }
          },
          {title: '客户编号', width: 120, key: 'customerNumber', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.customerNumber),
              ]);
            }
          },
          {title: '客户名称', width: 120, key: 'customerName', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.customerName),
              ]);
            }
          },
          {title: '雇员姓名', width: 120, key: 'employeeName', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.employeeName),
              ]);
            }
          },
          {title: '入职日期', width: 120, key: 'checkDate', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.checkDate),
              ]);
            }
          },
          {title: '人员分类', width: 120, key: 'personType', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.personType),
              ]);
            }
          },
          {title: '处理方式', key: 'handleMethod', align: 'center', width: 180,
            render: (h, params) => {
              return h('div', [
                  h('Select', {props: {value: ''}},
                    [
                      h('Option', {props: {value: '1'}},'网上申报'),
                      h('Option', {props: {value: '2'}},'柜面办理'),
                    ]
                  )
                ]
              );
            }
          },
          {title: '补缴办理月份', key: 'repairDoMonth', align: 'center', width: 180,
            render: (h, params) => {
              return h('div', [
                h('DatePicker', {props: {value: params.row.repairDoMonth}})
              ]);
            }
          },
          {title: '补缴基数', key: 'repairBase', align: 'center', width: 180,
            ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('i-input', {props: {value: params.row.repairBase}})
              ]);
            }
          },
          {title: '补缴起始月份', key: 'repairStartMonth', align: 'center', width: 180, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('DatePicker', {props: {value: params.row.repairStartMonth}})
              ]);
            }
          },
          {title: '补缴截止月份', key: 'repairEndMonth', align: 'center', width: 180, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('DatePicker', {props: {value: params.row.repairEndMonth}})
              ]);
            }
          },
          {title: '备注', key: 'notes', align: 'center', width: 260, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('i-input', {props: {value: params.row.notes}})
              ]);
            }
          }
        ],

        operatorTableChangeColumns: [
          {type: 'selection', align: 'center', width: 60, fixed: 'left'},
          {title: '操作', key: 'action', align: 'center', width: 80, fixed: 'left',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('a', {props: {},
                  on: {
                    click: () => {

                    }
                  }
                }, '移出')
              ])
            }
          },
          {title: '任务单编号', key: 'tid', width: 150, align: 'center', fixed: 'left',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.tid),
              ]);
            }
          },
          {title: '客户编号', width: 120, key: 'customerNumber', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.customerNumber),
              ]);
            }
          },
          {title: '客户名称', width: 120, key: 'customerName', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.customerName),
              ]);
            }
          },
          {title: '雇员姓名', width: 120, key: 'employeeName', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.employeeName),
              ]);
            }
          },
          {title: '入职日期', width: 120, key: 'checkDate', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.checkDate),
              ]);
            }
          },
          {title: '人员分类', width: 120, key: 'personType', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.personType),
              ]);
            }
          },
          {title: '处理方式', key: 'handleMethod', align: 'center', width: 180,
            render: (h, params) => {
              return h('div', [
                  h('Select', {props: {value: ''}},
                    [
                      h('Option', {props: {value: '1'}},'网上申报'),
                      h('Option', {props: {value: '2'}},'柜面办理'),
                    ]
                  )
                ]
              );
            }
          },
          {title: '调整办理月份', key: 'changeMonth', align: 'center', width: 180,
            render: (h, params) => {
              return h('div', [
                h('DatePicker', {props: {value: params.row.changeMonth}})
              ]);
            }
          },
          {title: '新基数', key: 'changeBase', align: 'center', width: 180, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('i-input', {props: {value: params.row.changeMonth}})
              ]);
            }
          },
          {title: '调整起始月份', key: 'changeStartMonth', align: 'center', width: 180, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('DatePicker', {props: {value: params.row.changeStartMonth}})
              ]);
            }
          },
          {title: '调整截止月份', key: 'changeEndMonth', align: 'center', width: 180, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('DatePicker', {props: {value: params.row.changeEndMonth}})
              ]);
            }
          },
          {title: '备注', key: 'notes', align: 'center', width: 260, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('i-input', {props: {value: params.row.notes}})
              ]);
            }
          }
        ],

        operatorTableOutColumns: [
          {type: 'selection', align: 'center', width: 60},
          {title: '操作', key: 'action', align: 'center', width: 80,
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('a', {props: {},
                  on: {
                    click: () => {

                    }
                  }
                }, '移出')
              ])
            }
          },
          {title: '任务单编号', key: 'tid', width: 150, align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'left'}}, [
                h('span', params.row.tid),
              ]);
            }
          },
          {title: '公司名称', width: 120, key: 'companyName', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.companyName),
              ]);
            }
          },
          {title: '雇员姓名', width: 120, key: 'employeeName', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.employeeName),
              ]);
            }
          },
          {title: '离职日期', width: 120, key: 'checkOutDate', align: 'center',
            render: (h, params) => {
              return h('div', {style: {textAlign: 'right'}}, [
                h('span', params.row.checkOutDate),
              ]);
            }
          },
          {title: '处理方式', key: 'handleMethod', align: 'center', width: 180,
            render: (h, params) => {
              return h('div', [
                  h('Select', {props: {value: ''}},
                    [
                      h('Option', {props: {value: '1'}},'网上申报'),
                      h('Option', {props: {value: '2'}},'柜面办理'),
                    ]
                  )
                ]
              );
            }
          },
          {title: '截止月份', key: 'endMonth', align: 'center', width: 180, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('DatePicker', {props: {value: params.row.changeEndMonth}})
              ]);
            }
          },
          {title: '备注', key: 'notes', align: 'center', width: 260, ellipsis: true,
            render: (h, params) => {
              return h('div', [
                h('i-input', {props: {value: params.row.notes}})
              ]);
            }
          }
        ],


        handleValue: '',
        handleList: [
          {value: '1', label: '网上申报'},
          {value: '2', label: '柜面办理'}
        ], //办理方式

        socialSecurityAccountTypeValue: '',
        socialSecurityAccountTypeList: [
          {value: '1', label: '中智大库1'},
          {value: '2', label: '中智大库2'},
          {value: '3', label: '独立户客户1'},
          {value: '4', label: '独立户客户2'},
        ], //办理方式

        taskValue: '',
        taskList: [
          {value: '1', label: '新进'},
          {value: '2', label: '转入'},
          {value: '3', label: '新进转出'},
          {value: '4', label: '转入转出'},
        ], //办理方式
        socialSecurityIndex: '', //社保序号
        socialSecurityStartMonth: '', //社保起缴月份
        socialSecurityEndMonth: '', //社保截止月份

        socialSecurityRepairBase: '', //补缴基数
        socialSecurityRepairStartMonth: '', //补缴起始月份
        socialSecurityRepairEndMonth: '', //补缴截止月份
        socialSecurityRepairDoMonth: '', //补缴办理月份

        socialSecurityChangeBase: '', //调整后的新基数
        socialSecurityChangeStartMonth: '', //调整起始月份
        socialSecurityChangeEndMonth: '', //调整截止月份
        socialSecurityChangeDoMonth: '', //调整办理月份

        refuseReason: '' //批退原因
      }
    },
    mounted() {
      this.setEmployeeCommcialOperator()
    },
    computed: {
      ...mapGetters([
        'employeecommcialoperator'
      ])
    },
    methods: {
      ...mapActions({
        setEmployeeCommcialOperator: eventType.EMPLOYEECOMMCIALOPERATOR
      }),
      goback () {
        this.$router.push({name:'employeeoperatorview'});
      }
    }
  }
</script>
<style scoped>
  .mt20 {margin-top: 20px;}
</style>
