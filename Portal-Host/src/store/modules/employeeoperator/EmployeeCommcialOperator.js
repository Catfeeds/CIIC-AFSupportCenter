/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/employeeoperator/EmployeeCommcialOperatorData'
import EventTypes from '../../EventTypes'

const state = {
  employeecommcialoperator: []
}

const mutations = {
  [EventTypes.EMPLOYEECOMMCIALOPERATOR](state, payload) {
    console.log(payload)
    state.employeecommcialoperator = payload.list.data;
  }
}

const actions = {
  [EventTypes.EMPLOYEECOMMCIALOPERATOR]({commit}, payload) {
    mock.employeeCommcialOperator.then(response => {
      commit(EventTypes.EMPLOYEECOMMCIALOPERATOR, {list: response.data})
    })
  }
}

const getters = {
  employeecommcialoperator() {
    return state.employeecommcialoperator
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

