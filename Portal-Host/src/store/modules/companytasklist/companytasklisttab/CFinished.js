/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../../data/companytasklist/companytasklisttab/CFinishedData'
import EventTypes from '../../../EventTypes'

const state = {
  cfinished: []
}

const mutations = {
  [EventTypes.CFINISHEDTYPE](state, payload) {
    console.log(payload)
    state.cfinished = payload.list.data;
  }
}

const actions = {
  [EventTypes.CFINISHEDTYPE]({commit}, payload) {
    mock.cFinished.then(response => {
      commit(EventTypes.CFINISHEDTYPE, {list: response.data})
    })
  }
}

const getters = {
  cfinished() {
    return state.cfinished
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
