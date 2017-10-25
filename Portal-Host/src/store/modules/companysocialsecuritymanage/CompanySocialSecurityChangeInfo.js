/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/companysocialsecuritymanage/CompanySocialSecurityChangeInfoData'
import EventTypes from '../../EventTypes'

const state = {
  companysocialsecuritychangeinfo: []
}

const mutations = {
  [EventTypes.COMPANYSOCIALSECURITYCHANGEINFOTYPE](state, payload) {
    console.log(payload)
    state.companysocialsecuritychangeinfo = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYSOCIALSECURITYCHANGEINFOTYPE]({commit}, payload) {
    mock.companySocialSecurityChangeInfo.then(response => {
      commit(EventTypes.COMPANYSOCIALSECURITYCHANGEINFOTYPE, {list: response.data})
    })
  }
}

const getters = {
  companysocialsecuritychangeinfo() {
    return state.companysocialsecuritychangeinfo
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
