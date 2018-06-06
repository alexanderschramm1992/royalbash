import {ATTACKING_ACCEPTED} from "./ActionTypes";
import {Action} from "redux";
import {Game} from "../model/Game";

interface AttackingAcceptedAction extends Action<ATTACKING_ACCEPTED>{

    readonly type: ATTACKING_ACCEPTED;
    readonly game: Game
}

export class AttackingAcceptedActionFactory {

    public static getInstance(game: Game): AttackingAcceptedAction {

        return {
            type: ATTACKING_ACCEPTED,
            game: game
        }
    }
}

export default AttackingAcceptedAction;
