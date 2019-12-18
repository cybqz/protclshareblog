<template>
    <div id="app">
      <Layout >
        <Header class="cyb-header">
          <app-header></app-header>
        </Header>
      </Layout>
      <Layout>
        <app-nav></app-nav>
      </Layout>
      <Layout>
        <Content>
          <router-view></router-view>
        </Content>
      </Layout>
      <Layout>
        <Footer>
          <app-footer></app-footer>
        </Footer>
      </Layout>
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
     components: {//局部注册组件这里，可能会定义多个组件
       "app-header": Header,
       "app-footer": Footer,
       'app-nav': Navbar
     },
     mounted(){

       //hash模式下监听地址栏路由变化
       window.addEventListener('hashchange',()=>{
            var currentPath = window.location.hash.slice(1); // 获取输入的路由
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
    background-color #f8f8f9

  #app
    padding 0
    margin  20px auto
    font-size 25px
    width 90%

  .ivu-layout,.ivu-layout-footer
    background-color #ffffff

  .cyb-header
    height 200px
    padding 0 0
    line-height 0

  #navbar
    margin 6px 0px 6px 0px

  .ivu-layout-content
    border none

  .ivu-layout-footer
    height 200px

  .layout
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
 </style>