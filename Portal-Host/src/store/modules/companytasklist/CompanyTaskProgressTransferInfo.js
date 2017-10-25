/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/companytasklist/CompanyTaskProgressTransferInfoData'
import EventTypes from '../../EventTypes'

const state = {
  companytaskprogresstransferinfo: []
}

const mutations = {
  [EventTypes.COMPANYTASKPROGRESSTRANSFERINFOTYPE](state, payload) {
    console.log(payload)
    state.companytaskprogresstransferinfo = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYTASKPROGRESSTRANSFERINFOTYPE]({commit}, payload) {
    mock.companyTaskProgressTransferInfo.then(response => {
      commit(EventTypes.COMPANYTASKPROGRESSTRANSFERINFOTYPE, {list: response.data})
    })
  }
}

const getters = {
  companytaskprogresstransferinfo() {
    return state.companytaskprogresstransferinfo
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
