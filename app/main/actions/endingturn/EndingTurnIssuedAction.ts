import {ENDING_TURN_ISSUED} from "../ActionTypes";
import { Action } from "redux";

interface EndingTurnIssuedAction extends Action<ENDING_TURN_ISSUED>{

    readonly type: ENDING_TURN_ISSUED;
    readonly gameId: string;
    readonly playerId: string;
}

export class EndingTurnIssuedActionFactory{

    public static getInstance(gameId: string, playerId: string): EndingTurnIssuedAction {

        return {
            type: ENDING_TURN_ISSUED,
            gameId: gameId,
            playerId: playerId
        }
    }
}

export default EndingTurnIssuedAction;
