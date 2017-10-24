<template>
  <div class="layout" :class="{'layout-hide-text': spanLeft < 4}">
    <div class="layout-ceiling layoutMenuLeft">
      <Row type="flex" class="topFlex">
        <i-col :span="spanLeft" class="layout-menu-left">
          <img src="static/img/cute.png" alt="金钱豹">
          <span class="logoFonts">Payroll</span>
        </i-col>
        <i-col :span="spanRight">
          <i-button type="text" @click="toggleClick">
            <Icon type="navicon" size="32"></Icon>
          </i-button>
          <div class="layout-ceiling-main Badge">
            <ul>
              <li>
                <Poptip trigger="hover" title="" content="待办事项" placement="bottom-start">
                  <Badge count="10" overflow-count="99" class="yellow">
                    <Icon type="ios-bell-outline" :size="size"></Icon>
                  </Badge>
                </Poptip>
              </li>
              <li>
                <Poptip trigger="hover" title="" content="站内信" placement="bottom-start">
                  <Badge count="3" overflow-count="99" class="green">
                    <Icon type="ios-email-outline" :size="size"></Icon>
                  </Badge>
                </Poptip>
              </li>
              <li>
                <Poptip trigger="hover" title="" content="帮助" placement="bottom-start">
                  <Badge count="100" overflow-count="99">
                    <Icon type="ios-help-outline" :size="size"></Icon>
                  </Badge>
                </Poptip>
              </li>
              <li class="userCenter">
                <Dropdown placement="bottom-start" @on-click="userCenterHandler">
                  <div class="user-panel user">
                    <div class="image">
                      <!-- <Avatar src="static/img/user.jpg" size="large" /> -->
                      <img src="static/img/user.jpg" alt="User Image" class="img-circle">
                      <span>欢迎您，{{userName}}！</span>
                    </div>
                  </div>
                  <Dropdown-menu slot="list">
                    <Dropdown-item name="logout">退出</Dropdown-item>
                    <Dropdown-item divided>个人中心</Dropdown-item>
                  </Dropdown-menu>
                </Dropdown>
              </li>
            </ul>
          </div>
        </i-col>
      </Row>
    </div>
    <Row type="flex">
      <i-col :span="spanLeft" class="layout-menu-left">
        <Menu theme="dark" width="auto" class="menuSet" :class="{'removeArror': spanLeft < 3}" accordion :open-names="openNames"
          @on-open-change="onOpenChange" @on-select="onSelect">
          <Submenu v-for="(item, index) in leftNavigationList" :name="item.id" :key="item.id">
            <template slot="title">
              <Icon :type="item.icon" :size="size"></Icon>
              <span :class="{'layout-text':layoutOut}">{{item.key}}</span>
            </template>
            <Menu-item v-for="subitem in item.children" :name="subitem.cid" :key="subitem.cid">
              <router-link :to="{name:subitem.crouter}" v-menuInner="{set:set}">{{subitem.ckey}}</router-link>
            </Menu-item>
          </Submenu>

<!--
          <Submenu name="1">
            <template slot="title">
              <Icon type="ios-paper" :size="size"></Icon>
              <span :class="{'layout-text':layoutOut}">薪资账套维护</span>
            </template>
            <Menu-item name="1-1" :class="{'layout-text':layoutOut}">
              <router-link :to="{name:'sl'}" v-menuInner="{set:set}">薪资项模板列表</router-link>
            </Menu-item>
          </Submenu> -->
        </Menu>
      </i-col>
      <i-col :span="spanRight">
        <div class="layout-content">
          <div class="wrapper">
            <div class="layout-breadcrumb">
              <Breadcrumb>
                <Breadcrumb-item @click="setIndex">
                  <router-link to="/main" id="level1" v-menuInner="{set:setIndex}">{{breadCrumb1}}</router-link>
                </Breadcrumb-item>
                <Breadcrumb-item v-show="breadCrumbBoolean2" id="level2">{{breadCrumb2}}</Breadcrumb-item>
                <Breadcrumb-item v-show="breadCrumbBoolean3" id="level3">{{breadCrumb3}}</Breadcrumb-item>
              </Breadcrumb>
            </div>
            <div class="layout-content-main">
              <transition name="fade" mode="out-in">
                <router-view :key="key"></router-view>
              </transition>
            </div>
          </div>
          <div class="layout-copy">
            <span>2017 &copy; 薪酬管理工资单</span>
          </div>
        </div>
      </i-col>
    </Row>
  </div>
</template>
<script>

import {mapActions,mapGetters} from 'vuex'
import EventTypes from '../store/EventTypes'

  export default {
    name:"leftnavigation",
    data() {
      return {
        items: [],
        personOnline: true,
        spanLeft: 4,
        spanRight: 20,
        size: 16, //icon的大小
        layoutOut: true, //控制左側菜單的顯示隱藏,
        breadCrumb1: "首页",
        breadCrumb2: "",
        breadCrumb3: "",
        transfer: true,
        breadCrumbBoolean2: false,
        breadCrumbBoolean3: false,
        openNames: ["1"],
        userName: ''
      }
    },
    created() {
      this.openNames = localStorage.getItem("openNames").split("");
    },
    watch: {
      $route() {
        this.getBreadCrumb();
      }
    },
    mounted() {
      this.getBreadCrumb();
      this.setList();
      // this.items = this.leftNavigationList();
    },
    computed: {
      key() {
        return this.$route.path.replace(/\//g, '_')
      },
      iconSize() {
        return this.spanLeft === 4 ? 24 : 24;
      },
      ...mapGetters('leftNaviModule',[
        'leftNavigationList'
      ]),
    },
    methods: {
      ...mapActions('leftNaviModule',{
        setList:EventTypes.LEFTNAVIGATION_SETLIST
      }),

      toggleClick() {
        if (this.spanLeft === 4) {
          this.spanLeft = 1;
          this.spanRight = 23;
          this.size = 26;
          this.layoutOut = true; //隐藏
        } else {
          this.spanLeft = 4;
          this.spanRight = 20;
          this.size = 16;
          this.layoutOut = false;
        }
      },
      onSelect(name) {},
      onOpenChange(name) {
        this.openNames = name;
        if (this.size == 26) {
          this.spanLeft = 4;
          this.spanRight = 20;
          this.size = 16;
          this.layoutOut = false;

        } else {
          return false;
        }
      },
      set(breadCrumb3) {
        this.breadCrumbBoolean2 = true;
        this.breadCrumbBoolean3 = true;
      },
      setIndex() {
        this.breadCrumbBoolean2 = false;
        this.breadCrumbBoolean3 = false;
      },
      getBreadCrumb() {
        if (localStorage.getItem('level2') != "undefined" && localStorage.getItem('level3') != "undefined") {
          this.breadCrumb2 = localStorage.getItem('level2');
          if (!this.$local.edits) {
            this.breadCrumb3 = localStorage.getItem('level3');
          } else {
            this.$local.edits = !this.$local.edits;
            this.breadCrumb3 = localStorage.getItem('level4');
          }
          this.breadCrumbBoolean2 = this.breadCrumbBoolean3 = true;
        }
      },
      userCenterHandler(name) {
        switch (name) {
          case 'logout':
            this.$router.push({
              name: 'login'
            });
            this.$local.save('userName', '');
            break;
        }
      }
    },
    directives: {
      menuInner: {
        bind: function (el, binding, vnode, oldValue) {
          var s = JSON.stringify
          el.onclick = function () {
            //binding.value 获取data中绑定的值
            binding.value.set(s(el.innerText));
          };
        }
      }
    },
    beforeRouteEnter: (to, from, next) => {
      next((vm) => {
        vm.userName = vm.$local.fetch('userName');
        // console.log(data);
      })
    }
  }

</script>
<style scoped>
  .layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }

  .layout-logo {
    width: 100px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
  }

  .layout-header {
    height: 60px;
    background: #fff;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .1);
  }

  .layout-ceiling {
    /* overflow: hidden;  */
  }

  .layout-ceiling-main {
    float: right;
    /* margin-right: 15px; */
  }

  .layout-ceiling-main a {
    color: #fff;
  }

  .layout-breadcrumb {
    padding: 10px 15px 0;
  }

  .layout-content {
    min-height: 200px;
    margin: 15px;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
  }

  .layout-content-main {
    padding: 10px;
  }

  .layout-copy {
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
  }

  .layout-menu-left {
    background: #1a2226;
  }

  .layout-header {
    height: 60px;
    background: #fff;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .1);
  }

  .layout-logo-left {
    width: 90%;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    margin: 15px auto;
  }

  .layout-hide-text .layout-text {
    /* animation:show 2s linear;
        -webkit-animation:show 2s linear;
        -webkit-transition: all 2s; */
    display: none;
  }

  @keyframes show {
    from {
      height: 100%;
    }
    to {
      height: 0;
    }
  }

  @-webkit-keyframes show {
    from {
      height: 100%;
    }
    to {
      height: 0;
    }
  }

  .ivu-col {
    transition: width .2s ease-in-out;
  }

  .ivu-menu-dark {
    background: #222d32;
    border-left: 2px solid #222d32;
    border-right: none;
  }

  .ivu-menu-dark.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu),
  .ivu-menu-dark.ivu-menu-vertical .ivu-menu-submenu-title-active:not(.ivu-menu-submenu) {
    background: #1e282c;
    border-right: none;
    color: #fff;
  }

  .ivu-menu-dark.ivu-menu-vertical .ivu-menu-item:hover,
  .ivu-menu-dark.ivu-menu-vertical .ivu-menu-submenu-title:hover,
  .ivu-menu-opened:hover {
    background: #1a2226;
    border-right: none;
  }

  .ivu-btn-text {
    color: #fff;
  }

  .layoutMenuLeft img {
    vertical-align: middle
  }

  .layoutMenuLeft .ivu-col {
    background: #3c8dbc;
  }

  .layoutMenuLeft .layout-menu-left {
    background: #367fa9;
  }

  .ivu-badge {
    color: #fff;
    margin-right: 20px;
  }

  .logoFonts {
    color: #fff;
    font-size: 18px;
  }

  .wrapper {
    min-height: 94%;
    height: auto !important;
    height: 100%;
  }

  .bold {
    font-size: 16px;
  }

  .ivu-dropdown-item {
    width: 100%;
    text-align: center;
  }

</style>
