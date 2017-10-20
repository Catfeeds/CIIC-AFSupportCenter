import mock from '../../data/LeftNavigationData'
import * as LeftNavigationType from '../EventTypes/LeftNavigationType'
import axios from 'axios'

const state = {
	leftnavigationlist: []
}

const mutations = {
	[LeftNavigationType.LEFTNAVIGATION_SETLIST](state,payload){
    console.log(payload)
		state.leftnavigationlist = payload.list.data;
		// console.log(state.leftnavigationlist);
	}
}

const actions = {
	[LeftNavigationType.LEFTNAVIGATION_SETLIST]({commit},payload){
		mock.leftNavigationList.then(response =>{
		    commit(LeftNavigationType.LEFTNAVIGATION_SETLIST, { list: response.data })
	      }
    	)
	}
}

const getters = {
	leftNavigationList(){
		console.log(state.leftnavigationlist)
		return state.leftnavigationlist
	}
}

export const leftNaviStore = {
  state,
  mutations,
  actions,
  getters
}
