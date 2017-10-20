<template>
      <Table :columns="payrollChildTaskColumns" :data="row" class="pchildtask-table"></Table>
</template>
<script>
    export default {
        data(){
          return {
            payrollChildTaskColumns:[
                    {
                        title: '任务单编号',
                        key: 'taskNo',
                        width: 115,
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
                        width: 60
                    },
                    {
                        title: '中方人数',
                        key: 'china',
                        width: 60
                    },
                    {
                        title: '外方人数',
                        key: 'foreign',
                        width: 60
                    },
                    {
                        title: '工资单形式',
                        key: 'payrolltype',
                        width: 70
                    },
                    {
                        title: '状态',
                        key: 'state',
                        width: 70
                    },
                    {
                        title: '实际发布日期',
                        key: 'publishdate',
                        width: 100
                    },
                    {
                        title: '操作',
                        key: 'action',
                        align: 'center',
                        width: 250,
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
                                            this.toEdit(params.index)
                                        }
                                    }
                                }, '修改'),
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
                                            this.toShow(params.index)
                                        }
                                    }
                                }, '明细'),
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
        methods: {
          toEdit(data) {
            this.$router.push({
              name: 'ptaskedit',
              params: {
                data: data
              }
            });
          },
          toShow(data){
            this.$router.push({
              name: 'ptaskdetial',
              params: {
                data: data
              }
            });
          },
          toDetail(data){
           this.$router.push({
              name: 'ptasksubmitdetial',
              params: {
                data: data
              }
            });
          },
          invalid(index) {
            this.$Modal.confirm({
              content:'确认要失效吗？',
              onOk(){
                this.$Message.info('失效成功!');
              }
            })
          }
        },
        props: {
            row: Array
        }
    };
</script>
<style scoped>
</style>
