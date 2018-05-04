import { DRAW_CARD_ACCEPTED } from "./ActionTypes";
import { Action } from "redux";
import {CardModel} from "../components/game/card/Card";

interface DrawCardAcceptedAction extends Action<DRAW_CARD_ACCEPTED> {

    readonly type: DRAW_CARD_ACCEPTED;
    readonly card: CardModel;
}

export class DrawCardAcceptedActionFactory{

    public static getInstance(card: CardModel): DrawCardAcceptedAction {

        return {
            type: DRAW_CARD_ACCEPTED,
            card: card
        }
    }
}

export default DrawCardAcceptedAction;
