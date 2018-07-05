import {POLLING_GAME_ACTIVATED} from "../ActionTypes";
import { Action } from "redux";

interface PollingGameActivatedAction extends Action<POLLING_GAME_ACTIVATED>{

    readonly type: POLLING_GAME_ACTIVATED;
}

export class PollingGameActivatedActionFactory{

    public static getInstance(): PollingGameActivatedAction {
        return {type: POLLING_GAME_ACTIVATED}
    }
}

export default PollingGameActivatedAction;
