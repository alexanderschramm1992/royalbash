import { MOUSE_OFF_SUMMONING } from "../ActionTypes";
import {Action} from "redux";

interface MouseOffSummoningAction extends Action<MOUSE_OFF_SUMMONING>{

    readonly type: MOUSE_OFF_SUMMONING;
}

export class MouseOffSummoningActionFactory {

    public static getInstance(): MouseOffSummoningAction {

        return {
            type: MOUSE_OFF_SUMMONING,
        }
    }
}

export default MouseOffSummoningAction;
