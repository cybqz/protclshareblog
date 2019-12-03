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

        <!-- 添加blog -->
        <Drawer
            :title="blogDrawer.title"
            v-model="blogDrawer.isShow"
            width="720"
            :mask-closable="true"
            styles="height: 'calc(100% - 55px)',
                    overflow: 'auto',
                    paddingBottom: '53px',
                    position: 'static'"
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
                <FormItem label="前言" label-position="top">
                    <Input type="textarea" v-model="blog.preface" :rows="4" placeholder="请输入前言" />
                </FormItem>
                <FormItem id="upload" label="图片" label-position="top">
                    <Upload
                        ref="imageUpload"
                        :show-upload-list="true"
                        :on-success="handleImageUploadSuccess"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        type="drag"
                        action="//jsonplaceholder.typicode.com/posts/"
                        style="display: inline-block;width:58px;">
                        <div style="width: 58px;height:58px;line-height: 58px;">
                            <Icon type="ios-camera" size="20"></Icon>
                        </div>
                    </Upload>
                </FormItem>
            </Form>
            <div class="demo-drawer-footer">
                <Button style="margin-right: 8px" @click="blogDrawer.isShow = false">取消</Button>
                <Button type="primary" @click="blogDrawer.isShow = false">添加</Button>
                <Button id="btn-chapter-drawer" @click="showChapterDrawer = true" type="primary">添加章节</Button>
            </div>
        </Drawer>

        <!-- 添加章节 -->
        <Drawer title="添加章节" 
                :closable="false"
                v-model="showChapterDrawer"
                width="600"
                :mask-closable="true"
                styles="height: 'calc(100% - 55px)',
                    overflow: 'auto',
                    paddingBottom: '53px',
                    position: 'static'">

            <Form :model="blogChapter">
                <Row :gutter="32">
                    <Col span="32">
                        <FormItem label="标题" label-position="top">
                            <Input v-model="blogChapter.title" placeholder="请输入标题" />
                        </FormItem>
                    </Col>
                </Row>
                <FormItem label="描述" label-position="top">
                    <Input type="textarea" v-model="blogChapter.content" :rows="4" placeholder="请输入描述" />
                </FormItem>
                <FormItem label="代码" label-position="top">
                    <Input type="textarea" v-model="blogChapter.code" :rows="4" placeholder="请输入代码" />
                </FormItem>
                <!-- <FormItem id="upload" label="图片" label-position="top">
                    <Upload
                        ref="imageUpload"
                        :show-upload-list="true"
                        :on-success="handleImageUploadSuccess"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        type="drag"
                        action="//jsonplaceholder.typicode.com/posts/"
                        style="display: inline-block;width:58px;">
                        <div style="width: 58px;height:58px;line-height: 58px;">
                            <Icon type="ios-camera" size="20"></Icon>
                        </div>
                    </Upload>
                </FormItem> -->
            </Form>
            <div class="demo-drawer-footer">
                <Button style="margin-right: 8px" @click="showChapterDrawer = false">取消</Button>
                <Button type="primary" @click="showChapterDrawer = false">添加</Button>
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
                showChapterDrawer: false,
                query:{a: '', b:''},
                blog: {
                    title: '',
                    author: '',
                    category: '',
                    tag: '',
                    preface: '',
                    chapter: []
                },
                blogChapter: {
                    title: '',
                    content: '',
                    code: ''
                },
                blogDrawer: {
                    isShow: false,
                    title: '',
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
            this.blogDrawer.isShow = true;
            let model = this.$store.state.model;
            if(model === 'teclearning'){
                debugger
                this.blogDrawer.teclearning = true;
                this.blogDrawer.title = '添加技术学习';
            }
        },

        //图片上传成功处理
        handleImageUploadSuccess: function(){

        }
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

#upload
    width 70%
    margin-bottom 20px

#btn-chapter-drawer
    margin-left 300px
</style>