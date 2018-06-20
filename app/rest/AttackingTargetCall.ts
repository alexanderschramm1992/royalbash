import store from "../Store";
import axios, {AxiosResponse} from "axios";
import {AttackingAcceptedActionFactory} from "../actions/AttackingTargetAcceptedAction";
import {AttackingDeclinedActionFactory} from "../actions/AttackingTargetDeclinedAction";
import {AttackingTargetProcessingActionFactory} from "../actions/AttackingTargetProcessingAction";

class AttackingTargetCall {

    constructor () {
        store.subscribe((): void => {
            if(store.getState().attackingTargetIssued && !store.getState().attackingTargetProcessing) {

                store.dispatch(AttackingTargetProcessingActionFactory.getInstance());
                axios.post(
                    "gameloop/attackingTarget",
                    {
                        "gameId": store.getState().gameId,
                        "playerId": store.getState().playerId,
                        "attackingSummoningId": store.getState().summoningDragged,
                        "attackedTargetId": store.getState().attackedTarget
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

export default AttackingTargetCall;
