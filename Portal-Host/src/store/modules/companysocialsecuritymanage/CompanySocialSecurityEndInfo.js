/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/companysocialsecuritymanage/CompanySocialSecurityEndInfoData'
import EventTypes from '../../EventTypes'

const state = {
  companysocialsecurityendinfo: []
}

const mutations = {
  [EventTypes.COMPANYSOCIALSECURITYENDINFOTYPE](state, payload) {
    console.log(payload)
    state.companysocialsecurityendinfo = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYSOCIALSECURITYENDINFOTYPE]({commit}, payload) {
    mock.companySocialSecurityEndInfo.then(response => {
      commit(EventTypes.COMPANYSOCIALSECURITYENDINFOTYPE, {list: response.data})
    })
  }
}

const getters = {
  companysocialsecurityendinfo() {
    return state.companysocialsecurityendinfo
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
