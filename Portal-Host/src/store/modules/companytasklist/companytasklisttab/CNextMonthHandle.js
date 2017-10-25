/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../../data/companytasklist/companytasklisttab/CNextMonthHandleData'
import EventTypes from '../../../EventTypes'

const state = {
  cnextmonthhandle: []
}

const mutations = {
  [EventTypes.CNEXTMONTHHANDLETYPE](state, payload) {
    console.log(payload)
    state.cnextmonthhandle = payload.list.data;
  }
}

const actions = {
  [EventTypes.CNEXTMONTHHANDLETYPE]({commit}, payload) {
    mock.cNextMonthHandle.then(response => {
      commit(EventTypes.CNEXTMONTHHANDLETYPE, {list: response.data})
    })
  }
}

const getters = {
  cnextmonthhandle() {
    return state.cnextmonthhandle
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
