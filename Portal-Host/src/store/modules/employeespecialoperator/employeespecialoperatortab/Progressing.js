/**
 * Created by huangye on 2017/10/24.
 */
import mock from '../../../../data/employeespecialoperator/employeespecialoperatortab/ProgressingData'
import EventTypes from '../../../EventTypes'

const state = {
  progressing: []
}

const mutations = {
  [EventTypes.PROGRESSINGTYPE](state, payload) {
    console.log(payload)
    state.progressing = payload.list.data;
  }
}

const actions = {
  [EventTypes.PROGRESSINGTYPE]({commit}, payload) {
    mock.Progressing.then(response => {
      commit(EventTypes.PROGRESSINGTYPE, {list: response.data})
    })
  }
}

const getters = {
  progressing() {
    return state.progressing
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
