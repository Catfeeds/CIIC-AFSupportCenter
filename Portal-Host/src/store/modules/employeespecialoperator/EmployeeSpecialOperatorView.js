/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/employeespecialoperator/EmployeeSpecialOperatorViewData'
import * as EmployeeSpecialOperatorViewType from '../../EventTypes/employeespecialoperator/EmployeeSpecialOperatorViewType'
import axios from 'axios'

const state = {
  employeespecialoperatorview: []
}

const mutations = {
  [EmployeeSpecialOperatorViewType.EMPLOYEESPECIALOPERATORVIEW](state, payload) {
    console.log(payload)
    state.employeespecialoperatorview = payload.list.data;
  }
}

const actions = {
  [EmployeeSpecialOperatorViewType.EMPLOYEESPECIALOPERATORVIEW]({commit}, payload) {
    mock.employeeSpecialOperatorView.then(response => {
      commit(EmployeeSpecialOperatorViewType.EMPLOYEESPECIALOPERATORVIEW, {list: response.data})
    })
  }
}

const getters = {
  employeespecialoperatorview() {
    return state.employeespecialoperatorview
  }
}

export const employeeSpecialOperatorView = {
  state,
  mutations,
  actions,
  getters
}
