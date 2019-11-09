import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/Home'
import Content from '@/components/Content'

Vue.use(Router)

export default new Router({
    routes: [
     {
          path : '/',  //到时候地址栏会显示的路径
          name : 'Home',
          component :  Home   // Home是组件的名字，这个路由对应跳转到的组件。。注意component没有加“s”.
      },
      {
          path : '/content',
          name : 'Content',
          component :  Content
      }
  ],
      mode: "history"
  })