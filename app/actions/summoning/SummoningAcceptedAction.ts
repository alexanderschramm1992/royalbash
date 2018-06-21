import {SUMMONING_ACCEPTED} from "../ActionTypes";
import {Action} from "redux";
import { Game } from "../../model/Game";

interface SummoningAcceptedAction extends Action<SUMMONING_ACCEPTED>{

    readonly type: SUMMONING_ACCEPTED;
    readonly game: Game
}

export class SummoningAcceptedActionFactory {

    public static getInstance(game: Game): SummoningAcceptedAction {

        return {
            type: SUMMONING_ACCEPTED,
            game: game
        }
    }
}

export default SummoningAcceptedAction;
