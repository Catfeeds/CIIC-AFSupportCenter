/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../../data/employeespecialoperator/employeespecialoperatortab/RefusedData'
import EventTypes from '../../../EventTypes'

const state = {
  refused: []
}

const mutations = {
  [EventTypes.REFUSEDTYPE](state, payload) {
    console.log(payload)
    state.refused = payload.list.data;
  }
}

const actions = {
  [EventTypes.REFUSEDTYPE]({commit}, payload) {
    mock.refused.then(response => {
      commit(EventTypes.REFUSEDTYPE, {list: response.data})
    })
  }
}

const getters = {
  refused() {
    return state.refused
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
