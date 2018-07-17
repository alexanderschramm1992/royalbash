import {LOAD_GAME_ISSUED} from "../ActionTypes";
import { Action } from "redux";

interface LoadGameIssuedAction extends Action<LOAD_GAME_ISSUED>{

    readonly type: LOAD_GAME_ISSUED;
    readonly gameId: string;
    readonly playerId: string;
}

export class LoadGameIssuedActionFactory{

    public static getInstance(gameId: string, playerId: string): LoadGameIssuedAction {

        return {
            type: LOAD_GAME_ISSUED,
            gameId: gameId,
            playerId: playerId
        }
    }
}

export default LoadGameIssuedAction;
