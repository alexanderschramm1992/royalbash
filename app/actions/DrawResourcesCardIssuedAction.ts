import {DRAW_RESOURCES_CARD_ISSUED} from "./ActionTypes";
import { Action } from "redux";

interface DrawResourcesCardIssuedAction extends Action<DRAW_RESOURCES_CARD_ISSUED>{

    readonly type: DRAW_RESOURCES_CARD_ISSUED;
    readonly playerId: string;
}

export class DrawResourcesCardIssuedActionFactory{

    public static getInstance(playerId: string): DrawResourcesCardIssuedAction {

        return {
            type: DRAW_RESOURCES_CARD_ISSUED,
            playerId: playerId
        }
    }
}

export default DrawResourcesCardIssuedAction;
