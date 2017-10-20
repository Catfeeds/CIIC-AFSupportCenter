const state = {
    progress: -1,
    messageConfig: {}
}

const actions = {
    ['gProgress']({ commit }, payload) {
        commit('progress', payload)
    },
    ['gMessage']({ commit }, payload) {
        commit('rMessage', payload)
    }
}

const mutations = {
    ['progress'](state, payload) {
        state.progress = payload
    },
    ['rMessage'](state, payload) {
        state.messageConfig = payload
    }
}

const getters = {
    ['getGlobal'](state) {
        return state
    }
}

export default {
    actions,
    state,
    mutations,
    getters
}
