import { createStore, Store } from "redux";
import combinedReducers from "./reducers/CombinedReducer";
import {Game, Player, Summoning, Card} from "./model/Game";

export interface StateModel {

    playerId?: string;

    game?: Game;

    gameId?: string;
    loadGameIssued: boolean;

    drawSummoningCardIssued: boolean;
    drawResourcesCardIssued: boolean;

    cardOnPreview?: string;
    summoningOnPreview?: string;
    
    cardToBeSummoned?: string;
    summoningTarget?: string;
    summonCardIssued: boolean;

    constants: {
        maxRations: number
    };
}

export const store: Store<StateModel> = createStore(
    combinedReducers,
    (window as any).__REDUX_DEVTOOLS_EXTENSION__ &&
        (window as any).__REDUX_DEVTOOLS_EXTENSION__()
);

export function getPlayer(): Player {

    let state = store.getState();

    let playerId = state.playerId;

    if (state.game.board.playerBlue.id == playerId) {

        return state.game.board.playerBlue;
    } else {
     
        return state.game.board.playerBlue;
    }
}

export function findCardById(id: string): Card {

    return store.getState().game.board.playerBlue.hand.cards.find((summoningCard) => {return summoningCard.id == id})
}

export function findSummoningById(id: string): Summoning {

    let playerBlueSummonings = store.getState().game.board.playerBlue.field.targets
        .filter(target => target.summoning)
        .map(target => target.summoning);
    let playerRedSummonings = store.getState().game.board.playerRed.field.targets
        .filter(target => target.summoning)
        .map(target => target.summoning);
    let summonings = playerBlueSummonings.concat(playerRedSummonings);
    
    return summonings.find(summoning => summoning.id == id);
}

export default store;
