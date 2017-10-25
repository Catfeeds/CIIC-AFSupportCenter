/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../../data/companytasklist/companytasklisttab/CRefusedData'
import EventTypes from '../../../EventTypes'

const state = {
  crefused: []
}

const mutations = {
  [EventTypes.CREFUSEDTYPE](state, payload) {
    console.log(payload)
    state.crefused = payload.list.data;
  }
}

const actions = {
  [EventTypes.CREFUSEDTYPE]({commit}, payload) {
    mock.cRefused.then(response => {
      commit(EventTypes.CREFUSEDTYPE, {list: response.data})
    })
  }
}

const getters = {
  crefused() {
    return state.crefused
  }
}

const namespaced = true;

export default {
  namespaced: namespaced,
  state,
  mutations,
  actions,
  getters
}
