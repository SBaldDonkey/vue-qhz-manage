require('../assets/css/common.css')
require('../assets/css/iconfont.css')
import Vue from 'vue'

Vue.config.productionTip = false
// 全局注册element-ui
import elementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(elementUi)
// 全局注册过滤器
import filters from './filters'
filters(Vue)

// 获取url参数 使用：this.$getUrlPara.getUrlKey('参数名')
import axios from 'util/util'
Vue.prototype.$getUrlPara = axios

import {post, fetch, patch, put} from './http'
import {getCookie, setCookie, delCookie} from './util'
Vue.prototype.$post = post
Vue.prototype.$fetch = fetch
Vue.prototype.$patch = patch
Vue.prototype.$put = put
Vue.prototype.$http = axios

// cookie方法
Vue.prototype.getCookie = getCookie
Vue.prototype.setCookie = setCookie
Vue.prototype.delCookie = delCookie

// 解决click点击300毫秒延时问题
import FastClick from 'fastclick'
FastClick.attach(document.body)

export default{
  api
}
