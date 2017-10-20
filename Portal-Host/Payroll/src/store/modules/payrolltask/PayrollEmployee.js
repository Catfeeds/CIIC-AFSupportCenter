import mock from '../../../data/payrolltask/PayrollEmployeeData'
import * as PayrollTaskSubmitDetialType from '../../EventTypes/PayrollTaskSubmitDetialType'
import axios from 'axios'

const state = {
	payrollemployeelist: []
}

const mutations = {
	[PayrollTaskSubmitDetialType.PAYROLLTEMPLOYEE_SETLIST](state,payload){
    console.log(payload)
		state.payrollemployeelist = payload.list.data;
		// console.log(state.payrollemployeelist);
	}
}

const actions = {
	[PayrollTaskSubmitDetialType.PAYROLLTEMPLOYEE_SETLIST]({commit},payload){
		mock.payrollEmployeeList.then(response =>{
		    commit(PayrollTaskSubmitDetialType.PAYROLLTEMPLOYEE_SETLIST, { list: response.data })
	      }
    	)
	}
}

const getters = {
	payrollemployeelist(){
		console.log(state.payrollemployeelist)
		return state.payrollemployeelist
	}
}

export const payrollEmployeeStore = {
  state,
  mutations,
  actions,
  getters
}
