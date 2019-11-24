import Vue from 'vue'
import Vuex from 'vuex'
import * as types from './mutation-type'  // 引入定义的方法
import createLogger from 'vuex/dist/logger'

Vue.use(Vuex)
const debug = true

export default new Vuex.Store({
    state: {
        showDrawer: false
    },
    mutations: {
        [types.SET_SHOW_DRAWER](state, showDrawer) {
            state.showDrawer = showDrawer
        }
    },
    actions: {},
    getters: {            // 定义getters，可以通过mapGetters拓展函数调用
        showDrawer: state => {
            return state.showDrawer
        }
    },
    plugins: debug ? [createLogger()] : []   // 是否开启vuex的debug模式
 })