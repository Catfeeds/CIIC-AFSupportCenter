import mock from '../../../data/shsocialsecurity/CompanySocialSecurityNewData'
import EventTypes from '../../EventTypes'

const state = {
  companysocialsecuritynew: []
}

const mutations = {
  [EventTypes.COMPANYSOCIALSECURITYNEWTYPE](state, payload) {
    console.log(payload)
    state.companysocialsecuritynew = payload.list.data;
  }
}

const actions = {
  [EventTypes.COMPANYSOCIALSECURITYNEWTYPE]({commit}, payload) {
    mock.companySocialSecurityNew.then(response => {
      commit(EventTypes.COMPANYSOCIALSECURITYNEWTYPE, {list: response.data})
    })
  }
}

const getters = {
  companysocialsecuritynew() {
    return state.companysocialsecuritynew
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

