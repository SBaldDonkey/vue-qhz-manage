<template>
    <el-header class="dn-head-nav">
      <router-link to="/manage" tag="div" class="topbar">
        <span class="topbar-logo">XXXXXXX</span>
        <span class="topbar-title">后台管理系统</span>
      </router-link>
      <el-dropdown trigger="click" class="head-dropdown">
        <span class="el-dropdown-link">
          <i class="iconfont icon-headUser"></i>
          {{sysUserName}}
        </span>
        <el-dropdown-menu slot="dropdown" class="head-dowm">
          <el-dropdown-item><i class="iconfont icon-setting"></i>个人信息</el-dropdown-item>
          <el-dropdown-item><i class="iconfont icon-switch"></i>修改密码</el-dropdown-item>
          <el-dropdown-item><i class="iconfont icon-out"></i>退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>
</template>
<script>
  export default {
    data () {
      return {
        sysUserName: ''
      }
    },
    mounted () {
      var userSession = this.getCookie('session')
      if (userSession) {
        this.sysUserName = userSession
      }
    },
    methods: {
      logout () {
        this.$confirm('确认退出吗？', '提示', {}).then(() => {
          this.$fetch('/system/loginOut')
            .then(res => {
              if (res.status === 200) {
                this.delCookie('session')
                this.$router.push({path: '/login', query: {redirect: this.$router.currentRoute.fullPath}})
              } else {
                this.$message({
                  message: res.msg,
                  type: 'warning'
                })
              }
            })
        }).catch(() => {
        })
      }
    }
  }
</script>



