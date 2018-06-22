import {
    LOADING_CONSTANTS_ISSUED,
    LOADING_CONSTANTS_ACCEPTED,
    LOADING_CONSTANTS_DECLINED
} from "../actions/ActionTypes";
import {AnyAction, Reducer} from "redux";
import { StateModel } from "../Store";
import LoadingConstantsAcceptedAction from "../actions/loadingconstants/LoadingConstantsAcceptedAction";
import LoadingConstantsDeclinedAction from "../actions/loadingconstants/LoadingConstantsDeclinedAction";

const handleLoadingConstans: Reducer<StateModel, AnyAction> = (state, action): StateModel => {

    switch (action.type) {
        case LOADING_CONSTANTS_ISSUED:

            console.log("Loading Constants issued");
            return {
                ...state,
                loadingConstantsIssued: true
            };
        case LOADING_CONSTANTS_ACCEPTED:

            console.log("Loading Constants accepted");
            let loadingConstantsAcceptedAction = action as LoadingConstantsAcceptedAction;
            return {
                ...state,
                loadingConstantsIssued: false,
                constants: loadingConstantsAcceptedAction.constants
            };
        case LOADING_CONSTANTS_DECLINED:

            let loadingConstantsDeclinedAction = action as LoadingConstantsDeclinedAction;
            console.log("Loading Constants declined because: " + loadingConstantsDeclinedAction.reason);
            return {
                ...state,
                loadingConstantsIssued: false
            };
        default:
            return state;
    }
};

export default handleLoadingConstans;
