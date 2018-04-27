import GenericCallPOST from "./GenericCallPOST";
import {CardModel} from "../components/game/card/Card";
import EventBus from "../events/EventBus";
import CardDrawnEvent from "../events/CardDrawnEvent";

export interface DrawCardCallParameters {

    readonly playerId: string
}

class DrawCardCall extends GenericCallPOST{

    constructor (
        private callParameters: DrawCardCallParameters,
        private eventBus: EventBus<CardDrawnEvent>
    ) {
        super();
    }

    public call(): void {

        new Promise<CardModel>((resolve) => {

            super.call(
                "gameloop/draw",
                {
                    "Content-Type": "application/json; charset=utf-8"
                },
                {
                    "playerId": this.callParameters.playerId
                },
                (data: CardModel): void => {

                    resolve({
                        id: data.id,
                        image: "",
                        type: "",
                        text: "",
                        name: data.name,
                        cost: data.cost,
                        strength: data.strength,
                        health: data.health
                    });
                }
            );
        }).then((value) => {

            this.eventBus.fireEvent(new CardDrawnEvent(value));
        });
    }
}

export default DrawCardCall;