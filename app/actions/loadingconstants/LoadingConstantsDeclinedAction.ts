import {LOADING_CONSTANTS_DECLINED} from "../ActionTypes";
import { Action } from "redux";

interface LoadingConstantsDeclinedAction extends Action<LOADING_CONSTANTS_DECLINED>{

    readonly type: LOADING_CONSTANTS_DECLINED;
    readonly reason: string;
}

export class LoadingConstantsDeclinedActionFactory{

    public static getInstance(reason: string): LoadingConstantsDeclinedAction {

        return {
            type: LOADING_CONSTANTS_DECLINED,
            reason: reason
        }
    }
}

export default LoadingConstantsDeclinedAction;
