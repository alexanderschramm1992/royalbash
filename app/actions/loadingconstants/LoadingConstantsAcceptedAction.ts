import {LOADING_CONSTANTS_ACCEPTED} from "../ActionTypes";
import { Action } from "redux";
import {Constants} from "../../model/Constants";

interface LoadingConstantsAcceptedAction extends Action<LOADING_CONSTANTS_ACCEPTED>{

    readonly type: LOADING_CONSTANTS_ACCEPTED;
    readonly constants: Constants;
}

export class LoadingConstantsAcceptedActionFactory{

    public static getInstance(constants: Constants): LoadingConstantsAcceptedAction {

        return {
            type: LOADING_CONSTANTS_ACCEPTED,
            constants: constants
        }
    }
}

export default LoadingConstantsAcceptedAction;
