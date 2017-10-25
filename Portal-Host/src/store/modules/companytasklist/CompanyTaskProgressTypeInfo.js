/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/companytasklist/CompanyTaskProgressTypeInfoData'
import EventTypes from '../../EventTypes'

const state = {
  companytaskprogresstypeinfo: []
}

const mutations = {
  [EventTypes.COMPANYTASKPROGRESSTYPEINFOTYPE](state, payload) {
    console.log(payload)
    state.companytaskprogresstypeinfo = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYTASKPROGRESSTYPEINFOTYPE]({commit}, payload) {
    mock.companyTaskProgressTypeInfo.then(response => {
      commit(EventTypes.COMPANYTASKPROGRESSTYPEINFOTYPE, {list: response.data})
    })
  }
}

const getters = {
  companytaskprogresstypeinfo() {
    return state.companytaskprogresstypeinfo
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
