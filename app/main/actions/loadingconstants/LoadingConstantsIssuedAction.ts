import {LOADING_CONSTANTS_ISSUED} from "../ActionTypes";
import { Action } from "redux";

interface LoadingConstantsIssuedAction extends Action<LOADING_CONSTANTS_ISSUED>{

    readonly type: LOADING_CONSTANTS_ISSUED;
}

export class LoadingConstantsIssuedActionFactory{

    public static getInstance(): LoadingConstantsIssuedAction {

        return {
            type: LOADING_CONSTANTS_ISSUED,
        }
    }
}

export default LoadingConstantsIssuedAction;
