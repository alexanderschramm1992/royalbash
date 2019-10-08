import Vue from 'vue'
import Vuex from 'vuex'

import { FETCH_GAMES } from "./MutationTypes.js";

import { get, post } from "./util/AjaxHandler.js";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    games: [],
    game: undefined,
    playerId: undefined
  },
  mutations: { // synchronous
    [FETCH_GAMES](state) {
      get("/game",
        response => state.games = response,
        error => console.error("Cannot fetch Games from Backend due to " + error));
    }
  },
  actions: { // asynchronous
  }
})
