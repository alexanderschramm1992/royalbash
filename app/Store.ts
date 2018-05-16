import { createStore, Store } from "redux";
import combinedReducers from "./reducers/CombinedReducer";
import { Game, Player, SummoningCard, Summoning } from "./model/Game";

export interface StateModel {

    playerId?: string;

    game?: Game;

    gameId?: string;
    loadGameIssued: boolean;

    drawSummoningCardIssued: boolean;

    cardOnPreview?: string;
    summoningOnPreview?: string;
    
    cardToBeSummoned?: string;
    summoningTarget?: string;
    summonCardIssued: boolean;
}

export const store: Store<StateModel> = createStore(
    combinedReducers,
    (window as any).__REDUX_DEVTOOLS_EXTENSION__ &&
        (window as any).__REDUX_DEVTOOLS_EXTENSION__()
);

export function getPlayer(): Player {

    let playerId = store.getState().playerId;

    if (store.getState().game.board.playerBlue.id == playerId) {

        return store.getState().game.board.playerBlue;
    } else {
     
        return store.getState().game.board.playerBlue;
    }
}

export function findCardById(id: string): SummoningCard {

    return store.getState().game.board.playerBlue.hand.summoningCards.find((summoningCard) => {return summoningCard.id == id})
}

export function findSummoningById(id: string): Summoning {

    let playerBlueSummonings = store.getState().game.board.playerBlue.field.targets.map((target) => {return target.summoning});
    let playerRedSummonings = store.getState().game.board.playerRed.field.targets.map((target) => {return target.summoning});
    let summonings = playerBlueSummonings.concat(playerRedSummonings);
    
    return summonings.find((summoning) => {return summoning.id == id});
}

export default store;
