<template>
  <div class="login">
    <div class="loginForm">
      <div class="logo">
        <!-- <img src="static/img/cutie.jpg"> -->
      </div>
      <Form ref="formValidate" :model="formValidate" :label-width=80 :rules="ruleValidate">
        <Form-item label="用户名" prop="name">
          <Input v-model.trim="formValidate.name" placeholder="请输入" autofocus></Input>
        </Form-item>
        <Form-item label="密码" prop="password">
          <Input type="password" v-model.trim="formValidate.password" placeholder="请输入" @on-enter="handleLogin('formValidate')"></Input>
        </Form-item>
      </Form>
      <div class="toCenter">
        <Button type="primary" @click="handleLogin('formValidate')">点击登录</Button>
        <span class="tips">用户名/密码：admin</span>
      </div>
    </div>
    <p>2017 &copy; 金钱豹薪资管理系统</p>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        formValidate: {
          name: '',
          password: '',
        },
        ruleValidate: {
          name: [{
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
          }],
          password: [{
            required: true,
            message: '密码不能为空',
            trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      handleLogin(name) {
        this.$refs[name].validate((valid) => {
          let names = this.formValidate.name;
          let password = this.formValidate.password;
          if (valid) {
            if (names == "admin" && password == "admin") {
              this.$Message.success('提交成功!');
              this.$router.push({
                name: 'main',
                // params:{userName:names}
              });
              this.$local.save('userName', names);
            } else {
              this.$Message.error('用户名或密码错误!');
            }
          } else {
            this.$Message.error('表单验证失败!');
          }
        })

        // const validatePass = (rule, value, callback) => {
        //   if (value === "") {
        //     callback(new Error('请输入密码'))
        //   } else {
        //     if (this.formCustom.passwdCheck !== '') {
        //       //对第二个密码框单独验证;
        //       this.$refs.formCustom.validateFiele('sss')
        //     }
        //     callback();
        //   }
        // }


      }
    }
  }

</script>
<style scoped>
  .tips {
    width: 100%;
    color: #ccc;
    display: block;
    text-align: center;
  }

  html,
  body {
    width: 100%;
    height: 100%;
    overflow: hidden;
    position: relative;
  }

  .loginForm {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -70%);
  }

  .login .ivu-card {
    width: 80%;
    margin: 0 auto;
    height: 100%;
    background: none;
  }

  .login .logo {
    margin: 0 auto;
  }

  .login .logo img {
    width: 100%;
    height: auto;
    border-radius: 50%;
    /* border: 1px solid #3c8dbc; */
  }

  .loginForm {
    width: 30%;
    margin: 0 auto;
  }

  .login .ivu-btn {
    width: 20%;
    margin: 30px auto;
    display: block;
  }

  .login .ivu-btn a {
    color: #fff;
  }

  .login p {
    width: 100%;
    text-align: center;
    color: #999;
    position: fixed;
    left: 0;
    bottom: 0;
    background: #eee;
    padding: 20px 0;
  }

</style>
