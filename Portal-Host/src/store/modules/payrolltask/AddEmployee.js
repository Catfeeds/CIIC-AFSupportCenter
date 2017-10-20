import mock from '../../../data/payrolltask/AddEmployeeData'
import * as PayrollTaskSubmitDetialType from '../../EventTypes/PayrollTaskSubmitDetialType'
import axios from 'axios'

const state = {
	addemployeelist: []
}

const mutations = {
	[PayrollTaskSubmitDetialType.PAYROLLTADDEMPLOYEE_SETLIST](state,payload){
    console.log(payload)
		state.addemployeelist = payload.list.data;
		// console.log(state.payrollemployeelist);
	}
}

const actions = {
	[PayrollTaskSubmitDetialType.PAYROLLTADDEMPLOYEE_SETLIST]({commit},payload){
		mock.addEmployeeList.then(response =>{
		    commit(PayrollTaskSubmitDetialType.PAYROLLTADDEMPLOYEE_SETLIST, { list: response.data })
	      }
    	)
	}
}

const getters = {
	addemployeelist(){
		console.log(state.addemployeelist)
		return state.addemployeelist
	}
}

export const addEmployeeStore = {
  state,
  mutations,
  actions,
  getters
}
