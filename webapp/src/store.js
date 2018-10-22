import { createStore } from 'redux';

const initialState = {
    gameRequested: false,
    gameIdsRequested: false,
    endTurnRequested: false,
    drawCardRequested: false,
    gameIds: [],
    game: {},
    chosenPlayer: "player1"
};

function storeModel(state = initialState, action) {
    switch (action.type) {
        case "LOAD_GAME_REQUESTED":
            console.debug("Load Game requested");
            state.gameRequested = true;
            state.gameId = action.gameId;
            break;
        case "LOAD_GAME_ACCEPTED":
            state.gameRequested = false;
            state.game = action.game;
            break;
        case "LOAD_GAME_DECLINED":
            state.gameRequested = false;
            state.errorMessage = action.errorMessage;
            break;
        case "LOAD_GAME_IDS_REQUESTED":
            state.gameIdsRequested = true;
            break;
        case "LOAD_GAME_IDS_ACCEPTED":
            state.gameIdsRequested = false;
            state.gameIds = action.gameIds;
            break;
        case "LOAD_GAME_IDS_DECLINED":
            state.gameIdsRequested = false;
            state.errorMessage = action.errorMessage;
            break;
        case "PLAYER_CHOSEN":
            state.chosenPlayer = action.chosenPlayerKey;
            break;
        case "END_TURN_REQUESTED":
            state.endTurnRequested = true;
            break;
        case "END_TURN_ACCEPTED":
            state.endTurnRequested = false;
            state.game = action.game;
            break;
        case "END_TURN_DECLINED":
            state.endTurnRequested = false;
            state.errorMessage = action.errorMessage;
            break;
        case "DRAW_CARD_REQUESTED":
            state.drawCardRequested = true;
            break;
        case "DRAW_CARD_ACCEPTED":
            state.drawCardRequested = false;
            state.game = action.game;
            break;
        case "DRAW_CARD_DECLINED":
            state.drawCardRequested = false;
            state.errorMessage = action.errorMessage;
            break;
    }
    return state;
}

export const Store = createStore(
    storeModel,
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);
