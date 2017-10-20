<template>
  <Form>
    <Row>
      <Col :xs="{span: 9, offset: 1}" :lg="{span: 9, offset: 1}">
        <Form>
          <Form-item>
            <Input v-model="mUserNumber" icon="ios-search" placeholder="请输入参保户登记码..."></Input>
          </Form-item>
        </Form>
      </Col>
      <Col :xs="{span: 12, offset: 1}" :lg="{span: 12, offset: 1}">
        <Form>
          <Form-item>
            <Input v-model="mCompanyName" icon="ios-search" placeholder="请输入养老金用公司名称..."></Input>
          </Form-item>
        </Form>
      </Col>
    </Row>
    <Row>
      <Col :xs="{span: 22, offset: 1}" :lg="{span: 22, offset: 1}">
        <Table highlight-row :columns="sSocialSecurityTypeColumns" :data="filterUser"></Table>
      </Col>
    </Row>
  </Form>
</template>
<script>
  export default {
    name:"companyAccountSearchModal",
    data() {
      return {
        mUserNumber: '', //参保户登记码
        mCompanyName: '', //养老金用公司名称
        sSocialSecurityTypeData: [
          {eid: '34235329', name: '中智大库'},
          {eid: '23434324', name: '中智外包'},
          {eid: '56565659', name: '独立户-欧莱雅'},
          {eid: '34543543', name: '独立户-迅达电梯'}
        ],
        sSocialSecurityTypeColumns: [
          {title: '参保户登记码', key: 'eid'},
          {title: '养老金用公司名称', key: 'name'}
        ]
      }
    },
    mounted() {

    },
    computed: {
      filterUser() {
        return this.filterData(this.mUserNumber, this.mCompanyName)
      }
    },
    methods: {
      filterData(filterKey1, filterKey2) {
        if(filterKey1 === '' && filterKey2 === '') {
          return this.sSocialSecurityTypeData;
        }
        return this.sSocialSecurityTypeData.filter(function(sType) {
          return Object.keys(sType).some(function(key) {
            return String(sType[key]).toLowerCase().indexOf(filterKey1) > -1
          })
        })
      }
    }
  }
</script>
