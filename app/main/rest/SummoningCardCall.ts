import store from "../Store";
import axios, {AxiosResponse} from "axios";
import {SummoningAcceptedActionFactory} from "../actions/summoning/SummoningAcceptedAction";
import {SummoningDeclinedActionFactory} from "../actions/summoning/SummoningDeclinedAction";

class SummoningCardCall {

    constructor () {

        store.subscribe((): void => {

            if(store.getState().summonCardIssued) {
                axios.post(
                    "gameloop/summon",
                    {
                        "gameId": store.getState().gameId,
                        "playerId": store.getState().playerId,
                        "cardId": store.getState().summoningCardDragged,
                        "targetId": store.getState().summoningTarget
                    },
                    {
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        }
                    },
                ).then((response: AxiosResponse): void => {
                    store.dispatch(SummoningAcceptedActionFactory.getInstance(response.data));
                }).catch((reason: string) => {
                    store.dispatch(SummoningDeclinedActionFactory.getInstance(reason));
                });
            }
        });
    }
}

export default SummoningCardCall;
