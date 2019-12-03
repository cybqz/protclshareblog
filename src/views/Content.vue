<template>
    <div id="content">
       <div id="btn-list">
            <div id="btn-list-left">
                <Input v-model="query.a" search placeholder="Enter something..." />
            </div>
            <div id="btn-list-right">
                <Select v-model="query.b" multiple style="width:260px">
                    <Option v-for="item in selectList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
                <Button icon="md-add" type="primary" shape="circle" @click="changeShowDrawer"></Button>
            </div>
        </div>
        <Drawer
            :title="drawer.title"
            v-model="drawer.isShow"
            width="720"
            :mask-closable="true"
            :styles="drawer.styles"
        >
            <Form :model="blog">
                <Row :gutter="32">
                    <Col span="32">
                        <FormItem label="标题" label-position="top">
                            <Input v-model="blog.title" placeholder="请输入标题" />
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col span="12">
                        <FormItem label="分类" label-position="top">
                            <Select v-model="blog.category" placeholder="请选择分类">
                                <Option value="jobs">Steven Paul Jobs</Option>
                                <Option value="ive">Sir Jonathan Paul Ive</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="标签" label-position="top">
                            <Input v-model="blog.tag" placeholder="请输入标签" />
                        </FormItem>
                    </Col>
                </Row>
                <FormItem label="描述" label-position="top">
                    <Input type="textarea" v-model="blog.desc" :rows="4" placeholder="请输入描述" />
                </FormItem>
            </Form>
            <div class="demo-drawer-footer">
                <Button style="margin-right: 8px" @click="drawer.isShow = false">取消</Button>
                <Button type="primary" @click="drawer.isShow = false">添加</Button>
            </div>
        </Drawer>    
        <router-view></router-view>   
    </div>
</template>
<script>
import {mapMutations, mapGetters, mapState} from 'vuex'  // 引入map方法
export default {
    data(){
        return {
                query:{a: '', b:''},
                blog: {
                    title: '',
                    author: '',
                    category: '',
                    tag: '',
                    desc: ''
                },
                drawer: {
                    isShow: false,
                    title: '',
                    styles: {
                        height: 'calc(100% - 55px)',
                        overflow: 'auto',
                        paddingBottom: '53px',
                        position: 'static'
                    },
                    teclearning: false
                },
                selectList: [
                    {
                        value: 'New York',
                        label: 'New York'
                    },
                    {
                        value: 'London',
                        label: 'London'
                    },
                    {
                        value: 'Sydney',
                        label: 'Sydney'
                    }
                ]
            }
    },
    methods:{
        test: function(){
            // this.$axios.get('yjdwyjqxjl/qxdjpage',{
            //     params: {                           //参数
            //         current: 1,
            //         size: 10,
            //     },
            //     }).then(res => {                   //请求成功后的处理函数     
            //         console.log(res);   
            //     }).catch(err => {                 //请求失败后的处理函数   
            //         console.log(err);
            //     })
        },
        //显示Drawer
        changeShowDrawer: function(){
            this.drawer.isShow = true;
            let model = this.$store.state.model;
            if(model === 'teclearning'){
                this.drawer.teclearning = true;
                this.drawer.title = '添加技术学习';
            }
        },
    },
    created(){
        this.test();
    },
    computed: {},
    watch: {}
}
</script>
<style lang="stylus" scoped>
#btn-list
    width 80%
    margin 30px auto
    display flex

#btn-list-left
    flex 1
    margin-right 35%

#btn-list-right
    flex 1

#btn-list-right div
    margin-right 14%
</style>