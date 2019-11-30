<template>
    <div id="navbar">
       <Menu mode="horizontal" :theme="theme" active-name="1" @on-select="switchMenu">
            <MenuItem name="1">
                <Icon type="ios-paper" />
                <router-link to="/content/teclearning">技术学习</router-link>
            </MenuItem>
            <MenuItem name="2">
                <Icon type="ios-people" />
                <router-link to="/content/b">埋坑笔记</router-link>
            </MenuItem>
            <MenuItem name="3">
                <Icon type="ios-paper" />
                <router-link to="/content/c">项目介绍</router-link>
            </MenuItem>
            <MenuItem name="4">
                <Icon type="ios-construct" />
                <router-link to="/content/d">我的模板</router-link>
            </MenuItem>
        </Menu>
        <!-- <br>
        <p>Change theme</p>
        <RadioGroup v-model="theme1">
            <Radio label="light"></Radio>
            <Radio label="dark"></Radio>
            <Radio label="primary"></Radio>
        </RadioGroup> -->
    </div>
</template>
<style scoped lang="stylus">

    #navbar > ul > button
        margin-left 60%

</style>
<script>
    import {mapMutations, mapGetters, mapState} from 'vuex'  // 引入map方法
    export default {
        data () {
            return {
                theme: 'light'
            }
        },
        methods: {
            //切换导航菜单
            switchMenu: function (name){
                console.log(name);
            }
        }
    }
</script>