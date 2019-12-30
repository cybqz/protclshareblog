//这是工程的入口文件
import Vue from 'vue';
import Vuex from 'vuex';
import App from '@/app';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource';
import router from './router';
import store from './store';
import axios from 'axios';
import qs from 'qs'

import VueAnime from 'vue-animejs';

import ViewUI from 'iview';
import 'iview/dist/styles/iview.css';


Vue.prototype.$axios = axios;
Vue.prototype.$qs = qs;

Vue.config.productionTip = false;
Vue.use(VueResource);
Vue.use(VueRouter);
Vue.use(VueAnime);
Vue.use(ViewUI, {
  transfer: true,
  size: 'large',
  capture: false,
  select: {
      arrow: 'md-arrow-dropdown',
      arrowSize: 20
  }
});
Vue.use(Vuex);

// axios 配置
axios.defaults.timeout = 5000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
//axios.defaults.baseURL = 'https://127.0.0.1:9090/'
axios.defaults.baseURL = 'https://114.115.134.1:9090/'
 
const root = document.createElement('div')
root.id = 'app'
document.body.appendChild(root)
 
//mount就是讲我们的App挂载到root这样一个根节点中去
new Vue({
  el: '#app',
  store, //将store注入到所有子组件
  router, //引用router
  template: '<App/>',
  components: { App },
  render: (h) => h(App)
}).$mount(root)