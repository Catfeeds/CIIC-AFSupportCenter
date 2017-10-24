import mock from '../../../data/shsocialsecurity/EmployeeSocialSecurityInfoData'
import EventTypes from '../../EventTypes'

const state = {
  employeesocialsecurityinfo: []
}

const mutations = {
  [EventTypes.EMPLOYEESOCIALSECURITYINFO](state, payload) {
    console.log(payload)
    state.employeesocialsecurityinfo = payload.list.data;
  }
}

const actions = {
  [EventTypes.EMPLOYEESOCIALSECURITYINFO]({commit}, payload) {
    mock.employeeSocialSecurityInfo.then(response => {
      commit(EventTypes.EMPLOYEESOCIALSECURITYINFO, {list: response.data})
  })
  }
}

const getters = {
  employeesocialsecurityinfo() {
    return state.employeesocialsecurityinfo
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
