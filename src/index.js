//这是工程的入口文件
import Vue from 'vue';
import App from '@/app';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource'
import router from './router'

import iView from 'iview';
import 'iview/dist/styles/iview.css';


Vue.config.productionTip = false;
Vue.use(VueResource)
Vue.use(VueRouter);
Vue.use(iView);
 
const root = document.createElement('div')
root.id = 'app'
document.body.appendChild(root)
 
//mount就是讲我们的App挂载到root这样一个根节点中去
new Vue({
  el: '#app',
  router,//引用router
  template: '<App/>',
  components: { App },
  render: (h) => h(App)
}).$mount(root)