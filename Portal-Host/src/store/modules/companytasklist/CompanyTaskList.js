/**
 * Created by huangye on 2017/10/24.
 */
import mock from '../../../data/companytasklist/CompanyTaskListData'
import EventTypes from '../../EventTypes'

const state = {
  companytasklist: []
}

const mutations = {
  [EventTypes.COMPANYTASKLIST](state, payload) {
    console.log(payload)
    state.companytasklist = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYTASKLIST]({commit}, payload) {
    mock.companyTaskList.then(response => {
      commit(EventTypes.COMPANYTASKLIST, {list: response.data})
    })
  }
}

const getters = {
  companytasklist() {
    return state.companytasklist
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
