/**
 * Created by huangye on 2017/10/20.
 */
import mock from '../../../data/shsocialsecurity/EmployeeSocialSecuritySearchData'
import * as EmployeeSocialSecuritySearchType from '../../EventTypes/shsocialsecurity/EmployeeSocialSecuritySearchType'
import axios from 'axios'

const state = {
  employeesocialsecuritysearch: []
}

const mutations = {
  [EmployeeSocialSecuritySearchType.EMPLOYEESOCIALSECURITYSEARCH](state, payload) {
    console.log(payload)
    state.employeesocialsecuritysearch = payload.list.data;
  }
}

const actions = {
  [EmployeeSocialSecuritySearchType.EMPLOYEESOCIALSECURITYSEARCH]({commit}, payload) {
    mock.employeeSocialSecuritySearch.then(response => {
      commit(EmployeeSocialSecuritySearchType.EMPLOYEESOCIALSECURITYSEARCH, {list: response.data})
  })
  }
}

const getters = {
  employeesocialsecuritysearch() {
    return state.employeesocialsecuritysearch
  }
}

export const employeeSocialSecuritySearch = {
  state,
  mutations,
  actions,
  getters
}
