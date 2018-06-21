import store from "../Store";
import axios, {AxiosResponse} from "axios";
import {DrawResourcesCardAcceptedActionFactory} from "../actions/drawingresourcescard/DrawResourcesCardAcceptedAction";
import {DrawResourcesCardDeclinedActionFactory} from "../actions/drawingresourcescard/DrawResourcesCardDeclinedAction";

class DrawCardCall {

    constructor () {

        store.subscribe((): void => {

            if(store.getState().drawResourcesCardIssued) {

                axios.post(
                    "gameloop/drawResourcesCard",
                    {
                        "gameId": store.getState().game.id,
                        "playerId": store.getState().playerId
                    },
                    {
                        headers: {
                            "Content-Type": "application/json; charset=utf-8"
                        }
                    },
                ).then((response: AxiosResponse): void => {

                    store.dispatch(
                        DrawResourcesCardAcceptedActionFactory.getInstance(
                            response.data.game
                        )
                    );
                }).catch((reason: string) => {

                    store.dispatch(
                        DrawResourcesCardDeclinedActionFactory.getInstance(
                            reason
                        )
                    );
                });
            }
        });
    }
}

export default DrawCardCall;
