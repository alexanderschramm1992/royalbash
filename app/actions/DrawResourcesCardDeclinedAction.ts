import {DRAW_RESOURCES_CARD_DECLINED, DRAW_SUMMONING_CARD_DECLINED} from "./ActionTypes";
import { Action } from "redux";

interface DrawResourcesCardDeclinedAction extends Action<DRAW_RESOURCES_CARD_DECLINED>{

    readonly type: DRAW_RESOURCES_CARD_DECLINED;
    readonly reason: string;
}

export class DrawResourcesCardDeclinedActionFactory{

    public static getInstance(reason: string): DrawResourcesCardDeclinedAction {

        return {
            type: DRAW_RESOURCES_CARD_DECLINED,
            reason: reason
        }
    }
}

export default DrawResourcesCardDeclinedAction;
