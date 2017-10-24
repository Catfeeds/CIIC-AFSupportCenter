import mock from '../../data/LeftNavigationData'
import EventTypes from '../EventTypes'

const state = {
	leftnavigationlist: []
}

const mutations = {
	[EventTypes.LEFTNAVIGATION_SETLIST](state,payload){
    console.log(payload)
		state.leftnavigationlist = payload.list.data;
		// console.log(state.leftnavigationlist);
	}
}

const actions = {
	[EventTypes.LEFTNAVIGATION_SETLIST]({commit},payload){
		mock.leftNavigationList.then(response =>{
		    commit(EventTypes.LEFTNAVIGATION_SETLIST, { list: response.data })
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

const namespaced = true;

export default {
  namespaced: namespaced,
  state,
  mutations,
  actions,
  getters
}

