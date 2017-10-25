/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../../data/employeespecialoperator/employeespecialoperatortab/FinishedData'
import EventTypes from '../../../EventTypes'

const state = {
  finished: []
}

const mutations = {
  [EventTypes.FINISHEDTYPE](state, payload) {
    console.log(payload)
    state.finished = payload.list.data;
  }
}

const actions = {
  [EventTypes.FINISHEDTYPE]({commit}, payload) {
    mock.finished.then(response => {
      commit(EventTypes.FINISHEDTYPE, {list: response.data})
    })
  }
}

const getters = {
  finished() {
    return state.finished
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
