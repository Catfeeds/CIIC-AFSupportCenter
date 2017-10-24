/**
 * Created by huangye on 2017/10/24.
 */
import mock from '../../../data/companytasklist/CompanyTaskListData'
import * as CompanyTaskListType from '../../EventTypes/companytasklist/CompanyTaskListType'
import axios from 'axios'

const state = {
  companytasklist: []
}

const mutations = {
  [CompanyTaskListType.COMPANYTASKLIST](state, payload) {
    console.log(payload)
    state.companytasklist = payload.list.data;
  }
}

const actions = {
  [CompanyTaskListType.COMPANYTASKLIST]({commit}, payload) {
    mock.companyTaskList.then(response => {
      commit(CompanyTaskListType.COMPANYTASKLIST, {list: response.data})
    })
  }
}

const getters = {
  companytasklist() {
    return state.companytasklist
  }
}

export const companyTaskList = {
  state,
  mutations,
  actions,
  getters
}
