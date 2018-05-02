import { DRAW_CARD_ISSUED, DRAW_CARD_ACCEPTED, DRAW_CARD_DECLINED } from "../actions/ActionTypes";
import DrawCardIssuedAction from "../actions/DrawCardIssuedAction";
import DrawCardAcceptedAction from "../actions/DrawCardAcceptedAction";
import DrawCardDeclinedAction from "../actions/DrawCardDeclinedAction";
import { Reducer } from "redux";
import { StateModel, initialState } from "../Store";

type ActionTypes = DrawCardIssuedAction | DrawCardAcceptedAction | DrawCardDeclinedAction;

const handleDrawCard: Reducer<StateModel, ActionTypes> = (state, action): any => {

    console.log(state);
    console.log(initialState());
    if (!state) {

        state = initialState();
        console.log(state);
    }

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
            }
        default:
            return state;
    }
}

export default handleDrawCard;
