/**
 * Created by huangye on 2017/10/23.
 */
import mock from '../../../data/shsocialsecurity/CompanySocialSecurityNewData'
import * as CompanySocialSecurityNewType from '../../EventTypes/shsocialsecurity/CompanySocialSecurityNewType'
import axios from 'axios'

const state = {
  companysocialsecuritynew: []
}

const mutations = {
  [CompanySocialSecurityNewType.COMPANYSOCIALSECURITYNEWTYPE](state, payload) {
    console.log(payload)
    state.companysocialsecuritynew = payload.list.data;
  }
}

const actions = {
  [CompanySocialSecurityNewType.COMPANYSOCIALSECURITYNEWTYPE]({commit}, payload) {
    mock.companySocialSecurityNew.then(response => {
      commit(CompanySocialSecurityNewType.COMPANYSOCIALSECURITYNEWTYPE, {list: response.data})
    })
  }
}

const getters = {
  companysocialsecuritynew() {
    return state.companysocialsecuritynew
  }
}

export const companySocialSecurityNew = {
  state,
  mutations,
  actions,
  getters
}
