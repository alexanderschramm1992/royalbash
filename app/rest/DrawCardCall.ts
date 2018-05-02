import {CardModel} from "../components/game/card/Card";
import EventBus from "../events/EventBus";
import CardDrawnEvent from "../events/CardDrawnEvent";
import store from "../Store";
import axios, {AxiosResponse} from "axios";
import DrawCardAcceptedAction from "../actions/DrawCardAcceptedAction";
import DrawCardDeclinedAction from "../actions/DrawCardDeclinedAction";

class DrawCardCall {

    constructor () {

        store.subscribe(() => {
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

                    store.dispatch(new DrawCardAcceptedAction(
                        response.data.cardId
                    ));                    
                }).catch((reason: string) => {

                    store.dispatch(new DrawCardDeclinedAction(
                        reason
                    ));
                });
            }
        });
    }
}

export default DrawCardCall;
