<template>
  <el-container class="dn-container is-vertical">
    <!--头部-->
    <header-top></header-top>
    <!--中间页面-->
      <!--左侧导航-->
      <el-container class="main">
        <el-aside width="">
          <div class="dn-aside">
            <left-nav></left-nav>
          </div>
        </el-aside>
        <el-container>
          <el-main>
            <el-col :span="24">
              <breadcrum></breadcrum>
              <router-view></router-view>
            </el-col>
          </el-main>
        </el-container>
      </el-container>
  </el-container>
</template>

<script>
  import LeftNav from '@/components/leftNav'
  import HeaderTop from '@/components/Header'
  import Breadcrum from '@/components/breadcrum'
  export default {
    data () {
      return {

      }
    },
    components: {
      LeftNav,
      HeaderTop,
      Breadcrum
    }
  }
</script>
<style lang="less">
  @import "./home.less";
</style>
