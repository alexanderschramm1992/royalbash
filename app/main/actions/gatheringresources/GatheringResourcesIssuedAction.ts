import {GATHERING_RESOURCES_ISSUED} from "../ActionTypes";
import { Action } from "redux";

interface GatheringResourcesIssuedAction extends Action<GATHERING_RESOURCES_ISSUED>{

    readonly type: GATHERING_RESOURCES_ISSUED;
    readonly cardId: string;
}

export class GatheringResourcesIssuedActionFactory{

    public static getInstance(cardId: string): GatheringResourcesIssuedAction {

        return {
            type: GATHERING_RESOURCES_ISSUED,
            cardId: cardId
        }
    }
}

export default GatheringResourcesIssuedAction;
