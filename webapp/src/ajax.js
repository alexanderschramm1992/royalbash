import axios from "axios/index";
import {Store} from "./store";
import {getChosenPlayer} from "./storeutil";

export default function registerAjaxController() {
    requestGame();
}

function doLoadGameCall() {
    axios.get("/game/" + Store.getState().gameId)
        .then((response) => Store.dispatch({type: "LOAD_GAME_ACCEPTED", game: response.data}))
        .catch((error) => Store.dispatch({type: "LOAD_GAME_DECLINED", errorMessage: error}));
}

function doLoadGameIdsCall() {
    axios.get("/game/id")
        .then((response) => Store.dispatch({type: "LOAD_GAME_IDS_ACCEPTED", gameIds: response.data}))
        .catch((error) => Store.dispatch({type: "LOAD_GAME_IDS_DECLINED", errorMessage: error}))
}

function doEndTurnCall() {
    axios.post("/game/" + Store.getState().gameId + "/event", {
        event: {
            type: "TURN_ENDED",
            playerId: getChosenPlayer(Store).id
        }
    })
        .then((response) => Store.dispatch({type: "END_TURN_ACCEPTED", game: response.data}))
        .catch((error) => Store.dispatch({type: "END_TURN_DECLINED", errorMessage: error}));
}

function doDrawCardCall() {
    axios.post("/game/" + Store.getState().gameId + "/event", {
        event: {
            type: "CARD_DRAWN",
            playerId: getChosenPlayer(Store).id,
            amountOfCards: 1
        }
    })
        .then((response) => Store.dispatch({type: "DRAW_CARD_ACCEPTED", game: response.data}))
        .catch((error) => Store.dispatch({type: "DRAW_CARD_DECLINED", errorMessage: error}));
}

function doPlayCardOnPlayerCall() {
    let playCardInformation = Store.getState().playCard;
    axios.post("/game/" + Store.getState().gameId + "/event", {
        event: {
            type: "CARD_PLAYED_ON_PLAYER",
            cardId: playCardInformation.id,
            ownerId: playCardInformation.ownerId,
            targetPlayerId: playCardInformation.target.playerId
        }
    })
        .then((response) => Store.dispatch({type: "PLAY_CARD_ON_PLAYER_ACCEPTED", game: response.data}))
        .catch((error) => Store.dispatch({type: "PLAY_CARD_ON_PLAYER_DECLINED", errorMessage: error}));
}

function doPlayCardOnSpotCall() {
    let playCardInformation = Store.getState().playCard;
    axios.post("/game/" + Store.getState().gameId + "/event", {
        event: {
            type: "CARD_PLAYED_ON_SPOT",
            cardId: playCardInformation.id,
            ownerId: playCardInformation.ownerId,
            targetSpotId: playCardInformation.target.spotId
        }
    })
        .then((response) => Store.dispatch({type: "PLAY_CARD_ON_SPOT_ACCEPTED", game: response.data}))
        .catch((error) => Store.dispatch({type: "PLAY_CARD_ON_SPOT_DECLINED", errorMessage: error}));
}

function doPlayCardOnCreatureCall() {
    let playCardInformation = Store.getState().playCard;
    axios.post("/game/" + Store.getState().gameId + "/event", {
        event: {
            type: "CARD_PLAYED_ON_CREATURE",
            cardId: playCardInformation.id,
            ownerId: playCardInformation.ownerId,
            targetCreatureId: playCardInformation.target.creatureId
        }
    })
        .then((response) => Store.dispatch({type: "PLAY_CARD_ON_CREATURE_ACCEPTED", game: response.data}))
        .catch((error) => Store.dispatch({type: "PLAY_CARD_ON_CREATURE_DECLINED", errorMessage: error}));
}

function requestGame() {
    Store.subscribe(() => {
        let state = Store.getState();
        if(state.gameRequested) {
            doLoadGameCall();
        } else if(state.gameIdsRequested) {
            doLoadGameIdsCall();
        } else if(state.endTurnRequested) {
            doEndTurnCall();
        } else if(state.drawCardRequested) {
            doDrawCardCall();
        } else if(state.playCard.requested && state.playCard.target.player) {
            doPlayCardOnPlayerCall();
        } else if(state.playCard.requested && state.playCard.target.spot) {
            doPlayCardOnSpotCall();
        } else if(state.playCard.requested && state.playCard.target.creature) {
            doPlayCardOnCreatureCall();
        } else {
            console.log("Something happened that does not concern ajax.js");
        }
    });
}
