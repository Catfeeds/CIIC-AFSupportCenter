/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/employeeoperator/EmployeeOperatorViewData'
import EventTypes from '../../EventTypes'

const state = {
  employeeoperatorview: []
}

const mutations = {
  [EventTypes.EMPLOYEEOPERATORVIEW](state, payload) {
    console.log(payload)
    state.employeeoperatorview = payload.list.data;
  }
}

const actions = {
  [EventTypes.EMPLOYEEOPERATORVIEW]({commit}, payload) {
    mock.employeeOperatorView.then(response => {
      commit(EventTypes.EMPLOYEEOPERATORVIEW, {list: response.data})
    })
  }
}

const getters = {
  employeeoperatorview() {
    return state.employeeoperatorview
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
