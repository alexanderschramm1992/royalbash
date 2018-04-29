import Event from "./Event";
import {CardModel} from "../components/game/card/Card";

class CardDrawnEvent extends Event {

    private readonly _cardModel: CardModel;

    constructor(
        cardModel: CardModel
    ) {

        super();

        this._cardModel = cardModel;
    }

    public get cardModel(): CardModel {

        return this._cardModel;
    }
}

export default CardDrawnEvent;
