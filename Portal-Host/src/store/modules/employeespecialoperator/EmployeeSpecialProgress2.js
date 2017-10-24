/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/employeespecialoperator/EmployeeSpecialProgress2Data'
import * as EmployeeSpecialProgress2Type from '../../EventTypes/employeespecialoperator/EmployeeSpecialProgress2Type'
import axios from 'axios'

const state = {
  employeespecialprogress2: []
}

const mutations = {
  [EmployeeSpecialProgress2Type.EMPLOYEESPECIALPROGRESS2](state, payload) {
    console.log(payload)
    state.employeespecialprogress2 = payload.list.data;
  }
}

const actions = {
  [EmployeeSpecialProgress2Type.EMPLOYEESPECIALPROGRESS2]({commit}, payload) {
    mock.employeeSpecialProgress2.then(response => {
      commit(EmployeeSpecialProgress2Type.EMPLOYEESPECIALPROGRESS2, {list: response.data})
    })
  }
}

const getters = {
  employeespecialprogress2() {
    return state.employeespecialprogress2
  }
}

export const employeeSpecialProgress2 = {
  state,
  mutations,
  actions,
  getters
}
