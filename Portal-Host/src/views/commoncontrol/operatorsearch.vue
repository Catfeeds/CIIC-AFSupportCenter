<template>
  <div>
    <Form :label-width=120>
      <Row>
        <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
          <Form-item label="服务中心：">
            <Select v-model="operatorSearchData.serviceCenterValue" style="width: 100%;">
              <Option v-for="item in operatorSearchData.serviceCenterList" :value="item.value" :key="item.value">{{item.label}}</Option>
            </Select>
          </Form-item>
          <Form-item label="企业社保账户分类：">
            <Button type="ghost" @click="operatorSearchData.isShowAccountType = true" long>&nbsp;</Button>
          </Form-item>
          <Form-item label="身份证号：">
            <Input v-model="operatorSearchData.idNumber" placeholder="请输入..."></Input>
          </Form-item>
          <Form-item label="任务发起时间：">
            <DatePicker v-model="operatorSearchData.taskStartTime" type="daterange" placement="bottom" placeholder="选择日期"></DatePicker>
          </Form-item>
        </Col>
        <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
          <Form-item label="客户编号：">
            <Input v-model="operatorSearchData.customerNumber" placeholder="请输入..."></Input>
          </Form-item>
          <Form-item label="人员分类：">
            <Select v-model="operatorSearchData.personTypeValue" style="width: 100%;">
              <Option v-for="item in operatorSearchData.personTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
            </Select>
          </Form-item>
          <Form-item label="任务单类型：">
            <Select v-model="operatorSearchData.taskTypeValue" style="width: 100%;">
              <Option v-for="item in operatorSearchData.taskTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
            </Select>
          </Form-item>
        </Col>
        <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
          <Form-item label="客户名称：">
            <Button type="ghost" @click="operatorSearchData.isShowCustomerName = true" long>&nbsp;</Button>
          </Form-item>
          <Form-item label="雇员编号：">
            <Input v-model="operatorSearchData.employeeNumber" placeholder="请输入..."></Input>
          </Form-item>
          <Form-item label="结算中心：">
            <Select v-model="operatorSearchData.region" style="width: 100%;">
              <Option v-for="item in operatorSearchData.regionList" :value="item.value" :key="item.value">{{item.label}}</Option>
            </Select>
          </Form-item>
        </Col>
        <Col :xs="{span: 4, offset: 1}" :lg="{span: 4, offset: 1}">
          <Form-item label="账户类型：">
            <Select v-model="operatorSearchData.accountTypeValue" style="width: 100%;">
              <Option v-for="item in operatorSearchData.accountTypeList" :value="item.value" :key="item.value">{{item.label}}</Option>
            </Select>
          </Form-item>
          <Form-item label="雇员姓名：">
            <Input v-model="operatorSearchData.employeeName" placeholder="请输入..."></Input>
          </Form-item>
          <Form-item label="是否加急：">
            <Select v-model="operatorSearchData.emergency" style="width: 100%;">
              <Option v-for="item in operatorSearchData.emergencyList" :value="item.value" :key="item.value">{{item.label}}</Option>
            </Select>
          </Form-item>
        </Col>
      </Row>
      <Row>
        <Row>
          <Col :xs="{span: 2, offset: 17}" :lg="{span: 2, offset: 17}">
            <Button type="primary" @click="" icon="ios-search">查询</Button>
          </Col>
          <Col :xs="{span: 2}" :lg="{span: 2}">
            <Button type="default" @click="">重置</Button>
          </Col>
        </Row>
      </Row>
    </Form>

    <!-- 客户名称 模态框 -->
    <Modal
      v-model="operatorSearchData.isShowCustomerName"
      title="选择客户"
      @on-ok="ok"
      @on-cancel="cancel">
      <customer-modal></customer-modal>
    </Modal>

    <!-- 企业社保账户分类 模态框 -->
    <Modal
      v-model="operatorSearchData.isShowAccountType"
      title="企业社保账户分类"
      @on-ok="ok"
      @on-cancel="cancel">
      <company-account-search-modal></company-account-search-modal>
    </Modal>
  </div>
</template>
<script>
  import customerModal from "./customermodal.vue"
  import companyAccountSearchModal from "./companyaccountsearchmodal.vue"

  export default {
    name: 'operatorsearch',
    components: {customerModal, companyAccountSearchModal},
    data() {
      return {
        operatorSearchData: {
          serviceCenterValue: '',
          serviceCenterList: [
            {value: 1, label: '大客户', children: [{value: '1-1', label: '大客户1'}, {value: '1-2', label: '大客户2'}]},
            {value: 2, label: '日本客户'},
            {value: 3, label: '虹桥'},
            {value: 4, label: '浦东'},
            {value: 5, label: '东区1'},
            {value: 6, label: '东区2'}
          ], //客服中心

          customerNumber: '', //客户编号

          isShowCustomerName: false, //客户名称显示模态框

          accountTypeValue: '',
          accountTypeList: [
            {value: '1', label: '独立户'},
            {value: '2', label: '大库'},
            {value: '3', label: '外包'}
          ], //账户类型

          isShowAccountType: false, //客户名称显示模态框

          personTypeValue: '',
          personTypeList: [
            {value: '1', label: '本地'},
            {value: '2', label: '外地'},
            {value: '3', label: '外籍三险'},
            {value: '4', label: '外籍五险'}
          ], //人员分类

          employeeNumber: '', //雇员编号

          employeeName: '', //雇员姓名

          idNumber: '', //身份证号

          taskTypeValue: '',
          taskTypeList: [
            {value: '1', label: '新开转入'},
            {value: '2', label: '调整'},
            {value: '3', label: '补缴'},
            {value: '4', label: '转出'}
          ], //人员分类

          regionList: [
            {value: '1', label: '徐汇'},
            {value: '2', label: '长宁'},
            {value: '3', label: '浦东'},
            {value: '4', label: '卢湾'},
            {value: '5', label: '静安'},
            {value: '6', label: '黄浦'}
          ],
          region: '', //结算区域

          emergency: '',
          emergencyList: [
            {value: '1', label: ''},
            {value: '2', label: '加急'}
          ], //是否加急

          taskStartTime: '' //任务发起时间
        }
      }
    },
    methods: {
      ok () {
        this.$Message.info('点击了确定');
      },
      cancel () {
        this.$Message.info('点击了取消');
      }
    }
  }
</script>
