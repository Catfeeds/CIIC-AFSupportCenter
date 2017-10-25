/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/companysocialsecuritymanage/CompanySocialSecurityManageData'
import EventTypes from '../../EventTypes'

const state = {
  companysocialsecuritymanage: []
}

const mutations = {
  [EventTypes.COMPANYSOCIALSECURITYMANAGETYPE](state, payload) {
    console.log(payload)
    state.companysocialsecuritymanage = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYSOCIALSECURITYMANAGETYPE]({commit}, payload) {
    mock.companySocialSecurityManage.then(response => {
      commit(EventTypes.COMPANYSOCIALSECURITYMANAGETYPE, {list: response.data})
    })
  }
}

const getters = {
  companysocialsecuritymanage() {
    return state.companysocialsecuritymanage
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
