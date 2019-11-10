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
    background-color red

  #header
    height 30%
    background-color yellow 

  #navbar
    height 10%
    background-color blue

  #home
    height 50%
    background-color green

  #footer
    height 10%
    background-color gray
</style>