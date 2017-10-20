import mock from '../../../data/payrolltask/PayrollTaskSubmitData'
import * as PayrollTaskSubmitType from '../../EventTypes/PayrollTaskSubmitType'
import axios from 'axios'

const state = {
	payrolltasksubmitlist: []
}

const mutations = {
	[PayrollTaskSubmitType.PAYROLLTASKSUBMIT_SETLIST](state,payload){
    console.log(payload)
		state.payrolltasksubmitlist = payload.list.data;
		// console.log(state.payrolltasksubmitlist);
	}
}

const actions = {
	[PayrollTaskSubmitType.PAYROLLTASKSUBMIT_SETLIST]({commit},payload){
		mock.payrollTaskSubmitList.then(response =>{
		    commit(PayrollTaskSubmitType.PAYROLLTASKSUBMIT_SETLIST, { list: response.data })
	      }
    	)
	}
}

const getters = {
	payrolltasksubmitlist(){
		console.log(state.payrolltasksubmitlist)
		return state.payrolltasksubmitlist
	}
}

export const payrollTaskSubmitStore = {
  state,
  mutations,
  actions,
  getters
}
