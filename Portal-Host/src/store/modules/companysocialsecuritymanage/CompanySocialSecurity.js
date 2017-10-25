/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/companysocialsecuritymanage/CompanySocialSecurityData'
import EventTypes from '../../EventTypes'

const state = {
  companysocialsecurity: []
}

const mutations = {
  [EventTypes.COMPANYSOCIALSECURITYTYPE](state, payload) {
    console.log(payload)
    state.companysocialsecurity = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYSOCIALSECURITYTYPE]({commit}, payload) {
    mock.companySocialSecurity.then(response => {
      commit(EventTypes.COMPANYSOCIALSECURITYTYPE, {list: response.data})
    })
  }
}

const getters = {
  companysocialsecurity() {
    return state.companysocialsecurity
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
