/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../../data/companytasklist/companytasklisttab/CThisMonthHandleData'
import EventTypes from '../../../EventTypes'

const state = {
  cthismonthhandle: []
}

const mutations = {
  [EventTypes.CTHISMONTHHANDLETYPE](state, payload) {
    console.log(payload)
    state.cthismonthhandle = payload.list.data;
  }
}

const actions = {
  [EventTypes.CTHISMONTHHANDLETYPE]({commit}, payload) {
    mock.cThisMonthHandle.then(response => {
      commit(EventTypes.CTHISMONTHHANDLETYPE, {list: response.data})
    })
  }
}

const getters = {
  cthismonthhandle() {
    return state.cthismonthhandle
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
