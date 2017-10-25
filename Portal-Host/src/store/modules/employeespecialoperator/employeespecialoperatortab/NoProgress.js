/**
 * Created by huangye on 2017/10/24.
 */
import mock from '../../../../data/employeespecialoperator/employeespecialoperatortab/NoProgressData'
import EventTypes from '../../../EventTypes'

const state = {
  noprogress: []
}

const mutations = {
  [EventTypes.NOPROGRESSTYPE](state, payload) {
    console.log(payload)
    state.noprogress = payload.list.data;
  }
}

const actions = {
  [EventTypes.NOPROGRESSTYPE]({commit}, payload) {
    mock.noProgress.then(response => {
      commit(EventTypes.NOPROGRESSTYPE, {list: response.data})
    })
  }
}

const getters = {
  noprogress() {
    return state.noprogress
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
