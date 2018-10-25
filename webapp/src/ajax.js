import axios from "axios/index";
import {Store} from "./store";
import {getChosenPlayer} from "./storeutil";

export default function registerAjaxController() {
    requestGame();
}

function requestGame() {
    Store.subscribe(() => {
        if(Store.getState().gameRequested) {
            axios.get("/game/" + Store.getState().gameId)
                .then((response) => Store.dispatch({type: "LOAD_GAME_ACCEPTED", game: response.data}))
                .catch((error) => Store.dispatch({type: "LOAD_GAME_DECLINED", errorMessage: error}));
        } else if(Store.getState().gameIdsRequested) {
            axios.get("/game/id")
                .then((response) => Store.dispatch({type: "LOAD_GAME_IDS_ACCEPTED", gameIds: response.data}))
                .catch((error) => Store.dispatch({type: "LOAD_GAME_IDS_DECLINED", errorMessage: error}))
        } else if(Store.getState().endTurnRequested) {
            axios.post("/game/" + Store.getState().gameId + "/event", {
                event: {
                    type: "TURN_ENDED",
                    playerId: getChosenPlayer(Store).id
                }
            })
                .then((response) => Store.dispatch({type: "END_TURN_ACCEPTED", game: response.data}))
                .catch((error) => Store.dispatch({type: "END_TURN_DECLINED", errorMessage: error}));
        } else if(Store.getState().drawCardRequested) {
            axios.post("/game/" + Store.getState().gameId + "/event", {
                event: {
                    type: "CARD_DRAWN",
                    playerId: getChosenPlayer(Store).id
                }
            })
                .then((response) => Store.dispatch({type: "DRAW_CARD_ACCEPTED", game: response.data}))
                .catch((error) => Store.dispatch({type: "DRAW_CARD_DECLINED", errorMessage: error}));
        } else {
            console.log("Something happened that does not concern ajax.js");
        }
    });
}
