import { DRAW_CARD_ISSUED, DRAW_CARD_ACCEPTED, DRAW_CARD_DECLINED } from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import { StateModel } from "../Store";

const handleDrawCard: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case DRAW_CARD_ISSUED:

            console.log("Draw Card was issued");
            return {
                ...state, 
                drawCardIssued: true
            };
        case DRAW_CARD_ACCEPTED:

            console.log("Draw Card was accepted");
            return {
                ...state,
                drawCardIssued: false,
                hand:[
                    ...state.hand,
                    action.cardId    
                ] 
            };
        case DRAW_CARD_DECLINED:

            console.log("Draw Card was declined");
            return {
                ...state,
                drawCardIssued: false
            };
        default:
            return state;
    }
};

export default handleDrawCard;