import mock from '../../../data/shsocialsecurity/EmployeeSocialSecuritySearchData'
import EventTypes from '../../EventTypes'

const state = {
  employeesocialsecuritysearch: []
}

const mutations = {
  [EventTypes.EMPLOYEESOCIALSECURITYSEARCH](state, payload) {
    console.log(payload)
    state.employeesocialsecuritysearch = payload.list.data;
  }
}

const actions = {
  [EventTypes.EMPLOYEESOCIALSECURITYSEARCH]({commit}, payload) {
    mock.employeeSocialSecuritySearch.then(response => {
      commit(EventTypes.EMPLOYEESOCIALSECURITYSEARCH, {list: response.data})
  })
  }
}

const getters = {
  employeesocialsecuritysearch() {
    return state.employeesocialsecuritysearch
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

