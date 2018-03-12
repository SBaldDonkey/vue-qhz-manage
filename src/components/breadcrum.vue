<template>
  <div class="warp-breadcrum warp-top">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item v-for="bread in this.$route.matched" :key="bread.index">{{bread.name}}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
    export default {}
</script>
<style>
</style>
