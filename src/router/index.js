import Vue from 'vue'
import Router from 'vue-router'
import store from '@/util/store'
import {fetch} from '@/util/http'
import {getCookie, delCookie} from '@/util/util'
import * as types from '@/util/types'

Vue.use(Router)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'login',
    component: resolve => require(['@/views/public/login'], resolve)
  },
  {
    path: '/home',
    name: '首 页',
    component: resolve => require(['@/views/index/index'], resolve),
    iconCls: 'iconfont icon-home',
    menuShow: true,
    leaf: true,
    // meta:{requireAuth: true},
    children: [
      {
        path: '/index',
        name: '首 页',
        component: resolve => require(['@/views/index/home'], resolve),
        menuShow: true,
        // meta:{requireAuth: true}
      }
    ]
  },
  {
    path: '/manage',
    name: '商品管理',
    component: resolve => require(['@/views/index/index'], resolve),
    iconCls: 'iconfont icon-commodit',
    menuShow: true,
    // meta:{requireAuth: true},
    children: [
      {
        path: '/manage/brand',
        name: '品牌管理',
        component: resolve => require(['@/views/goodsManage/brand'], resolve),
        menuShow: true,
        // meta:{requireAuth: true}
      },
      {
        path: '/manage/goods',
        name: '商品管理',
        component: resolve => require(['@/views/goodsManage/goods'], resolve),
        menuShow: true,
        // meta:{requireAuth: true}
      }
    ]
  },
  {
    path: '/permissions',
    name: '权限管理',
    component: resolve => require(['@/views/index/index'], resolve),
    iconCls: 'iconfont icon-permissions',
    menuShow: true,
    // meta:{requireAuth: true},
    children: [
      {
        path: '/permissions/department',
        name: '部门管理',
        component: resolve => require(['@/views/permissions/department'], resolve),
        menuShow: true,
        // meta:{requireAuth: true}
      },
      {
        path: '/permissions/role',
        name: '角色管理',
        component: resolve => require(['@/views/permissions/role'], resolve),
        menuShow: true,
        // meta:{requireAuth: true}
      },
      {
        path: '/permissions/position',
        name: '职位管理',
        component: resolve => require(['@/views/permissions/position'], resolve),
        menuShow: true,
        // meta:{requireAuth: true}
      },
      {
        path: '/permissions/employees',
        name: '员工管理',
        component: resolve => require(['@/views/permissions/employees'], resolve),
        menuShow: true,
        // meta:{requireAuth: true}
      },
      {
        path: '/permissions/menu',
        name: '菜单管理',
        component: resolve => require(['@/views/permissions/menu'], resolve),
        menuShow: true,
        // meta:{requireAuth: true}
      }
    ]
  },
  {
    path: '/banner',
    name: 'banner管理',
    component: resolve => require(['@/views/index/index'], resolve),
    iconCls: 'iconfont icon-banner',
    menuShow: true,
    leaf: true,
    // meta:{requireAuth: true},
    children: [
      {
        path: '/banner/bannerManage',
        name: 'banner管理',
        component: resolve => require(['@/views/bannerManage/banner'], resolve),
        menuShow: true,
        // meta:{requireAuth: true}
      }
    ]
  }
]

// 页面刷新时，重新赋值token
if (window.localStorage.getItem('token')) {
  store.commit(types.LOGIN, window.localStorage.getItem('token'))
}

const router = new Router({
  // mode: 'history',
  routes
})

// 这个是请求页面路由的时候会验证token存不存在，不存在的话会到登录页
router.beforeEach((to, from, next) => {
  if (to.matched.some(r => r.meta.requireAuth)) {
    if (store.state.token) {
      next()
    } else {
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    next()
  }
})
export default router
