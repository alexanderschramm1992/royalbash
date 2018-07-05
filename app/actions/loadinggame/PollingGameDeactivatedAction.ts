import {POLLING_GAME_ACTIVATED, POLLING_GAME_DEACTIVATED} from "../ActionTypes";
import { Action } from "redux";

interface PollingGameDeactivatedAction extends Action<POLLING_GAME_DEACTIVATED>{

    readonly type: POLLING_GAME_DEACTIVATED;
}

export class PollingGameDeactivatedActionFactory{

    public static getInstance(): PollingGameDeactivatedAction  {
        return {type: POLLING_GAME_DEACTIVATED}
    }
}

export default PollingGameDeactivatedAction ;
