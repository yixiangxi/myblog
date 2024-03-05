<template>
  <div class="container">
    <div style="width: 400px; padding: 30px; background-color: white; border-radius: 5px;">
      <div style="text-align: center; font-size: 30px; margin-bottom: 20px; color: #333">yixiangxi的小家</div>
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password v-model="form.password"></el-input>
        </el-form-item>

        <el-form-item prop="role">
          <el-radio-group v-model="form.role">
            <el-radio label="ADMIN">管理员</el-radio>
            <el-radio label="USER">用户</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item prop="code">
          <div style="display: flex">
            <el-input style="flex: 1"></el-input>
            <Identify :identifyCode="identifyCode" @click.native="refreshCode"/>

          </div>

        </el-form-item>

        <el-form-item>
          <el-button style="width: 100%; background-color: #333; border-color: #333; color: white" @click="login">登 录
          </el-button>
        </el-form-item>
        <!--        <div style="display: flex; align-items: center">-->
        <!--          <div style="flex: 1"></div>-->
        <!--          <div style="flex: 1; text-align: right">-->
        <!--            还没有账号？请 <a href="/register">注册</a>-->
        <!--          </div>-->
        <!--        </div>-->
      </el-form>
    </div>
  </div>
</template>

<script>
import Identify from "@/components/Identify";

export default {
  name: "Login",
  components: {Identify},
  comments: {
    Identify
  },
  data() {
    return {
      form: {role: 'ADMIN'},
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ]
      },
      code: '',   // 表单绑定的验证码
      // 图片验证码
      identifyCode: '',
      // 验证码规则
      identifyCodes: '123456789ABCDEFGHGKMNPQRSTUVWXY',
    }

  },
  created() {

  },
  mounted() {
    this.refreshCode()
  },

  methods: {


    // 切换验证码
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },

    // 生成随机验证码
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[Math.floor(Math.random() * (this.identifyCodes.length))]
      }
    },

    login() {
      if(!this.code){
        this.$message.warning("请输入验证码");
        this.refreshCode();
        return
      }
      if(this.code.toLocaleLowerCase()!==this.identifyCode.toLocaleLowerCase()){
        this.$message.warning("请输入正确的验证码");
        this.refreshCode();
        return;
      }

      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过
          this.$request.post('/login', this.form).then(res => {
            if (res.code === '200') {
              localStorage.setItem("xm-user", JSON.stringify(res.data))  // 存储用户数据
              this.$router.push('/')  // 跳转主页
              this.$message.success('登录成功')
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.container {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/imgs/bg.jpg");
  background-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}

a {
  color: #2a60c9;
}
</style>