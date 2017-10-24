/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/employeeoperator/EmployeeCommcialOperatorData'
import * as EmployeeCommcialOperatorType from '../../EventTypes/employeeoperator/EmployeeCommcialOperatorType'
import axios from 'axios'

const state = {
  employeecommcialoperator: []
}

const mutations = {
  [EmployeeCommcialOperatorType.EMPLOYEECOMMCIALOPERATOR](state, payload) {
    console.log(payload)
    state.employeecommcialoperator = payload.list.data;
  }
}

const actions = {
  [EmployeeCommcialOperatorType.EMPLOYEECOMMCIALOPERATOR]({commit}, payload) {
    mock.employeeCommcialOperator.then(response => {
      commit(EmployeeCommcialOperatorType.EMPLOYEECOMMCIALOPERATOR, {list: response.data})
    })
  }
}

const getters = {
  employeecommcialoperator() {
    return state.employeecommcialoperator
  }
}

export const employeeCommcialOperator = {
  state,
  mutations,
  actions,
  getters
}
