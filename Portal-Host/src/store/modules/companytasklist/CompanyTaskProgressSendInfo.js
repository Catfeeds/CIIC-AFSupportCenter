/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/companytasklist/CompanyTaskProgressSendInfoData'
import EventTypes from '../../EventTypes'

const state = {
  companytaskprogresssendinfo: []
}

const mutations = {
  [EventTypes.COMPANYTASKPROGRESSSENDINFOTYPE](state, payload) {
    console.log(payload)
    state.companytaskprogresssendinfo = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYTASKPROGRESSSENDINFOTYPE]({commit}, payload) {
    mock.companyTaskProgressSendInfo.then(response => {
      commit(EventTypes.COMPANYTASKPROGRESSSENDINFOTYPE, {list: response.data})
    })
  }
}

const getters = {
  companytaskprogresssendinfo() {
    return state.companytaskprogresssendinfo
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
