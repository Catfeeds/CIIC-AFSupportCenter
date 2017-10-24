/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/employeespecialoperator/EmployeeSpecialProgress3Data'
import * as EmployeeSpecialProgress3Type from '../../EventTypes/employeespecialoperator/EmployeeSpecialProgress3Type'
import axios from 'axios'

const state = {
  employeespecialprogress3: []
}

const mutations = {
  [EmployeeSpecialProgress3Type.EMPLOYEESPECIALPROGRESS3](state, payload) {
    console.log(payload)
    state.employeespecialprogress3 = payload.list.data;
  }
}

const actions = {
  [EmployeeSpecialProgress3Type.EMPLOYEESPECIALPROGRESS3]({commit}, payload) {
    mock.employeeSpecialProgress3.then(response => {
      commit(EmployeeSpecialProgress3Type.EMPLOYEESPECIALPROGRESS3, {list: response.data})
    })
  }
}

const getters = {
  employeespecialprogress3() {
    return state.employeespecialprogress3
  }
}

export const employeeSpecialProgress3 = {
  state,
  mutations,
  actions,
  getters
}
