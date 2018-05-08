import store from "../Store";
import axios, {AxiosResponse} from "axios";
import {SummoningAcceptedActionFactory} from "../actions/SummoningAcceptedAction";
import {DrawCardDeclinedActionFactory} from "../actions/DrawCardDeclinedAction";
import { Game } from "../model/Game";

class SummonCardCall {

    constructor () {

        store.subscribe((): void => {

            if(store.getState().summonCardIssued) {

                axios.post(
                    "gameloop/summon",
                    {
                        "playerId": store.getState().playerId,
                        "cardId": store.getState().cardToBeSummoned,
                        "targetId": store.getState().summoningTarget
                    },
                    {
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        }
                    },
                ).then((response: AxiosResponse): void => {

                    store.dispatch(
                        SummoningAcceptedActionFactory.getInstance(response.data)
                    );
                }).catch((reason: string) => {

                    store.dispatch(
                        DrawCardDeclinedActionFactory.getInstance(
                            reason
                        )
                    );
                });
            }
        });
    }
}

export default SummonCardCall;
