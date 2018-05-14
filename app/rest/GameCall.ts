import store from "../Store";
import axios, {AxiosResponse} from "axios";
import { DrawCardAcceptedActionFactory } from "../actions/DrawCardAcceptedAction";
import { DrawCardDeclinedActionFactory } from "../actions/DrawCardDeclinedAction";

class GameCall {

    constructor () {

        store.subscribe((): void => {

            if(store.getState().loadGameIssued) {

                axios.post(
                    "gameloop/draw",
                    {
                        "gameId": "6d5864f4-5fb1-4615-bf6a-07a1211ef6d3",
                    },
                    {
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        }
                    },
                ).then((response: AxiosResponse): void => {

                    store.dispatch(
                        DrawCardAcceptedActionFactory.getInstance(response.data)
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

export default GameCall;
