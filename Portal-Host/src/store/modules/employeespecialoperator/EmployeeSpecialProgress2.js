/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/employeespecialoperator/EmployeeSpecialProgress2Data'
import EventTypes from '../../EventTypes'

const state = {
  employeespecialprogress2: []
}

const mutations = {
  [EventTypes.EMPLOYEESPECIALPROGRESS2](state, payload) {
    console.log(payload)
    state.employeespecialprogress2 = payload.list.data;
  }
}

const actions = {
  [EventTypes.EMPLOYEESPECIALPROGRESS2]({commit}, payload) {
    mock.employeeSpecialProgress2.then(response => {
      commit(EventTypes.EMPLOYEESPECIALPROGRESS2, {list: response.data})
    })
  }
}

const getters = {
  employeespecialprogress2() {
    return state.employeespecialprogress2
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
