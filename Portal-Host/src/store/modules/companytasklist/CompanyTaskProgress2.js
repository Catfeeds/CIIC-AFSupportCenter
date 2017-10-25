/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/companytasklist/CompanyTaskProgress2Data'
import EventTypes from '../../EventTypes'

const state = {
  companytaskprogress2: []
}

const mutations = {
  [EventTypes.COMPANYTASKPROGRESS2TYPE](state, payload) {
    console.log(payload)
    state.companytaskprogress2 = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYTASKPROGRESS2TYPE]({commit}, payload) {
    mock.companyTaskProgress2.then(response => {
      commit(EventTypes.COMPANYTASKPROGRESS2TYPE, {list: response.data})
    })
  }
}

const getters = {
  companytaskprogress2() {
    return state.companytaskprogress2
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
