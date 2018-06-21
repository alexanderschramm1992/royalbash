import {RESOURCES_CARD_DRAGGED} from "../ActionTypes";
import {Action} from "redux";

interface ResourcesCardDraggedAction extends Action<RESOURCES_CARD_DRAGGED>{

    readonly type: RESOURCES_CARD_DRAGGED;
    readonly cardId: string;
}

export class ResourcesCardDraggedActionFactory {

    public static getInstance(cardId: string): ResourcesCardDraggedAction {

        return {
            type: RESOURCES_CARD_DRAGGED,
            cardId: cardId
        }
    }
}

export default ResourcesCardDraggedAction;
