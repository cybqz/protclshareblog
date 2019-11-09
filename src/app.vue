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
   import Header from '@/components/Header'
   import Footer from '@/components/Footer'
   import Navbar from '@/components/Navbar'
   export default {
     name: 'app',
     data () {
       return {}
     },
     components: {//局部注册组件这里，可能会定义多个组件，所以component这个单词加上“s”
       "app-header": Header,
       "app-footer": Footer,
       'app-nav': Navbar
     }
   }
 </script>
 
<style scoped lang="stylus">
  #app
    padding 0
</style>