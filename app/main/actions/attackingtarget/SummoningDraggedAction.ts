import {SUMMONING_DRAGGED} from "../ActionTypes";
import {Action} from "redux";

interface SummoningDraggedAction extends Action<SUMMONING_DRAGGED>{

    readonly type: SUMMONING_DRAGGED;
    readonly summoningId: string;
}

export class SummoningDraggedActionFactory {

    public static getInstance(summoningId: string): SummoningDraggedAction {

        return {
            type: SUMMONING_DRAGGED,
            summoningId: summoningId
        }
    }
}

export default SummoningDraggedAction;
