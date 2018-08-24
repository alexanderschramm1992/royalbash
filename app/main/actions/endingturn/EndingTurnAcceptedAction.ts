import {ENDING_TURN_ACCEPTED} from "../ActionTypes";
import { Action } from "redux";
import Game from "../../model/Game";

interface EndingTurnAcceptedAction extends Action<ENDING_TURN_ACCEPTED>{

    readonly type: ENDING_TURN_ACCEPTED;
    readonly game: Game;
}

export class EndingTurnAcceptedActionFactory{

    public static getInstance(game: Game): EndingTurnAcceptedAction {

        return {
            type: ENDING_TURN_ACCEPTED,
            game: game
        }
    }
}

export default EndingTurnAcceptedAction;
