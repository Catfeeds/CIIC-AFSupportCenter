/**
 * Created by huangye on 2017/10/24.
 */
import mock from '../../../../data/employeeoperator/employeeoperatortab/NextMonthHandleData'
import EventTypes from '../../../EventTypes'

const state = {
  nextmonthhandle: []
}

const mutations = {
  [EventTypes.NEXTMONTHHANDLETYPE](state, payload) {
    console.log(payload)
    state.nextmonthhandle = payload.list.data;
  }
}

const actions = {
  [EventTypes.NEXTMONTHHANDLETYPE]({commit}, payload) {
    mock.nextMonthHandle.then(response => {
      commit(EventTypes.NEXTMONTHHANDLETYPE, {list: response.data})
    })
  }
}

const getters = {
  nextmonthhandle() {
    return state.nextmonthhandle
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
