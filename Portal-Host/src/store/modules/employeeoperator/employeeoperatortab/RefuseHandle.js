/**
 * Created by huangye on 2017/10/24.
 */
import mock from '../../../../data/employeeoperator/employeeoperatortab/RefuseHandleData'
import EventTypes from '../../../EventTypes'

const state = {
  refusehandle: []
}

const mutations = {
  [EventTypes.REFUSEHANDLETYPE](state, payload) {
    console.log(payload)
    state.refusehandle = payload.list.data;
  }
}

const actions = {
  [EventTypes.REFUSEHANDLETYPE]({commit}, payload) {
    mock.refuseHandle.then(response => {
      commit(EventTypes.REFUSEHANDLETYPE, {list: response.data})
    })
  }
}

const getters = {
  refusehandle() {
    return state.refusehandle
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
