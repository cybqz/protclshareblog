<template>
    <div id="tec-learning">
        <div class="container" v-for="(item , index) in blogList" :key="index">
            <div class="title">
                {{item.title}}
            </div>
            <div class="article">
                <span class="article-author">
                    <Icon type="md-person" />
                    <span>{{item.author}}</span>
                </span>    
                <span class="article-category">
                    <Icon type="md-folder" />
                    <span>{{item.category}}</span>
                </span>              
                <span class="article-tag">
                    <Icon type="md-pricetag" />
                    <span>{{item.tag}}</span>
                </span>
                <span class="article-date">
                    <Icon type="md-timer" />
                    <span>{{item.date}}</span>
                </span>    
            </div>
            <!-- <img class="titleimg" :src="item.img"/> -->
            <div class="sketch">
                {{item.sketch}}
            </div>
            <Button type="primary" shape="circle" icon="md-eye" @click="readAll(index)">阅读全部</Button>
            <div class="content" v-if="item.status" :ref="'content-'+item"
                 v-anime="{ rotate: '1turn', duration: 2000, loop: false }">
                {{item.content}}
            </div>
        </div>
    </div>
</template>
<script>
export default {
  name: 'TecLearning',
  data () {
    return {
      blogList: []
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

        //阅读全部
        readAll: function(index){
            let status = this.blogList[index].status

            //关闭动画
            if(status){
                let targets = this.$refs['content-' + index];
                this.$anime.timeline().add({
                    targets,
                    translateX: 250,
                    easing: 'easeOutExpo',
                }).add({
                    targets,
                    translateX: 250,
                    easing: 'easeOutExpo',
                }).complete(this.$set(this.blogList[index],'status',false));
            }else{
                this.$set(this.blogList[index],'status',true);
            }
        }
    },
    created(){
        this.test();
    },
    mounted(){
        this.blogList=[
            {title: 'VUE', author: '张三', category: '技术框架1', tag: '雪花算法', date: '2019-11-11',
            img: 'https://raw.githubusercontent.com/hansonwang99/pic/master/id-springbt-starter/profile.JPG',
            sketch: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',
            content: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'},
            {title: 'VUE', author: '张三', category: '技术框架1', tag: '雪花算法', date: '2019-11-11',
            img: 'https://raw.githubusercontent.com/hansonwang99/pic/master/id-springbt-starter/profile.JPG',
            sketch: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',
            content: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'}
        ]
        for(let i in this.blogList){
            this.$set(this.blogList[i],'status',false);
        }
    }
}
</script>
<style lang="stylus" scoped>
 #tec-learning
    width 80%
    margin 20px auto

.container
    padding 10px 10px
    margin-bottom 50px
    background-color #fafafa
    border-radius 5px

.title
    max-width 200px

.article
    margin 15px auto
    font-size 20px

.titleimg
    width 100%
    height 250px

.sketch
    margin 20px
    word-break break-all

.article-author, .article-date, .article-category, .article-tag
    margin-right 30px

.container > button
    background-color #4b4b4b
    border-color #c2c2c2

.content
    margin 20px
    word-break break-all
</style>