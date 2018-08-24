import {GATHERING_RESOURCES_ACCEPTED} from "../ActionTypes";
import { Action } from "redux";
import Game from "../../model/Game";

interface GatheringResourcesAcceptedAction extends Action<GATHERING_RESOURCES_ACCEPTED>{

    readonly type: GATHERING_RESOURCES_ACCEPTED;
    readonly game: Game;
}

export class GatheringResourcesAcceptedActionFactory{

    public static getInstance(game: Game): GatheringResourcesAcceptedAction {

        return {
            type: GATHERING_RESOURCES_ACCEPTED,
            game: game
        }
    }
}

export default GatheringResourcesAcceptedAction;
