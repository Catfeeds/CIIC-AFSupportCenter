import mock from '../../../data/payrolldatasource/PayrollDataSourceImportData'
import * as PayrollDataSourceImportType from '../../EventTypes/PayrollDataSourceImportType'
import axios from 'axios'

const state = {
	payrolldatasourceimportlist: []
}

const mutations = {
	[PayrollDataSourceImportType.PAYROLLDATASOURCEIMPORT_SETLIST](state,payload){
    console.log(payload)
		state.payrolldatasourceimportlist = payload.list.data;
		// console.log(state.payrolldatasourceimportlist);
	}
}

const actions = {
	[PayrollDataSourceImportType.PAYROLLDATASOURCEIMPORT_SETLIST]({commit},payload){
		mock.payrollDataSourceImportList.then(response =>{
		    commit(PayrollDataSourceImportType.PAYROLLDATASOURCEIMPORT_SETLIST, { list: response.data })
	      }
    	)
	}
}

const getters = {
	payrolldatasourceimportlist(){
		console.log(state.payrolldatasourceimportlist)
		return state.payrolldatasourceimportlist
	}
}

export const payrollDataSourceImportStore = {
  state,
  mutations,
  actions,
  getters
}
