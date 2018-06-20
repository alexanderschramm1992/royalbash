import {GATHERING_RESOURCES_DECLINED} from "./ActionTypes";
import { Action } from "redux";

interface GatheringResourcesDeclinedAction extends Action<GATHERING_RESOURCES_DECLINED>{

    readonly type: GATHERING_RESOURCES_DECLINED;
    readonly reason: string;
}

export class GatheringResourcesDeclinedActionFactory{

    public static getInstance(reason: string): GatheringResourcesDeclinedAction {

        return {
            type: GATHERING_RESOURCES_DECLINED,
            reason: reason
        }
    }
}

export default GatheringResourcesDeclinedAction;
