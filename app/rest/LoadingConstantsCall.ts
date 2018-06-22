import store from "../Store";
import axios, {AxiosResponse} from "axios";
import {LoadingConstantsAcceptedActionFactory} from "../actions/loadingconstants/LoadingConstantsAcceptedAction";
import {LoadingConstantsDeclinedActionFactory} from "../actions/loadingconstants/LoadingConstantsDeclinedAction";

class LoadingConstantsCall {

    constructor () {

        store.subscribe((): void => {

            if(store.getState().loadGameIssued) {

                axios.get(
                    "constants",
                    {
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        }
                    },
                ).then((response: AxiosResponse): void => {

                    store.dispatch(
                        LoadingConstantsAcceptedActionFactory.getInstance(response.data.constants)
                    );
                }).catch((reason: string) => {

                    store.dispatch(
                        LoadingConstantsDeclinedActionFactory.getInstance(reason)
                    );
                });
            }
        });
    }
}

export default LoadingConstantsCall;
