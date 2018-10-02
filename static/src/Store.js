import { createStore } from 'redux';

function storeModel(state = {}, action) {
    switch (action.type) {
        case GAME_REQUESTED:
            state.gameId = action.gameId;
            return state;
        default:
            return state;
    }
}

export let Store = createStore(storeModel);