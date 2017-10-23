/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/employeeoperator/EmployeeOperatorViewData'
import * as EmployeeOperatorViewType from '../../EventTypes/employeeoperator/EmployeeOperatorViewType'
import axios from 'axios'

const state = {
  employeeoperatorview: []
}

const mutations = {
  [EmployeeOperatorViewType.EMPLOYEEOPERATORVIEW](state, payload) {
    console.log(payload)
    state.employeeoperatorview = payload.list.data;
  }
}

const actions = {
  [EmployeeOperatorViewType.EMPLOYEEOPERATORVIEW]({commit}, payload) {
    mock.employeeOperatorView.then(response => {
      commit(EmployeeOperatorViewType.EMPLOYEEOPERATORVIEW, {list: response.data})
    })
  }
}

const getters = {
  employeeoperatorview() {
    return state.employeeoperatorview
  }
}

export const employeeOperatorView = {
  state,
  mutations,
  actions,
  getters
}
