/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/employeespecialoperator/EmployeeSpecialOperatorViewData'
import EventTypes from '../../EventTypes'

const state = {
  employeespecialoperatorview: []
}

const mutations = {
  [EventTypes.EMPLOYEESPECIALOPERATORVIEW](state, payload) {
    console.log(payload)
    state.employeespecialoperatorview = payload.list.data;
  }
}

const actions = {
  [EventTypes.EMPLOYEESPECIALOPERATORVIEW]({commit}, payload) {
    mock.employeeSpecialOperatorView.then(response => {
      commit(EventTypes.EMPLOYEESPECIALOPERATORVIEW, {list: response.data})
    })
  }
}

const getters = {
  employeespecialoperatorview() {
    return state.employeespecialoperatorview
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

