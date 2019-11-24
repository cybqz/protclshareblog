import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/views/Home'
import Content from '@/views/Content'
import A from '@/views/content/A'
import B from '@/views/content/B'
import C from '@/views/content/C'
import D from '@/views/content/D'

Vue.use(Router)


const router = new Router({
    mode: "history",
    routes: [
     {
          path : '/',  //到时候地址栏会显示的路径
          name : '',
          component :  Home   // Home是组件的名字，这个路由对应跳转到的组件。。注意component没有加“s”.
      },
      {
        path : '/home',  //到时候地址栏会显示的路径
        name : 'Home',
        component :  Home   // Home是组件的名字，这个路由对应跳转到的组件。。注意component没有加“s”.
      },
      {
          path : '/content',
          name : 'Content',
          component :  Content,
          beforeRouteEnter (to, from, next) {
            console.log(from.fullPath);
            console.log(to.fullPath);
            next();
          },
          beforeRouteUpdate (to, from, next) {
            console.log(from.fullPath);
            console.log(to.fullPath);
            next();
          },
          beforeRouteLeave (to, from, next) {
            console.log(from.fullPath);
            console.log(to.fullPath);
            next();
          },
          children : [
              {
                path : 'a',
                name : 'A',
                component :  A
              },
              {
                path : 'b',
                name : 'B',
                component :  B
              },
              {
                path : 'c',
                name : 'C',
                component :  C
              },
              {
                path : 'd',
                name : 'D',
                component :  D
              }
          ] 
      }
  ]
})

//路由全局前置守卫
router.beforeEach((to, from, next) => {
  console.log(from.fullPath);
  console.log(to.fullPath);
  next();
})

export default router;