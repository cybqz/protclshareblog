<template>
    <div>A</div>
</template>
<script>
export default {
  name: 'a',
  data () {
    return {
      msg: 'Hi, I am JSPang'
    }
  },
  methods:{
        test: function(){
            this.$axios.get('yjdwyjqxjl/qxdjpage',{
                params: {                           //参数
                    current: 1,
                    size: 10,
                },
                }).then(res => {                   //请求成功后的处理函数     
                    console.log(res);   
                }).catch(err => {                 //请求失败后的处理函数   
                    console.log(err);
                })
        }
    },
    created(){
        this.test();
    }
}
</script>