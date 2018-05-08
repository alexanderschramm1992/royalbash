import store from "../Store";
import axios, {AxiosResponse} from "axios";
import {SummoningAcceptedActionFactory} from "../actions/SummoningAcceptedAction";
import {DrawCardDeclinedActionFactory} from "../actions/DrawCardDeclinedAction";

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

                    let data: {summoningId: string, targetId: string} = response.data;

                    store.dispatch(
                        SummoningAcceptedActionFactory.getInstance(
                            data.summoningId,
                            data.targetId
                        )
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
