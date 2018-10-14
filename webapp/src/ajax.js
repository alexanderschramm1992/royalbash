import axios from "axios/index";
import {Store} from "./store";

export default function registerAjaxController() {
    requestGame();
}

function requestGame() {
    Store.subscribe(() => {
        console.log("Subscription fired");
        if(Store.getState().gameRequested) {
            axios.get("/game/" + Store.getState().gameId)
                .then((response) => Store.dispatch({type: "LOAD_GAME_ACCEPTED", game: response.data}))
                .catch((error) => Store.dispatch({type: "LOAD_GAME_DECLINED", errorMessage: error.response.data}));
        } else if(Store.getState().gameIdsRequested) {
            axios.get("/game/id")
                .then((response) => Store.dispatch({type: "LOAD_GAME_IDS_ACCEPTED", gameIds: response.data}))
                .catch((error) => Store.dispatch({type: "LOAD_GAME_IDS_DECLINED", errorMessage: error.response.data}))
        } else {
            console.log("Something happened that does not concern ajax.js");
        }
    });
}
