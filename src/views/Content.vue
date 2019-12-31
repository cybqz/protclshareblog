<template>
    <div id="content">
       <div id="btn-list">
            <div id="btn-list-left">
                <Input v-model="query.a" search placeholder="Enter something..." />
            </div>
            <div id="btn-list-right">
                <Select v-model="query.b" multiple style="width:260px">
                    <Option v-for="item in selectList" :value="item.value" :key="item.value">
                        
                        <span>{{ item.label }}</span>
                        <span style="float:right;color:#ccc">{{ item.category }}</span>
                    </Option>
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
                            <Select v-model="blog.category">
                                <Option v-for="item in selectList" :value="item.value" :key="item.value">
                                    <span>{{ item.label }}</span>
                                    <span style="float:right;color:#ccc">{{ item.category }}</span>
                                </Option>
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
                        :on-success="blogImageUploadSuccess"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        type="drag"
                        :action="tecLearningImageAction"
                        style="display: inline-block;width:58px;">
                        <div style="width: 58px;height:58px;line-height: 58px;">
                            <Icon type="ios-camera" size="20"></Icon>
                        </div>
                    </Upload>
                </FormItem>
                <FormItem id="chapter" label-position="top">
                    <Collapse simple v-for="(item, index) in blog.chapterList" :key="index">
                        <Panel name="index">
                            {{item.title}}
                            <div slot="content">
                                <div>
                                    {{item.description}}
                                </div>
                                <div v-html="item.content"></div>
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
import {SET_REFRESH_MODEL_LIST_TECLEARNING} from '@/store/mutation-type'
import {mapMutations, mapGetters, mapState} from 'vuex'
import {isEmptyMap} from '@/Utils/Utils'
import WangEditor from 'wangeditor'
export default {
    data(){
        return {
                chapterImageAction: '',
                tecLearningImageAction: '',
                AddChapterResult: false,
                isInitWangEditor: true,
                editor: null,
                query:{a: '', b:''},
                richContent: null,
                blog: {
                    title: '',
                    author: 'author',
                    category: '',
                    tag: '',
                    preface: '',
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
                        value: 'VUE',
                        label: 'VUE',
                        category: '前端'
                    },
                    {
                        value: 'iview',
                        label: 'iview',
                        category: '前端'
                    },
                    {
                        value: 'Recat',
                        label: 'Recat',
                        category: '前端'
                    },
                    {
                        value: 'Java',
                        label: 'Java',
                        category: '后端'
                    },
                    {
                        value: 'SpringBoot',
                        label: 'SpringBoot',
                        category: '后端'
                    },
                    {
                        value: 'Mybatis',
                        label: 'Mybatis',
                        category: '后端'
                    }
                ]
            }
    },
    methods:{
        test: function(){
            
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

        //技术学习图片上传成功处理
        blogImageUploadSuccess: function(res){
            this.blog.img = res.data;
        },

        //添加blog
        addBlog: function(){
            this.$axios.post('tecLearning/add',
                    this.$qs.stringify(this.blog, {arrayFormat: 'indices', allowDots: true})
                ).then(res => {                   //请求成功后的处理函数     
                    this.message(res.data);

                    //刷新列表
                    if(res.data.validate){
                        let refreshModelListTecLearning = this.$store.getters.refreshModelListTecLearning;
                        this.$store.commit(SET_REFRESH_MODEL_LIST_TECLEARNING,!refreshModelListTecLearning);
                    }
                }).catch(err => {                 //请求失败后的处理函数   
                    console.log(err);
                })
            this.blogDrawer.isShow = false;
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
                let check = true;
                //标题重复检测
                for(let i in this.blog.chapterList){

                    if(this.blog.chapterList[i].title === chaptermap['title']){
                        this.$Message.warning('章节标题不能重复');
                        this.AddChapterResult = false;
                        check = false;
                    }
                }
                if(check){
                    this.blog.chapterList.push(chaptermap)
                    this.blogChapter = {};
                    this.editor.txt.html('');
                    this.AddChapterResult = true;
                }
            }
            this.AddChapterResult = false;
        },

        //初始化WangEditor
        initWangEditor: function(){
            this.editor = new WangEditor(this.$refs.toolbar, this.$refs.textarea)
            this.editor.customConfig.uploadImgShowBase64 = false // base 64 存储图片
            this.editor.customConfig.uploadImgServer = this.chapterImageAction// 配置服务器端地址
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
                },
                success: (xhr, editor, result) => {
                },
                timeout: (xhr, editor) => {
                },
                error: (xhr, editor) => {
                },
                customInsert: (insertImg, result, editor) => {
                    let url = this.$axios.defaults.baseURL+result.data;
                    insertImg(url)
                }
            }
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
        this.chapterImageAction = this.$axios.defaults.baseURL + 'upload/chapter';
        this.tecLearningImageAction = this.$axios.defaults.baseURL + 'upload/teclearning';
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