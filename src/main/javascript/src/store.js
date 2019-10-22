import Vue from 'vue'
import Vuex from 'vuex'

import {get, post} from "./util/AjaxHandler.js";
import {COMMIT_EVENT, FETCH_GAMES, SET_GAME, SET_PLAYER_ID} from "./MutationTypes.js";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        games: [],
        game: undefined,
        playerId: undefined,
        focus: undefined
    },
    mutations: { // synchronous
        [FETCH_GAMES](state) {
            get("/game",
                response => state.games = response,
                error => console.error("Cannot fetch Games from Backend due to " + error));
        },
        [SET_PLAYER_ID](state, playerId) {
            state.playerId = playerId
        },
        [SET_GAME](state, game) {
            state.game = game
        },
        [COMMIT_EVENT](state, event) {
            post(`/game/${state.game.id}/event`, { event: event },
                response => state.game = response,
                error => console.error(`Cannot commit 'placeCreature' to Backend due to ${error}`)
            );
        }
    },
    actions: { // asynchronous
    }
})
