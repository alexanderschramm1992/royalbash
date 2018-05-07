import { MOUSE_ON_SUMMONING } from "./ActionTypes";
import {Action} from "redux";

interface MouseOnSummoningAction extends Action<MOUSE_ON_SUMMONING>{

    readonly type: MOUSE_ON_SUMMONING;
    readonly summoningId: string;
}

export class MouseOnSummoningActionFactory {

    public static getInstance(summoningId: string): MouseOnSummoningAction {

        return {
            type: MOUSE_ON_SUMMONING,
            summoningId: summoningId
        }
    }
}

export default MouseOnSummoningAction;
