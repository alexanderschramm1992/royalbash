import {AnyAction, Reducer} from "redux";
import {StateModel} from "../Store";
import {MOUSE_OFF_SUMMONING, MOUSE_ON_SUMMONING} from "../actions/ActionTypes";
import MouseOnSummoningAction from "../actions/MouseOnSummoningAction";

const handleMouseOnCard: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch(action.type) {
        case MOUSE_ON_SUMMONING:

            let mouseOnSummoningAction = action as MouseOnSummoningAction;
            console.log("Mouse on summoning " + mouseOnSummoningAction.summoningId);
            return {
                ...state,
                cardOnPreview: null,
                summoningOnPreview: mouseOnSummoningAction.summoningId
            };
        case MOUSE_OFF_SUMMONING:

            console.log("Mouse off summoning");
            return {
                ...state,
                summoningOnPreview: null,
            };
        default:
            return state;
    }
};

export default handleMouseOnCard;