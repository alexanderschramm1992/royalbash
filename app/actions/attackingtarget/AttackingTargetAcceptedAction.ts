import {ATTACKING_TARGET_ACCEPTED} from "../ActionTypes";
import {Action} from "redux";
import {Game} from "../../model/Game";

interface AttackingTargetAcceptedAction extends Action<ATTACKING_TARGET_ACCEPTED>{

    readonly type: ATTACKING_TARGET_ACCEPTED;
    readonly game: Game
}

export class AttackingAcceptedActionFactory {

    public static getInstance(game: Game): AttackingTargetAcceptedAction {

        return {
            type: ATTACKING_TARGET_ACCEPTED,
            game: game
        }
    }
}

export default AttackingTargetAcceptedAction;
