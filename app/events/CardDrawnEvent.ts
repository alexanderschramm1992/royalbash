import Event from "./Event";
import {CardModel} from "../components/game/card/Card";

class CardDrawnEvent extends Event {

    private readonly _creatureModel: CardModel;

    constructor(
        creatureModel: CardModel
    ) {

        super();

        this._creatureModel = creatureModel;
    }

    public get creatureModel(): CardModel {

        return this._creatureModel;
    }
}

export default CardDrawnEvent;
