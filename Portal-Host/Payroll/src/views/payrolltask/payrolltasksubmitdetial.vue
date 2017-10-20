<template>
  <div class="smEdit">
      <Card>
        <Row>
          <Col :xs="{ span: 8, offset: 1 }" :lg="{ span: 8, offset: 1 }">
            <Form :model="formItem" :label-width=100>
              <Form-item label="任务单编号：">
                <span>{{ formItem.taskNo}}</span>
              </Form-item>
              <Form-item label="管理方编号：">
                <span>{{ formItem.managementNo }}</span>
              </Form-item>
              <Form-item label="薪资周期：">
                <span>{{ formItem.salaryperiod }}</span>
              </Form-item>
              <Form-item label="中方人数：">
                <span>{{ formItem.china }}</span>
              </Form-item>
              <Form-item label="工资单形式：">
                <span>{{ formItem.payrolltype }}</span>
              </Form-item>
            </Form>
          </Col>
          <Col :xs="{ span: 8, offset: 1 }" :lg="{ span: 8, offset: 1}">
            <Form :model="formItem" :label-width=100>
              <Form-item label="任务单标题：">
                <span>{{ formItem.taskTitle}}</span>
              </Form-item>
              <Form-item label="管理方名称：">
                <span>{{ formItem.management }}</span>
              </Form-item>
              <Form-item label="总人数：">
                <span>{{ formItem.total }}</span>
              </Form-item>
              <Form-item label="外方人数：">
                <span>{{ formItem.foreign }}</span>
              </Form-item>
            </Form>
          </Col>
      </Row>
    </Card>
    <div class="buttons">
      <div class="previewDiv" style="display:inline-block">
        <Button type="primary" class="addEmp" @click="preview">预览</Button>
      </div>
      <div class="addEmpDiv" style="display:inline-block">
        <Button type="primary" class="addEmp" @click="addEmployee">增加人员</Button>
      </div>
      <div class="deleteEmpDiv" style="display:inline-block">
        <Button type="error" class="deleteEmp" @click="removeSelected">删除人员</Button>
      </div>
      <div class="exportEmpDiv" style="display:inline-block">
        <Button type="primary" class="addEmp" :loading="loading" @click="toLoading">导出明细数据</Button>
      </div>
      <div class="submitDiv" style="display:inline-block">
        <Button type="primary" class="addEmp" @click="submit">提交</Button>
      </div>
       <div class="cancelDiv" style="display:inline-block">
        <Button type="primary" class="addEmp" @click="cancel">取消</Button>
      </div>
      <div class="returnDiv" style="display:inline-block">
        <Button type="primary" class="addEmp" @click="back">返回</Button>
      </div>
    </div>
    <Table border :columns="empColumns" :data="payrollemployeelist" class="empTable"></Table>
    <Page :total="100" show-sizer show-elevator></Page>
    <add-emp-modal :showModal="showModal" @closeModal="closeModal"></add-emp-modal>
  </div>
</template>
<script>
  import {mapActions,mapGetters} from 'vuex'
  import * as eventType from '../../store/EventTypes/PayrollTaskSubmitDetialType'
  import addEmpModal from '../../components/payrolltask/addEmpModel.vue'

  export default {
    data() {
      return {
        loading: false,
        selectedItems: [],
        modal1: false,
        showModal:false,
        formItem: {
          taskNo:"CAPIT00001",
          taskTitle:"201608上海德邦A类雇员",
          management:"邦德集团",
          managementNo:"C00000001",
          salaryperiod:"201608",
          calculatebatch:"00001,000002",
          payrolltemplate:"上海邦德模板A",
          total:"100",
          china:"95",
          foreign:"5",
          payrolltype:"纸质"
        },
        empColumns: [{
            type: 'selection',
            width: 60,
            sortable: true,
            align: 'center'
          },
          {
            title: '雇员编码',
            sortable: true,
            key: 'empId'
          },
          {
            title: '雇员名称',
            sortable: true,
            key: 'empName'
          },
          {
            title: '管理方',
            sortable: true,
            key: 'management'
          },
          {
            title: '薪资周期',
            sortable: true,
            key: 'salaryperiod',
            width: 150,
            align: 'center'
          },
          {
            title: '实际基本工资',
            sortable: true,
            key: 'basicSalary',
            width: 150,
            align: 'center'
          },
           {
            title: '应税工资合计',
            sortable: true,
            key: 'taxTotalSalary',
            width: 150,
            align: 'center'
          },
          {
            title: '个人所得税',
            sortable: true,
            key: 'persnonTax',
            width: 150,
            align: 'center'
          },
          {
            title: '工资单模板',
            sortable: true,
            key: 'payrolltemplate',
            width: 150,
            align: 'center'
          },
          {
            title: '操作',
            key: 'action',
            width: 150,
            align: 'center',
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
    methods: {
     ...mapActions({
        setList:eventType.PAYROLLTEMPLOYEE_SETLIST
      }),
      preview() {
        this.$router.push({
          name: 'payrollpreview'
        });
      },
      addEmployee(){
        this.showModal=true;
      },
      closeModal(val){
        this.showModal=val;
      },
      back() {
        this.$local.back();
      },
      save() {
        this.isNotSaved = false;
      },
      toLoading() {
        this.loading = true;
        window.setTimeout(() => {
          this.loading = false;
          this.$Message.info('导出成功!');
        }, 2000);
      },
      cancel() {
        this.$Modal.confirm({
          content:'确认要取消？',
          onOk(){
            this.$local.back();
          }
        })
      },
      submit(){
         this.$Modal.confirm({
          content:'确认要提交？',
          onOk(){
            this.$local.back();
          }
        })
      },
      remove(index) {
        this.data7.splice(index, 1);
      },
      removeSelected() {
        let selectedItems = this.selectedItems;
        let dataItem = this.data7; //原始数据
        if (selectedItems.length == 0) {
          this.$Modal.warning({
            content: '请选择要删除的项！',
          })
        } else {
          for (var s = 0; s < selectedItems.length; s++) {
            for (var d = 0; d < dataItem.length; d++) {
              if (dataItem[d].name == selectedItems[s].name) {
                dataItem[d].del = "deleted"; //标记要删除的元素
              }
            }
          }
          for (var i = 0; i < this.data7.length; i++) {
            if (!!this.data7[i].del) { //判断元素是否存在
              this.data7.splice(i, 1); //从索引处开始删除
              i--;
              this.selectedItems = [];
            }
          }
        }
      }
    },
    mounted() {
      this.setList();
    },
    computed: {
    ...mapGetters([
      'payrollemployeelist'
    ]),
  },
  components:{
    addEmpModal
  }
  }
</script>
<style scoped>
  .ivu-card {
    margin-bottom: 20px;
  }

  .buttons {
    margin: 15px 0;
  }

  .smEdit .ivu-btn {
    clear: both;
  }

  .addNewEmp {
    margin: 20px 0;
    background: #eee;
    padding: 20px;
  }

  .addNewEmp .ivu-form-item {
    width: 30%;
    display: inline-block;
    margin-right: 20px;
  }

  .clientFixed {
    margin-bottom: 20px;
    width: 90%;
  }

  .rows {
    width: 45%;
    float: left;
  }

</style>
