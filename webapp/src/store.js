import { createStore } from 'redux';

const initialState = {
    gameRequested: false,
    gameIdsRequested: false,
    endTurnRequested: false,
    drawCardRequested: false,
    playCardOnPlayerRequested: false,
    playCardOnSpotRequested: false,
    playCard: {
        id: "",
        ownerId: "",
        target: {
            playerId: "",
            spotId: "",
            creatureId: ""
        }
    },
    gameLoaded: false,
    onTurn: false,
    gameIds: [],
    game: {},
    chosenPlayer: "player1",
    chosenPlayerId: ""
};

function storeModel(state = initialState, action) {
    state = choosePlayer(state, action);
    state = loadGame(state, action);
    state = loadGameIds(state, action);
    state = endTurn(state, action);
    state = drawCard(state, action);
    state = playCardOnPlayer(state, action);
    state = playCardOnSpot(state, action);
    return state;
}

export function loadGame(state, action) {
    switch (action.type) {
        case "LOAD_GAME_REQUESTED":
            state.gameId = action.gameId;
            state.gameRequested = true;
            state.gameLoaded = false;
            return state;
        case "LOAD_GAME_ACCEPTED":
            state.game = action.game;
            state.chosenPlayerId = state.game[state.chosenPlayer].id;
            state.onTurn = state.game.playerOnTurn === state.chosenPlayerId;
            state.gameRequested = false;
            state.gameLoaded = true;
            return state;
        case "LOAD_GAME_DECLINED":
            state.gameRequested = false;
            state.gameLoaded = false;
            state.errorMessage = action.errorMessage;
            return state;
        default:
            return state;
    }
}

export function loadGameIds(state, action) {
    switch (action.type) {
        case "LOAD_GAME_IDS_REQUESTED":
            state.gameIdsRequested = true;
            return state;
        case "LOAD_GAME_IDS_ACCEPTED":
            state.gameIds = action.gameIds;
            state.gameIdsRequested = false;
            return state;
        case "LOAD_GAME_IDS_DECLINED":
            state.gameIdsRequested = false;
            state.errorMessage = action.errorMessage;
            return state;
        default:
            return state;
    }
}

export function endTurn(state, action) {
    switch (action.type) {
        case "END_TURN_REQUESTED":
            state.endTurnRequested = true;
            return state;
        case "END_TURN_ACCEPTED":
            state.endTurnRequested = false;
            state.game = action.game;
            state.onTurn = false;
            return state;
        case "END_TURN_DECLINED":
            state.endTurnRequested = false;
            state.errorMessage = action.errorMessage;
            return state;
        default:
            return state;
    }
}

export function drawCard(state, action) {
    switch (action.type) {
        case "DRAW_CARD_REQUESTED":
            state.drawCardRequested = true;
            return state;
        case "DRAW_CARD_ACCEPTED":
            state.drawCardRequested = false;
            state.game = action.game;
            return state;
        case "DRAW_CARD_DECLINED":
            state.drawCardRequested = false;
            state.errorMessage = action.errorMessage;
            return state;
        default:
            return state;
    }
}

export function playCardOnPlayer(state, action) {
    let reset = {
        ...state,
        playCardOnPlayerRequested: false,
        playCard : {
            id: "",
            ownerId: "",
            target: {
                playerId: ""
            }
        }
    };
    switch (action.type) {
        case "PLAY_CARD_ON_PLAYER_REQUESTED":
            state.playCard = {
                requested: true,
                id: action.cardId,
                ownerId: action.ownerId,
                target: {
                    playerId: action.playerId
                }
            };
            return state;
        case "PLAY_CARD_ON_PLAYER_ACCEPTED" :
            state = reset;
            state.game = action.game;
            return state;
        case "PLAY_CARD_ON_PLAYER_DECLINED":
            state = reset;
            state.errorMessage = action.errorMessage;
            return state;
        default:
            return state;
    }
}

export function playCardOnSpot(state, action) {
    let reset = {
        ...state,
        playCardOnSpotRequested: false,
        playCard : {
            id: "",
            ownerId: "",
            target: {
                spotId: ""
            }
        }
    };
    switch (action.type) {
        case "PLAY_CARD_ON_SPOT_REQUESTED":
            state.playCard = {
                requested: true,
                id: action.cardId,
                ownerId: action.ownerId,
                target: {
                    spotId: action.spotId
                }
            };
            return state;
        case "PLAY_CARD_ON_SPOT_ACCEPTED":
            state = reset;
            state.game = action.game;
            return state;
        case "PLAY_CARD_ON_SPOT_DECLINED":
            state = reset;
            state.errorMessage = action.errorMessage;
            return state;
        default:
            return state;
    }
}

export function choosePlayer(state, action) {
    switch (action.type) {
        case "PLAYER_CHOSEN":
            state.chosenPlayer = action.player;
            return state;
        default:
            return state;
    }
}

export const Store = createStore(
    storeModel,
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);
