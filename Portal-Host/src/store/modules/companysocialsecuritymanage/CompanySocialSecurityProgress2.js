/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/companysocialsecuritymanage/CompanySocialSecurityProgress2Data'
import EventTypes from '../../EventTypes'

const state = {
  companysocialsecurityprogress2: []
}

const mutations = {
  [EventTypes.COMPANYSOCIALSECURITYPROGRESS2TYPE](state, payload) {
    console.log(payload)
    state.companysocialsecurityprogress2 = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYSOCIALSECURITYPROGRESS2TYPE]({commit}, payload) {
    mock.companySocialSecurityProgress2.then(response => {
      commit(EventTypes.COMPANYSOCIALSECURITYPROGRESS2TYPE, {list: response.data})
    })
  }
}

const getters = {
  companysocialsecurityprogress2() {
    return state.companysocialsecurityprogress2
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
