/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/socialsecuritypay/SocialSecurityPayData'
import EventTypes from '../../EventTypes'

const state = {
  socialsecuritypay: []
}

const mutations = {
  [EventTypes.SOCIALSECURITYPAYTYPE](state, payload) {
    console.log(payload)
    state.socialsecuritypay = payload.list.data;
  }
}

const actions = {
  [EventTypes.SOCIALSECURITYPAYTYPE]({commit}, payload) {
    mock.socialSecurityPay.then(response => {
      commit(EventTypes.SOCIALSECURITYPAYTYPE, {list: response.data})
    })
  }
}

const getters = {
  socialsecuritypay() {
    return state.socialsecuritypay
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
