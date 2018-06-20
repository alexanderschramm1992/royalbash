import {
    GATHERING_RESOURCES_ISSUED,
    GATHERING_RESOURCES_ACCEPTED,
    GATHERING_RESOURCES_DECLINED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import Game from "../model/Game";
import GatheringResourcesIssuedAction from "../actions/GatheringResourcesIssuedAction";
import GatheringResourcesDeclinedAction from "../actions/GatheringResourcesDeclinedAction";

const handleGatheringResources: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case GATHERING_RESOURCES_ISSUED:

            let summoningIssuedAction = action as GatheringResourcesIssuedAction;
            console.log("Gathering resources issued");
            return {
                ...state,
                gatheringResourceIssued: true,
                cardDragged: summoningIssuedAction.cardId
            };
        case GATHERING_RESOURCES_ACCEPTED:

            let game = action.game.game as Game;
            console.log("Gathering resources accepted");
            return {
                ...state,
                game: game,
                gatheringResourceIssued: false,
                cardDragged: null
            };
        case GATHERING_RESOURCES_DECLINED:

            let gatherResourcesDeclinedAction = action as GatheringResourcesDeclinedAction;
            console.log("Gathering resources declined because: " + gatherResourcesDeclinedAction.reason);
            return {
                ...state,
                gatheringResourceIssued: false,
                cardDragged: null
            };
        default:
            return state;
    }
};

export default handleGatheringResources;
