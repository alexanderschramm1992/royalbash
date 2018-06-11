import { createStore, Store } from "redux";
import combinedReducers from "./reducers/CombinedReducer";
import {Game, Player, Summoning, Card} from "./model/Game";

export interface StateModel {

    playerId?: string;
    enemyId?: string;

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

    attackingSummoning?: string;
    attackedSummoning?: string;
    attackingSummoningIssued: boolean;

    summoningToAttack?: string;

    constants: {
        maxRations: number,
        maxMaterial: number,
        maxBlessing: number
    };
}

export const store: Store<StateModel> = createStore(
    combinedReducers,
    (window as any).__REDUX_DEVTOOLS_EXTENSION__ &&
        (window as any).__REDUX_DEVTOOLS_EXTENSION__()
);

export function getPlayer(): Player {

    let state = store.getState();

    if (state.game.board.playerBlue.id == state.playerId) {

        return state.game.board.playerBlue;
    } else {
     
        return state.game.board.playerRed;
    }
}

export function getEnemyPlayer(): Player {

    let state = store.getState();

    if (state.game.board.playerBlue.id == state.enemyId) {

        return state.game.board.playerBlue;
    } else {

        return state.game.board.playerRed;
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
