/**
 * Created by huangye on 2017/10/24.
 */
import mock from '../../../../data/employeeoperator/employeeoperatortab/ThisMonthHandleData'
import EventTypes from '../../../EventTypes'

const state = {
  thismonthhandle: []
}

const mutations = {
  [EventTypes.THISMONTHHANDLETYPE](state, payload) {
    console.log(payload)
    state.thismonthhandle = payload.list.data;
  }
}

const actions = {
  [EventTypes.THISMONTHHANDLETYPE]({commit}, payload) {
    mock.thisMonthHandle.then(response => {
      commit(EventTypes.THISMONTHHANDLETYPE, {list: response.data})
    })
  }
}

const getters = {
  thismonthhandle() {
    return state.thismonthhandle
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
