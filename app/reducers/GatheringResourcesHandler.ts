import {
    GATHERING_RESOURCES_ISSUED,
    GATHERING_RESOURCES_ACCEPTED,
    GATHERING_RESOURCES_DECLINED,
    RESOURCES_CARD_DRAGGED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import Game from "../model/Game";
import GatheringResourcesIssuedAction from "../actions/GatheringResourcesIssuedAction";
import GatheringResourcesDeclinedAction from "../actions/GatheringResourcesDeclinedAction";
import ResourcesCardDraggedAction from "../actions/ResourcesCardDraggedAction";

const handleGatheringResources: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case RESOURCES_CARD_DRAGGED:

            let cardDraggedAction = action as ResourcesCardDraggedAction;
            console.log("Resources card " + cardDraggedAction.cardId + " is dragged");
            return {
                ...state,
                resourcesCardDragged: cardDraggedAction.cardId
            };
        case GATHERING_RESOURCES_ISSUED:

            let gatheringResourcesIssuedAction = action as GatheringResourcesIssuedAction;
            console.log("Gathering resources issued");
            return {
                ...state,
                gatheringResourceIssued: true,
                resourcesCardDragged: gatheringResourcesIssuedAction.cardId
            };
        case GATHERING_RESOURCES_ACCEPTED:

            let game = action.game.game as Game;
            console.log("Gathering resources accepted");
            return {
                ...state,
                game: game,
                gatheringResourceIssued: false,
                resourcesCardDragged: null
            };
        case GATHERING_RESOURCES_DECLINED:

            let gatherResourcesDeclinedAction = action as GatheringResourcesDeclinedAction;
            console.log("Gathering resources declined because: " + gatherResourcesDeclinedAction.reason);
            return {
                ...state,
                gatheringResourceIssued: false,
                resourcesCardDragged: null
            };
        default:
            return state;
    }
};

export default handleGatheringResources;
