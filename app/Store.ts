import { createStore, Store } from "redux";
import combinedReducers from "./reducers/CombinedReducer";
import { CardModel } from "./components/game/card/Card";
import { SummoningModel } from "./components/game/summoning/Summoning";

export interface StateModel {

    playerId: string;
    drawCardIssued: boolean;
    hand: Array<string>;

    cardOnPreview: string;
    summoningOnPreview: string;
    
    cardToBeSummoned: string;
    summoningTarget: string;
    summonCardIssued: boolean;

    cardModels: Array<CardModel>;
    summoningModels: Array<SummoningModel>;
}

export const store: Store<StateModel> = createStore(
    combinedReducers,
    (window as any).__REDUX_DEVTOOLS_EXTENSION__ &&
        (window as any).__REDUX_DEVTOOLS_EXTENSION__()
);

export function findCardModelById(id: string): CardModel {

    return store.getState().cardModels.filter(
        (cardModel: CardModel): boolean => {
            return cardModel.id == id;
        }
    )[0];
}

export function findSummoningModelById(id: string): SummoningModel {
    
    return store.getState().summoningModels.filter(
        (summoningModel: SummoningModel): boolean => {
            return summoningModel.id == id;
        }
    )[0];
}

export default store;
