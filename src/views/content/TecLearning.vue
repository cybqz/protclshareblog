<template>
    <div id="tec-learning">
        <div :id="'container-'+index" class="container" v-for="(item , index) in blogList" :key="index" :ref="'container-'+index">
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
            <img class="titleimg" :src="getImageUrl(item.img)"/>
            <div class="sketch">
                {{item.sketch}}
            </div>
            <Button type="primary" shape="circle" icon="md-eye" @click="readAll(index)">阅读全部</Button>
            <div :id="'content-'+index" class="content" v-show="item.status" :ref="'content-'+index">
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
      blogList: [],

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
            let _this = this;
            let status = _this.blogList[index].status;
            let containerTgt = _this.$refs['container-' + index];
            let contentTgt = _this.$refs['content-' + index];
            //关闭动画
            if(status){
                _this.$anime({
                    targets: contentTgt,
                    translateY: -10,
                    duration: 500, //持续时间
                    opacity: 0,
                    delay: 0, //延迟
                    easing: 'linear',
                    update: function(anim) {},
                    begin: function(anim) {},
                    complete: function(anim) {
                        _this.$set(_this.blogList[index],'status',false);
                        //console.log('close  '+containerTgt[0].offsetHeight);
                        console.log('close  '+contentTgt[0].offsetHeight);
                    }
                });
            }else{  //打开动画
                _this.$set(_this.blogList[index],'status',true);
                //console.log('open  '+containerTgt[0].offsetHeight);
                console.log('open  '+ contentTgt[0].offsetHeight);     
                _this.$anime({
                    targets: contentTgt,
                    translateY: 10,
                    duration: 500,
                    opacity: 1,
                    delay: 0,
                    easing: 'linear'
                });
            }
        },

        //加载图片
        getImageUrl: function(img){
            return require("@/assets/images/"+img+".png");
        }
    },
    created(){
        this.test();
    },
    mounted(){
        for(let i = 0; i < 2; i++){
            this.blogList.push({title: 'VUE', author: '张三', category: '技术框架1', tag: '雪花算法', date: '2019-11-11',
            img: 'profile',
            sketch: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',
            content: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'});
        }
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