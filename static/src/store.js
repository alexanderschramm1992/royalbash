import { createStore } from 'redux';

const initialState = {
    gameIds: []
};

function storeModel(state = initialState, action) {
    switch (action.type) {
        case "LOAD_GAME_REQUESTED":
            console.debug("Load Game requested");
            state.gameRequested = true;
            state.gameId = action.gameId;
            return state;
        case "LOAD_GAME_ACCEPTED":
            state.gameRequested = false;
            state.game = action.game;
            return state;
        case "LOAD_GAME_DECLINED":
            state.gameRequested = false;
            state.errorMessage = action.errorMessage;
            return state;
        case "LOAD_GAME_IDS_REQUESTED":
            state.gameIdsRequested = true;
            return state;
        case "LOAD_GAME_IDS_ACCEPTED":
            state.gameIdsRequested = false;
            state.gameIds = action.gameIds;
            return state;
        case "LOAD_GAME_IDS_DECLINED":
            state.gameIdsRequested = false;
            state.errorMessage = action.errorMessage;
            return state;
        default:
            return state;
    }
}

export const Store = createStore(
    storeModel,
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);
