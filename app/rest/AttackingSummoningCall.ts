import store from "../Store";
import axios, {AxiosResponse} from "axios";
import {AttackingAcceptedActionFactory} from "../actions/AttackingAcceptedAction";
import {AttackingDeclinedActionFactory} from "../actions/AttackingDeclinedAction";

class AttackSummoningCall {

    constructor () {
        store.subscribe((): void => {
            if(store.getState().summonCardIssued) {
                axios.post(
                    "gameloop/attackSummoning",
                    {
                        "gameId": store.getState().gameId,
                        "playerId": store.getState().playerId,
                        "attackingSummoning": store.getState().attackingSummoning,
                        "attackedSummoning": store.getState().attackedSummoning
                    },
                    {
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        }
                    },
                ).then((response: AxiosResponse): void => {
                    store.dispatch(AttackingAcceptedActionFactory.getInstance(response.data));
                }).catch((reason: string) => {
                    store.dispatch(AttackingDeclinedActionFactory.getInstance(reason));
                });
            }
        });
    }
}

export default AttackSummoningCall;
