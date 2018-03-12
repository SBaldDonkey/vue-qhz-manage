<template>
  <div>
    <!--span-toggle-->
    <div class="menufold">
      <div class="span-unfurled" :class="{'span-toggle':collapsed}" @click="toggleClass">
        <span class="line line1"></span>
        <span class="line line2"></span>
        <span class="line line3"></span>
        <span class="line line4"></span>
        <span class="line line5"></span>
      </div>
    </div>

    <el-menu v-show="!collapsed" default-active="0" @open="handleOpen" @close="handleClose" router>
      <template v-for="(item,index) in $router.options.routes" v-if="item.menuShow">
        <el-submenu v-if="!item.leaf" :index="index+''">
          <template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>
          <el-menu-item v-for="term in item.children" :key="term.path" :index="term.path" v-if="term.menuShow">
            {{term.name}}
          </el-menu-item>
        </el-submenu>
        <el-menu-item v-else-if="item.leaf&&item.children&&item.children.length" :index="item.children[0].path" class="el-submenu__title">
          <i :class="item.iconCls"></i>{{item.children[0].name}}
        </el-menu-item>
      </template>
    </el-menu>

    <ul v-show="collapsed" class="el-menu collapsed" ref="menuCollapsed" >
      <template v-for="(item,index) in $router.options.routes" v-if="item.menuShow">
        <li v-if="!item.leaf" :index="index+''" style="position: relative;">
          <div class="el-submenu__title" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)"><i :class="item.iconCls"></i></div>
          <ul class="el-menu submenu" :class="'submenu-hook-'+index" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)">
            <li v-for="term in item.children" :key="term.path" v-if="term.menuShow" class="el-menu-item" :class="$route.path==term.path?'is-active':''"
                @click="$router.push(term.path)">{{term.name}}</li>
          </ul>
        </li>
        <li v-else-if="item.leaf&&item.children&&item.children.length" class="el-menu-item el-submenu__title" :class="$route.path==item.children[0].path?'is-active':''" @click="$router.push(item.children[0].path)">
          <i :class="item.iconCls"></i>
        </li>
      </template>
    </ul>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        submenuIndex: '1-1',
        itemIndex: '',
        collapsed: false
      }
    },
    methods: {
      handleOpen () {
      },
      handleClose () {
      },
      toggleClass () {
        this.collapsed = !this.collapsed
      },
      showMenu (i, status) {
        this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none'
      }
    },
    mounted () {
      console.log(this.$router.options.routes)
//      let index = this.$router.options.routes.children.length
//      this.itemIndex = index
    }
  }
</script>
<style>
</style>
