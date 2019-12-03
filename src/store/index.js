import Vue from 'vue'
import Vuex from 'vuex'
import * as types from './mutation-type'  // 引入定义的方法
import createLogger from 'vuex/dist/logger'

Vue.use(Vuex)
const debug = true

export default new Vuex.Store({
    state: {
        model: '' //当前选中的模块
    },
    mutations: {
        [types.SET_MODEL](state, model) {
            state.model = model
        }
    },
    actions: {},
    getters: {            // 定义getters，可以通过mapGetters拓展函数调用
        model: state => {
            return state.model
        }
    },
    plugins: debug ? [createLogger()] : []   // 是否开启vuex的debug模式
 })