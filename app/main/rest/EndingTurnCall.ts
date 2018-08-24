import store from "../Store";
import axios, {AxiosResponse} from "axios";
import {EndingTurnAcceptedActionFactory} from "../actions/endingturn/EndingTurnAcceptedAction";
import {EndingTurnDeclinedActionFactory} from "../actions/endingturn/EndingTurnDeclinedAction";

class EndingTurnCall {

    constructor () {

        store.subscribe((): void => {

            if(store.getState().endingTurnIssued) {
                axios.post(
                    "gameloop/endingturn",
                    {
                        "gameId": store.getState().gameId,
                        "playerId": store.getState().playerId
                    },
                    {
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        }
                    },
                ).then((response: AxiosResponse): void => {
                    store.dispatch(
                        EndingTurnAcceptedActionFactory.getInstance(response.data.game)
                    );
                }).catch((reason: string) => {
                    store.dispatch(
                        EndingTurnDeclinedActionFactory.getInstance(reason)
                    );
                });
            }
        });
    }
}

export default EndingTurnCall;
