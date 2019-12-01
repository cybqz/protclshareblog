<template>
    <div id="content">
       <div id="btn-list">
            <div id="btn-list-left">
                <Input search placeholder="Enter something..." />
            </div>
            <div id="btn-list-right">
                <Select v-model="model10" multiple style="width:260px">
                    <Option v-for="item in selectList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
                <Button icon="md-add" type="primary" shape="circle" @click="changeShowDrawer"></Button>
            </div>
        </div>
        <Drawer
            title="Create"
            v-model="showDrawer"
            width="720"
            :mask-closable="true"
            :styles="styles"
        >
            <Form :model="formData">
                <Row :gutter="32">
                    <Col span="12">
                        <FormItem label="Name" label-position="top">
                            <Input v-model="formData.name" placeholder="please enter user name" />
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="Url" label-position="top">
                            <Input v-model="formData.url" placeholder="please enter url">
                                <span slot="prepend">http://</span>
                                <span slot="append">.com</span>
                            </Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col span="12">
                        <FormItem label="Owner" label-position="top">
                            <Select v-model="formData.owner" placeholder="please select an owner">
                                <Option value="jobs">Steven Paul Jobs</Option>
                                <Option value="ive">Sir Jonathan Paul Ive</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="Type" label-position="top">
                            <Select v-model="formData.type" placeholder="please choose the type">
                                <Option value="private">Private</Option>
                                <Option value="public">Public</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col span="12">
                        <FormItem label="Approver" label-position="top">
                            <Select v-model="formData.approver" placeholder="please choose the approver">
                                <Option value="jobs">Steven Paul Jobs</Option>
                                <Option value="ive">Sir Jonathan Paul Ive</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="DateTime" label-position="top">
                            <DatePicker v-model="formData.date" type="daterange" placeholder="please select the date" style="display: block" placement="bottom-end"></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
                <FormItem label="Description" label-position="top">
                    <Input type="textarea" v-model="formData.desc" :rows="4" placeholder="please enter the description" />
                </FormItem>
            </Form>
            <div class="demo-drawer-footer">
                <Button style="margin-right: 8px" @click="showDrawer = false">取消</Button>
                <Button type="primary" @click="showDrawer = false">添加</Button>
            </div>
        </Drawer>    
        <router-view></router-view>   
    </div>
</template>
<script>
export default {
    data(){
        return {
                model10:'',
                styles: {
                    height: 'calc(100% - 55px)',
                    overflow: 'auto',
                    paddingBottom: '53px',
                    position: 'static'
                },
                formData: {
                    name: '',
                    url: '',
                    owner: '',
                    type: '',
                    approver: '',
                    date: '',
                    desc: ''
                },
                showDrawer: false,
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
            this.showDrawer = true
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