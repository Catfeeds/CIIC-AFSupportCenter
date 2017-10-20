<template>
  <Form :label-width=100>
    <Row class="mt20">
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="办理方式：" class="">
          <Select v-model="socialSecurityPayOperator.doValue" style="width: 100%;">
            <Option v-for="item in socialSecurityPayOperator.doMethod" :value="item.value" :key="item.value">{{item.label}}</Option>
          </Select>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="办理月份：" class="">
          <DatePicker v-model="socialSecurityPayOperator.doMonth" type="daterange" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}">
        <Form-item label="变更类型：" class="">
          <span v-if="operatorType === '1'">调整</span>
          <span v-else-if="operatorType === '4'">补缴</span>
          <Select v-model="socialSecurityPayOperator.changeValue" style="width: 100%;" v-else>
            <Option v-for="item in socialSecurityPayOperator.changeType" :value="item.value" :key="item.value">{{item.label}}</Option>
          </Select>
        </Form-item>
      </Col>
    </Row>
    <Row class="mt20">
      <!-- 仅新增 -->
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}" v-show="operatorType === '0'">
        <Form-item label="社保序号：" class="">
          <Input v-model="socialSecurityPayOperator.socialSecurityNumber" placeholder="请输入..."></Input>
        </Form-item>
      </Col>
      <!-- 仅转出 -->
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}" v-show="operatorType === '2'">
        <Form-item label="特殊变更类型：" class="">
          <Select v-model="socialSecurityPayOperator.specialChangeValue" style="width: 100%;">
            <Option v-for="item in socialSecurityPayOperator.specialChangeType" :value="item.value" :key="item.value">{{item.label}}</Option>
          </Select>
        </Form-item>
      </Col>
      <!-- 仅新增 -->
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}" v-show="operatorType === '0'">
        <Form-item label="起缴月份：" class="">
          <DatePicker v-model="socialSecurityPayOperator.startMonth" type="daterange" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <!-- 仅转出 -->
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}" v-show="operatorType === '2'">
        <Form-item label="缴费截止月份：" class="">
          <DatePicker v-model="socialSecurityPayOperator.payEndMonth" type="daterange" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
      <!-- 仅新增 -->
      <Col :xs="{span: 5, offset: 1}" :lg="{span: 5, offset: 1}" v-show="operatorType === '0'">
        <Form-item label="截至月份：" class="">
          <DatePicker v-model="socialSecurityPayOperator.endMonth" type="daterange" placement="bottom-end" placeholder="选择日期" style="width: 100%;"></DatePicker>
        </Form-item>
      </Col>
    </Row>
    <Row class="mt20" v-if="operatorType !== '2'">
      <Col :xs="{span: 20, offset: 1}" :lg="{span: 20, offset: 1}">
        <Table border width="100%" :columns="socialSecurityPayOperator.operatorListColumns" :data="socialSecurityPayOperator.operatorListData"></Table>
      </Col>
    </Row>
    <Row class="mt20">
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="办理备注：" class="">
          <Input v-model="socialSecurityPayOperator.doNotes" placeholder="请输入..."></Input>
        </Form-item>
      </Col>
    </Row>
    <Row class="mt20">
      <Col :xs="{span: 8, offset: 1}" :lg="{span: 8, offset: 1}">
        <Form-item label="批退备注：" class="">
          <Input v-model="socialSecurityPayOperator.refuseNotes" placeholder="请输入..."></Input>
        </Form-item>
      </Col>
    </Row>
  </Form>
</template>
<script>
  export default {
    name:"socialsecurityoperator",
    props:{
      operatorType:{
        type: String
      }
    },
    data() {
      return {
        socialSecurityPayOperator: {
          doValue: '',
          doMethod: [
            {value: 1, label: '网上申报'},
            {value: 2, label: '柜面办理'}
          ], //办理方式

          socialSecurityNumber: '', //社保序号

          changeValue: '',
          changeType: [
            {value: 1, label: '新进'},
            {value: 2, label: '转入'},
            {value: 3, label: '新进转出'},
            {value: 4, label: '转入转出'},
          ], //变更方式

          doMonth: '', //办理月份

          startMonth: '', //起缴月份

          endMonth: '', //截至月份

          specialChangeValue: '',
          specialChangeType: [
            {value: 1, label: '退休'},
            {value: 2, label: '终止'}
          ], //特殊变更类型：

          payEndMonth: '', //缴费截止月份

          operatorListData: [
            {operator: '补', startMonth: '201701', endMonth: '201703', base: '17817', action: '', disabled: true},
            {operator: '补', startMonth: '201704', endMonth: '201706', base: '19000', action: '', disabled: true},
            {operator: '', startMonth: '', endMonth: '', base: '', action: '', disabled: true},
          ],
          operatorListColumns: [
            {title: '', key: 'operator', align: 'center', width: 100,
              render: (h, params) => {
                return h('div', {style: {textAlign: 'left'}}, [
                  h('span', params.row.operator),
                ]);
              }
            },
            {
              title: '起缴月份',
              key: 'startMonth',
              align: 'center',
              render: (h, params) => {
                return h('div', [
                  h('i-input', {
                    props: {value: params.row.startMonth, disabled: params.row.disabled},
                  }, params.row.startMonth)
                ]);
              }
            },
            {
              title: '截止月份',
              key: 'endMonth',
              align: 'center',
              render: (h, params) => {
                return h('div', [
                  h('i-input', {
                    props: {value: params.row.endMonth, disabled: params.row.disabled},
                  }, params.row.endMonth)
                ]);
              }
            },
            {
              title: '基数',
              key: 'base',
              align: 'center',
              render: (h, params) => {
                return h('div', [
                  h('i-input', {
                    props: {value: params.row.base, disabled: params.row.disabled},
                  }, params.row.base)
                ]);
              }
            },
            {
              title: '操作',
              key: 'base',
              align: 'center',
              width: 130,
              render: (h, params) => {
                return h('div', [
                  h('Button', {
                    props: {type: 'default', shape: 'circle', icon: 'edit', size: 'small'},
                    style: {marginRight: '5px'},
                    on: {
                      click: () => {
                        params.row.disabled = false;
                      }
                    }
                  }),
                  h('Button', {
                    props: {type: 'default', shape: 'circle', icon: 'minus', size: 'small'},
                    style: {marginRight: '5px'},
                    on: {
                      click: () => {
                        this.remove(params.index);
                      }
                    }
                  }),
                  h('Button', {
                    props: {type: 'default', shape: 'circle', icon: 'plus', size: 'small'},
                    on: {
                      click: () => {
                        this.insert(params.index);
                      }
                    }
                  })
                ]);
              }
            }
          ],
          doNotes: '',
          refuseNotes: ''
        }
      }
    },
    mounted() {

    },
    computed: {

    },
    methods: {
      insert(index) {
        this.socialSecurityPayOperator.operatorListData.splice(index, 0, {operator: '', startMonth: '', endMonth: '', base: '', action: '', disabled: false});
      },
      remove (index) {
        this.socialSecurityPayOperator.operatorListData.splice(index, 1);
      }
    }
  }
</script>
<style scoped>
  .mt20 {margin-top: 20px;}
</style>
