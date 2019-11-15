<template>
    <div id="app">
      <app-header></app-header>
      <app-nav></app-nav>
      <!-- 展示router -->
      <router-view></router-view>
      <app-footer></app-footer>
    </div>
 </template>
 
 <script>
   import Header from '@/views/Header'
   import Footer from '@/views/Footer'
   import Navbar from '@/views/Navbar'
   export default {
     name: 'app',
     data () {
       return {}
     },
     components: {//局部注册组件这里，可能会定义多个组件，所以component这个单词加上“s”
       "app-header": Header,
       "app-footer": Footer,
       'app-nav': Navbar
     },
     mounted(){

       //hash模式下监听地址栏路由变化
       window.addEventListener('hashchange',()=>{
            var currentPath = window.location.hash.slice(1); // 获取输入的路由
            console.log('=============================');
            console.log(currentPath);
            if(this.$router.path !== currentPath){
                this.$router.push(currentPath); // 动态跳转
            }
       },false);
    },
    watch:{
      $route(to,from){
        console.log(to.path);
      }
    }
   }
 </script>
 
<style scoped lang="stylus">
  body
    padding 0
    margin  0

  #app
    padding 0
    margin  0
    font-size 25px
    width 1000px
    height 750px
    border solid red 1px

  #header
    height 30%
    border solid yellow 1px

  #navbar
    height 10%
    border solid blue 1px

  #home
    height 50%
    border solid green 1px

  #footer
    height 10%
    border solid gray 1px
</style>