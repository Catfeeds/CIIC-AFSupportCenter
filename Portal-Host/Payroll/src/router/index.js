import Vue from 'vue'
import Router from 'vue-router'

const main = r => require.ensure([], () => r(require('@/components/main')), 'main') //首页
const login = r => require.ensure([], () => r(require('@/components/login')), 'login') //登录
const charts = r => require.ensure([], () => r(require('@/components/charts')), 'charts') //报表
const payrollDataSourceImport = r => require.ensure([], () => r(require('@/views/payrolldatasource/payrolldatasourceimport')), 'payrollDataSourceImport') //工资单数据源导入
const payrollDataSourceSearch = r => require.ensure([], () => r(require('@/views/payrolldatasource/payrolldatasourcesearch')), 'payrollDataSourceSearch') //工资单数据源查询
const payrollTaskSubmit = r => require.ensure([], () => r(require('@/views/payrolltask/payrolltasksubmit')), 'payrollTaskSubmit') //工资单任务单提交
const payrollTaskEdit = r => require.ensure([], () => r(require('@/views/payrolltask/payrolltaskedit')), 'payrollTaskEdit') //工资单任务单编辑
const payrollTaskSubmitDetial = r => require.ensure([], () => r(require('@/views/payrolltask/payrolltasksubmitdetial')), 'payrollTaskSubmitDetial') //工资单任务单提交详细
const payrollPreview = r => require.ensure([], () => r(require('@/components/payrolltask/payrollpreview')), 'payrollPreview') //工资单任务单提交预览
const payrollTaskDetial = r => require.ensure([], () => r(require('@/views/payrolltask/payrolltaskdetial')), 'payrollTaskDetial') //工资单任务单明细

const payrollTaskApprove = r => require.ensure([], () => r(require('@/views/payrolltask/payrolltaskapprove')), 'payrollTaskApprove') //工资单任务单审批
const payrollTaskSearch = r => require.ensure([], () => r(require('@/views/payrolltask/payrolltasksearch')), 'payrollTaskSearch') //工资单任务单查询

const payrollPrintManage = r => require.ensure([], () => r(require('@/views/payrollprint/payrollprintmanage')), 'payrollPrintManage') //工资单任务单打印管理

const payrollGrantManage = r => require.ensure([], () => r(require('@/views/payrollgrant/payrollgrantmanage')), 'payrollGrantManage') //工资单发放管理

Vue.use(Router)
let router = new Router({

  linkActiveClass: 'is-active',
  routes: [
    {
      path: '/',
      component: login
    },
    {
      path: '/main',
      component: main,
      children: [
        {
          path: '', //默认显示的自路由
          name:'main',
          component: charts
        },
        {
          path: '/payrolldatasourceimport',
          name:'dimport',
          component: payrollDataSourceImport,
          meta:{
            level1:'首页',
            level2:"工资单数据源管理",
            level3:"工资单数据源导入",
            openNames:['1']
          }
        },
        {
          path: '/payrolldatasourcesearch',
          name:'dsearch',
          component: payrollDataSourceSearch,
          meta:{
            level1:'首页',
            level2:"工资单数据源管理",
            level3:"工资单数据源查询",
            openNames:['1']
          }
        },
        {
          path: '/payrolltasksubmit',
          name:'ptasksubmit',
          component: payrollTaskSubmit,
          meta:{
            level1:'首页',
            level2:"工资单任务单管理",
            level3:"工资单任务单提交",
            openNames:['1']
          }
        },
        {
          path: '/payrolltaskedit',
          name:'ptaskedit',
          component: payrollTaskEdit,
          meta:{
            level1:'首页',
            level2:"工资单任务单管理",
            level3:"工资单任务单编辑",
            openNames:['1']
          }
        },
        {
          path: '/payrolltasksubmitdetial',
          name:'ptasksubmitdetial',
          component: payrollTaskSubmitDetial,
          meta:{
            level1:'首页',
            level2:"工资单任务单管理",
            level3:"工资单任务单提交详细",
            openNames:['1']
          }
        },
        {
          path: '/payrollpreview',
          name:'payrollpreview',
          component: payrollPreview,
          meta:{
            level1:'首页',
            level2:"工资单任务单管理",
            level3:"工资单任务单预览",
            openNames:['1']
          }
        },
        {
          path: '/payrolltaskdetial',
          name:'ptaskdetial',
          component: payrollTaskDetial,
          meta:{
            level1:'首页',
            level2:"工资单任务单管理",
            level3:"工资单任务单明细",
            openNames:['1']
          }
        },
        {
          path: '/payrolltaskapprove',
          name:'ptaskapproval',
          component: payrollTaskApprove,
          meta:{
            level1:'首页',
            level2:"工资单任务单管理",
            level3:"工资单任务单审批",
            openNames:['1']
          }
        },
        {
          path: '/payrolltasksearch',
          name:'ptasksearch',
          component: payrollTaskSearch,
          meta:{
            level1:'首页',
            level2:"工资单任务单管理",
            level3:"工资单任务单查询",
            openNames:['1']
          }
        },
        {
          path: '/payrollprintmanage',
          name:'ptaskprint',
          component: payrollPrintManage,
          meta:{
            level1:'首页',
            level2:"工资单打印管理",
            level3:"工资单打印任务单管理",
            openNames:['1']
          }
        },
        {
          path: '/payrollgrantmanage',
          name:'ptaskgrant',
          component: payrollGrantManage,
          meta:{
            level1:'首页',
            level2:"工资单发放管理",
            level3:"工资单任务单发放",
            openNames:['1']
          }
        }
      ]
      }
   ]})

router.beforeEach((to,from,next)=>{
  // console.log(router.app.$local);
    window.document.title="薪酬-工资单管理系统";
    localStorage.setItem('level1',to.meta.level1);
    localStorage.setItem('level2',to.meta.level2);
    localStorage.setItem('level3',to.meta.level3);
    localStorage.setItem('level4',to.meta.level4);
    localStorage.setItem("openNames",[to.meta.openNames]);
    next();
});
router.afterEach((to,from)=>{});


export default router;
