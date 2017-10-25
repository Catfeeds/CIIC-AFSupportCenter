/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/companytasklist/CompanyTaskProgressChangeInfoData'
import EventTypes from '../../EventTypes'

const state = {
  companytaskprogresschangeinfo: []
}

const mutations = {
  [EventTypes.COMPANYTASKPROGRESSCHANGEINFOTYPE](state, payload) {
    console.log(payload)
    state.companytaskprogresschangeinfo = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYTASKPROGRESSCHANGEINFOTYPE]({commit}, payload) {
    mock.companyTaskProgressChangeInfo.then(response => {
      commit(EventTypes.COMPANYTASKPROGRESSCHANGEINFOTYPE, {list: response.data})
    })
  }
}

const getters = {
  companytaskprogresschangeinfo() {
    return state.companytaskprogresschangeinfo
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
