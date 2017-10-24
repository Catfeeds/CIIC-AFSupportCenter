/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/employeespecialoperator/EmployeeSpecialProgress3Data'
import EventTypes from '../../EventTypes'

const state = {
  employeespecialprogress3: []
}

const mutations = {
  [EventTypes.EMPLOYEESPECIALPROGRESS3](state, payload) {
    console.log(payload)
    state.employeespecialprogress3 = payload.list.data;
  }
}

const actions = {
  [EventTypes.EMPLOYEESPECIALPROGRESS3]({commit}, payload) {
    mock.employeeSpecialProgress3.then(response => {
      commit(EventTypes.EMPLOYEESPECIALPROGRESS3, {list: response.data})
    })
  }
}

const getters = {
  employeespecialprogress3() {
    return state.employeespecialprogress3
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
