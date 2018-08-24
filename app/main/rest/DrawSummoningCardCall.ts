import store from "../Store";
import axios, {AxiosResponse} from "axios";
import { DrawSummoningCardAcceptedActionFactory } from "../actions/drawingsummoningcard/DrawSummoningCardAcceptedAction";
import { DrawSummoningCardDeclinedActionFactory } from "../actions/drawingsummoningcard/DrawSummoningCardDeclinedAction";

class DrawCardCall {

    constructor () {

        store.subscribe((): void => {

            if(store.getState().drawSummoningCardIssued) {

                axios.post(
                    "gameloop/drawSummoningCard",
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
                        DrawSummoningCardAcceptedActionFactory.getInstance(response.data.game)
                    );
                }).catch((reason: string) => {

                    store.dispatch(
                        DrawSummoningCardDeclinedActionFactory.getInstance(
                            reason
                        )
                    );
                });
            }
        });
    }
}

export default DrawCardCall;
