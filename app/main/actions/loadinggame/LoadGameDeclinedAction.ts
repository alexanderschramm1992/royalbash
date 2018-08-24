import {LOAD_GAME_DECLINED} from "../ActionTypes";
import { Action } from "redux";

interface LoadGameDeclinedAction extends Action<LOAD_GAME_DECLINED>{

    readonly type: LOAD_GAME_DECLINED;
    readonly reason: string;
}

export class LoadGameDeclinedActionFactory{

    public static getInstance(reason: string): LoadGameDeclinedAction {

        return {
            type: LOAD_GAME_DECLINED,
            reason: reason
        }
    }
}

export default LoadGameDeclinedAction;
