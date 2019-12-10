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
                <Button icon="md-add" type="primary" shape="circle" @click="changeShowBlogDrawer"></Button>
            </div>
        </div>

        <!-- 添加blog -->
        <Drawer
            :title="blogDrawer.title"
            v-model="blogDrawer.isShow"
            width="720"
            :mask-closable="true"
            :styles="blogDrawer.style"
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
                <FormItem id="chapter" label-position="top">
                    <Collapse simple v-for="(item, index) in blog.chapterList" :key="index" >
                        <Panel name="index">
                            {{item.title}}
                            <div slot="content">
                                <div>
                                    {{item.description}}
                                </div>
                                <div>
                                    {{item.content}}
                                </div>
                            </div>
                        </Panel>
                    </Collapse>
                </FormItem>
            </Form>
            <div class="demo-drawer-footer">
                <Button style="margin-right: 8px" @click="blogDrawer.isShow = false">取消</Button>
                <Button type="primary" @click="addBlog">添加</Button>
                <Button id="btn-chapter-drawer" @click="changeShowChapterDrawer" type="primary">添加章节</Button>
            </div>
        </Drawer>

        <!-- 添加章节 -->
        <Drawer :title="chapterDrawer.title" 
                :closable="false"
                v-model="chapterDrawer.isShow"
                width="600"
                :mask-closable="true"
                :styles="chapterDrawer.style">

            <Form :model="blogChapter">
                <Row :gutter="32">
                    <Col span="32">
                        <FormItem label="标题" label-position="top">
                            <Input v-model="blogChapter.title" placeholder="请输入标题" />
                        </FormItem>
                    </Col>
                </Row>
                <FormItem label="描述" label-position="top">
                    <Input type="textarea" v-model="blogChapter.description" :rows="4" placeholder="请输入描述" />
                </FormItem>
                
                <div id="editor">
                    <div ref="toolbar" class="toolbar"></div>
                    <div ref="textarea" class="textarea"></div>
                </div>
            </Form>
            <div class="demo-drawer-footer">
                <Button style="margin-right: 8px" @click="chapterDrawer.isShow = false">取消</Button>
                <Button type="primary" @click="addChapter">添加</Button>
                <Button type="primary" @click="addChapterContinue">继续添加</Button>
            </div>
        </Drawer>  
        <router-view></router-view>   
    </div>
</template>
<script>
import {mapMutations, mapGetters, mapState} from 'vuex'
import {isEmptyMap} from '@/Utils/Utils'
import WangEditor from 'wangeditor'
export default {
    data(){
        return {
                AddChapterResult: false,
                isInitWangEditor: true,
                editor: null,
                query:{a: '', b:''},
                richContent: null,
                blog: {
                    title: 'title',
                    author: 'author',
                    category: 'category',
                    tag: 'tag',
                    preface: 'preface',
                    chapterList: []
                },
                blogChapter: {
                    title: '',
                    description: '',
                    img: ''
                },
                blogDrawer: {
                    isShow: false,
                    title: '',
                    teclearning: false,
                    style:{
                        height: 'calc(100% - 55px)',
                        overflow: 'auto',
                        paddingBottom: '53px',
                        position: 'static'
                    }
                },
                chapterDrawer: {
                    title: '添加章节',
                    isShow: false,
                    style:{
                        height: 'calc(100% - 55px)',
                        overflow: 'auto',
                        paddingBottom: '53px',
                        position: 'static'
                    }
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
        //显示BlogDrawer
        changeShowBlogDrawer: function(){
            this.blogDrawer.isShow = true;
            let model = this.$store.state.model;
            if(model === 'teclearning'){
                this.blogDrawer.teclearning = true;
                this.blogDrawer.title = '添加技术学习';
            }
        },

        //显示C
        changeShowChapterDrawer: function(){
            this.chapterDrawer.isShow = true;
            if(this.isInitWangEditor){
                this.$nextTick(function(){
                    this.initWangEditor();
                    this.isInitWangEditor = false;
                });
            }
        },

        //图片上传成功处理
        handleImageUploadSuccess: function(){

        },

        //添加blog
        addBlog: function(){
            this.$axios.post('tecLearning/add',
                    this.$qs.stringify(this.blog, {arrayFormat: 'indices', allowDots: true})
                ).then(res => {                   //请求成功后的处理函数     
                    console.log(res.data);
                    this.message(res.data);
                }).catch(err => {                 //请求失败后的处理函数   
                    console.log(err);
                })
            //this.blogDrawer.isShow = false;
        },

        //添加章节
        addChapter: function(){
            this.excuteAddChapter();
            this.chapterDrawer.isShow = this.AddChapterResult;
        },
        //继续添加章节
        addChapterContinue: function(){
            this.excuteAddChapter();
        },

        //执行添加章节操作
        excuteAddChapter(){
            let chaptermap = {
                title: this.blogChapter.title,
                description: this.blogChapter.description,
                content: this.editor.txt.html()
            }

            //非空判断
            if(!isEmptyMap(chaptermap,['title','content'])){

                //标题重复检测
                for(let i in this.blog.chapterList){

                    if(this.blog.chapterList[i].title === chaptermap['title']){
                        this.$Message.warning('章节标题不能重复');
                        this.AddChapterResult = false;
                    }
                }
                this.blog.chapterList.push(chaptermap)
                //this.blogChapter = {}
                this.editor.txt.html('')
                this.AddChapterResult = true;
            }
            this.AddChapterResult = false;
        },

        //初始化WangEditor
        initWangEditor: function(){
            this.editor = new WangEditor(this.$refs.toolbar, this.$refs.textarea)
            this.editor.customConfig.uploadImgShowBase64 = false // base 64 存储图片
            this.editor.customConfig.uploadImgServer = 'http://otp.cdinfotech.top/file/upload_images'// 配置服务器端地址
            this.editor.customConfig.uploadImgHeaders = { }// 自定义 header
            this.editor.customConfig.uploadFileName = 'file' // 后端接受上传文件的参数名
            this.editor.customConfig.uploadImgMaxSize = 2 * 1024 * 1024 // 将图片大小限制为 2M
            this.editor.customConfig.uploadImgMaxLength = 6 // 限制一次最多上传 3 张图片
            this.editor.customConfig.uploadImgTimeout = 3 * 60 * 1000 // 设置超时时间

            // 配置菜单
            this.editor.customConfig.menus = [
            'head', // 标题
            'bold', // 粗体
            'fontSize', // 字号
            'fontName', // 字体
            'italic', // 斜体
            'underline', // 下划线
            'strikeThrough', // 删除线
            'foreColor', // 文字颜色
            'link', // 插入链接
            'list', // 列表
            'justify', // 对齐方式
            'quote', // 引用
            'image', // 插入图片
            'table', // 表格
            'code', // 插入代码
            'undo', // 撤销
            'redo', // 重复
            'fullscreen' // 全屏
            ]

            this.editor.customConfig.uploadImgHooks = {
                fail: (xhr, editor, result) => {
                    // 插入图片失败回调
                },
                success: (xhr, editor, result) => {
                    // 图片上传成功回调
                },
                timeout: (xhr, editor) => {
                    // 网络超时的回调
                },
                error: (xhr, editor) => {
                    // 图片上传错误的回调
                },
                customInsert: (insertImg, result, editor) => {
                    // 图片上传成功，插入图片的回调
                    //result为上传图片成功的时候返回的数据，这里我打印了一下发现后台返回的是data：[{url:"路径的形式"},...]
                    // console.log(result.data[0].url)
                    //insertImg()为插入图片的函数
                    //循环插入图片
                    // for (let i = 0; i < 1; i++) {
                    // console.log(result)
                    let url = "http://otp.cdinfotech.top"+result.url
                    insertImg(url)
                }
            }
            // 创建富文本编辑器
            this.editor.create()
        },
        message: function(data){
            if(data.show){
                if(data.validate){
                    this.$Message.success(data.msg);
                }else{
                    this.$Message.error(data.msg);
                }
            }
        },
    },
    created(){
    },
    mounted(){
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

#editor
    background-color #f8f8f8
    width auto
    height 500px
    margin-bottom: 20px
    position: relative

.toolbar
    background-color #000000
    height 25px

.textarea
    height: 475px;

</style>