<template>
    <div id="navbar">
        <div id="menu">
            <Menu mode="horizontal" :theme="theme" active-name="1" @on-select="switchMenu">
                <MenuItem name="1">
                    <Icon type="ios-paper" />
                    <router-link to="/content/a">内容管理</router-link>
                </MenuItem>
                <MenuItem name="2">
                    <Icon type="ios-people" />
                    <router-link to="/content/b">用户管理</router-link>
                </MenuItem>
                <MenuItem name="3">
                    <Icon type="ios-paper" />
                    <router-link to="/content/c">内容管理</router-link>
                </MenuItem>
                <MenuItem name="4">
                    <Icon type="ios-construct" />
                    <router-link to="/content/d">综合设置</router-link>
                </MenuItem>
            </Menu>
        </div>
        <!-- <br>
        <p>Change theme</p>
        <RadioGroup v-model="theme1">
            <Radio label="light"></Radio>
            <Radio label="dark"></Radio>
            <Radio label="primary"></Radio>
        </RadioGroup> -->
        <div id="avatar">
            <Avatar icon="ios-person" size="large" />
        </div>
    </div>
</template>
<style scoped lang="stylus">

    #menu
        width 600px
        float left
    #avatar
        float right
</style>
<script>
    export default {
        data () {
            return {
                theme: 'light',
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