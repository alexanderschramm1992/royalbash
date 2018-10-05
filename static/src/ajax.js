import axios from "axios/index";
import {Store} from "./store";

export default function registerAjaxController() {
    requestGame();
}

function requestGame() {
    Store.subscribe(() => {
        if(Store.getState().gameRequested) {
            axios.get("/game/" + Store.getState().gameId)
                .then((response) => Store.dispatch({type: "LOAD_GAME_ACCEPTED", game: response.data}))
                .catch((error) => Store.dispatch({type: "LOAD_GAME_DECLINED", errorMessage: error}));
        } else if(Store.getState.gameIdsRequested) {
            axios.get("/game")
                .then((response) => Store.dispatch({type: "LOAD_GAME_IDS_ACCEPTED", gameIds: response.data}))
                .catch((error) => Store.dispatch({type: "LOAD_GAME_IDS_DECLINED", errorMessage: error}))
        }
    });
}
