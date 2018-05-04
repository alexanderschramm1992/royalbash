import {MOUSE_OFF_CARD} from "./ActionTypes";
import {Action} from "redux";

interface MouseOffCardAction extends Action<MOUSE_OFF_CARD>{

    readonly type: MOUSE_OFF_CARD;
}

export class MouseOffCardActionFactory{

    public static getInstance(): MouseOffCardAction {

        return {
            type: MOUSE_OFF_CARD,
        }
    }
}

export default MouseOffCardAction;
