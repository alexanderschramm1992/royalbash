import store from "../Store";
import axios, {AxiosResponse} from "axios";
import { DrawCardAcceptedActionFactory } from "../actions/DrawCardAcceptedAction";
import { DrawCardDeclinedActionFactory } from "../actions/DrawCardDeclinedAction";

class DrawCardCall {

    constructor () {

        store.subscribe((): void => {

            if(store.getState().drawCardIssued) {

                axios.post(
                    "gameloop/draw",
                    {
                        "playerId": store.getState().playerId
                    },
                    {
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        }
                    },
                ).then((response: AxiosResponse): void => {

                    store.dispatch(
                        DrawCardAcceptedActionFactory.getInstance(
                            response.data
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

export default DrawCardCall;
