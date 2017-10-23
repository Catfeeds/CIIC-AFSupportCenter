/**
 * Created by huangye on 2017/10/23.
 */
/**
 * Created by huangye on 2017/10/20.
 */
import mock from '../../../data/shsocialsecurity/EmployeeSocialSecurityInfoData'
import * as EmployeeSocialSecurityInfoType from '../../EventTypes/shsocialsecurity/EmployeeSocialSecurityInfoType'
import axios from 'axios'

const state = {
  employeesocialsecurityinfo: []
}

const mutations = {
  [EmployeeSocialSecurityInfoType.EMPLOYEESOCIALSECURITYINFO](state, payload) {
    console.log(payload)
    state.employeesocialsecurityinfo = payload.list.data;
  }
}

const actions = {
  [EmployeeSocialSecurityInfoType.EMPLOYEESOCIALSECURITYINFO]({commit}, payload) {
    mock.employeeSocialSecurityInfo.then(response => {
      commit(EmployeeSocialSecurityInfoType.EMPLOYEESOCIALSECURITYINFO, {list: response.data})
  })
  }
}

const getters = {
  employeesocialsecurityinfo() {
    return state.employeesocialsecurityinfo
  }
}

export const employeeSocialSecurityInfo = {
  state,
  mutations,
  actions,
  getters
}
