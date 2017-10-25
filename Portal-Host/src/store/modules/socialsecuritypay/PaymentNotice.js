/**
 * Created by huangye on 2017/10/25.
 */
import mock from '../../../data/socialsecuritypay/PaymentNoticeData'
import EventTypes from '../../EventTypes'

const state = {
  paymentnotice: []
}

const mutations = {
  [EventTypes.PAYMENTNOTICETYPE](state, payload) {
    console.log(payload)
    state.paymentnotice = payload.list.data;
  }
}

const actions = {
  [EventTypes.PAYMENTNOTICETYPE]({commit}, payload) {
    mock.paymentNotice.then(response => {
      commit(EventTypes.PAYMENTNOTICETYPE, {list: response.data})
    })
  }
}

const getters = {
  paymentnotice() {
    return state.paymentnotice
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
