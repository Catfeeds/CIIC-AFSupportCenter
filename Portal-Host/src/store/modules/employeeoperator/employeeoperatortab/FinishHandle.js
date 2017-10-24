/**
 * Created by huangye on 2017/10/24.
 */
import mock from '../../../../data/employeeoperator/employeeoperatortab/FinishHandleData'
import EventTypes from '../../../EventTypes'

const state = {
  finishhandle: []
}

const mutations = {
  [EventTypes.FINISHHANDLETYPE](state, payload) {
    console.log(payload)
    state.finishhandle = payload.list.data;
  }
}

const actions = {
  [EventTypes.FINISHHANDLETYPE]({commit}, payload) {
    mock.finishHandle.then(response => {
      commit(EventTypes.FINISHHANDLETYPE, {list: response.data})
    })
  }
}

const getters = {
  finishhandle() {
    return state.finishhandle
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
