import store from "../Store";
import axios, {AxiosResponse} from "axios";
import {LoadGameAcceptedActionFactory} from "../actions/LoadGameAcceptedAction";
import {LoadGameDeclinedActionFactory} from "../actions/LoadGameDeclinedAction";

class LoadGameCall {

    constructor () {

        store.subscribe((): void => {

            if(store.getState().loadGameIssued) {

                axios.post(
                    "game",
                    {
                        "gameId": store.getState().gameId,
                    },
                    {
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        }
                    },
                ).then((response: AxiosResponse): void => {

                    store.dispatch(
                        LoadGameAcceptedActionFactory.getInstance(response.data.game)
                    );
                }).catch((reason: string) => {

                    store.dispatch(
                        LoadGameDeclinedActionFactory.getInstance(reason)
                    );
                });
            }
        });
    }
}

export default LoadGameCall;
