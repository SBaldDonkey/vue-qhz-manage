// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
import axios from 'axios'
import store from '@/util/store'

import FastClick from 'fastclick'
import filters from '@/util/filters'
import {post, fetch, patch, put} from '@/util/http'
import {getCookie, setCookie, delCookie} from '@/util/util'

// 解决click点击300毫秒延时问题
FastClick.attach(document.body)

Vue.config.productionTip = false
Vue.use(ElementUI)

// 全局注册过滤器
filters(Vue)

Vue.prototype.$post = post
Vue.prototype.$fetch = fetch
Vue.prototype.$patch = patch
Vue.prototype.$put = put
Vue.prototype.$http = axios

// cookie方法
Vue.prototype.getCookie = getCookie
Vue.prototype.setCookie = setCookie
Vue.prototype.delCookie = delCookie

/* eslint-disable no-new */
new Vue({
  el: '#app',
  axios,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
