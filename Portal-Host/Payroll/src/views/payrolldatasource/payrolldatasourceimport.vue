<template>
    <div class="smList">
        <Collapse v-model="value1" accordion>
            <Panel name="1">
                <div slot="content">
                     <Row>
                        <Col :xs="{ span: 8, offset: 1 }" :lg="{ span: 8, offset: 1 }">
                        <Form :model="formItem" :label-width=100>
                            <Form-item label="文件名称：">
                                <Select
                                    v-model="formItem.client"
                                    filterable
                                    remote
                                    :remote-method="searchFileName"
                                    :loading="loading1">
                                    <Option v-for="option in optionsClient" :value="option.value" :key="option.value">{{option.label}}</Option>
                                </Select>
                            </Form-item>
                            <Form-item label="操作人：">
                                <Select
                                    v-model="formItem.client"
                                    filterable
                                    remote
                                    :remote-method="searchOpreator"
                                    :loading="loading1">
                                    <Option v-for="option in optionsClient" :value="option.value" :key="option.value">{{option.label}}</Option>
                                </Select>
                            </Form-item>
                        </Form>
                        </Col>
                        <Col :xs="{ span: 8, offset: 1 }" :lg="{ span: 8, offset: 1}" class="checkBtn">
                        <Form :model="formItem" :label-width=100>
                            <Form-item label="操作时间：">
                                <Select
                                    v-model="formItem.name"
                                    filterable
                                    remote
                                    :remote-method="searchOpreatTime"
                                    :loading="loading1">
                                    <Option v-for="option in optionsName" :value="option.value" :key="option.value">{{option.label}}</Option>
                                </Select>
                            </Form-item>
                            <Form-item label="管理方名称：">
                                <Select
                                    v-model="formItem.name"
                                    filterable
                                    remote
                                    :remote-method="searchManager"
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
        <div class="create">
            <Button type="primary" @click="add">
                新建
            </Button>
        </div>
        <Table border :columns="importColumns" :data="payrolldatasourceimportlist"></Table>
        <Page :total="100" show-sizer show-elevator></Page>
    </div>
</template>
<script>

    import {mapActions,mapGetters} from 'vuex'
    import * as eventType from '../../store/EventTypes/PayrollDataSourceImportType'

    export default {
        name:"payrollDataSourceImport",
        data() {
            return {
                optionsClient:[],
                optionsName:[],
                loading1:false,
                formItem: {
                    client:"",
                },
                value1: '1',
                importColumns: [
                   {
                        title: '序号',
                        key: 'id',
                        width: 70
                    },
                    {
                        title: '导入文件',
                        key: 'importFile',
                        width: 300,
                        render: (h, params) => {
                            return h('div',{
                                style:{
                                    cursor:'pointer',
                                    textDecoration:'underline'
                                }
                            },params.row.importFile);
                        }
                    },
                    {
                        title: '总记录数',
                        key: 'total'
                    },
                    {
                        title: '操作人',
                        key: 'operator'
                    },
                    {
                        title: '操作时间',
                        sortable: true,
                        key: 'operattime'
                    },
                    {
                        title: '状态',
                        key: 'state'
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
                                type: 'error',
                                size: 'small'
                              },
                              style: {
                                marginRight: '5px'
                              },
                              on: {
                                click: () => {
                                  let _that = this;
                                  this.$Modal.confirm({
                                    content: '确定进行删除？',
                                    onOk() {
                                      _that.remove(params.index)
                                    }
                                  })
                                }
                              }
                            }, '移除'),
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
            'payrolldatasourceimportlist'
          ]),
        },
        methods: {
           ...mapActions({
              setList:eventType.PAYROLLDATASOURCEIMPORT_SETLIST
            }),
            add(){},
            remove(index) {},
            searchFileName(){},
            searchOpreator(){},
            searchOpreatTime(){},
            searchManager(){}
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
</style>
