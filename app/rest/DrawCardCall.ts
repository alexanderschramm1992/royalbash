import GenericCallPOST from "./GenericCallPOST";
import {CardModel} from "../components/game/card/Card";
import EventBus from "../events/EventBus";
import CardDrawnEvent from "../events/CardDrawnEvent";

export interface DrawCardCallParameters {

    readonly playerInstanceId: string,
    readonly deckInstanceId: string,
    readonly boardId: string
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
                    "playerInstanceId": this.callParameters.playerInstanceId,
                    "deckInstanceId": this.callParameters.deckInstanceId,
                    "boardId": this.callParameters.boardId
                },
                function(data){

                    let json = JSON.parse(data);

                    resolve({
                        id: json.id,
                        image: "",
                        type: "",
                        text: "",
                        name: json.name,
                        cost: json.cost,
                        strength: json.strength,
                        health: json.health
                    });
                }
            );
        }).then((value) => {

            this.eventBus.fireEvent(new CardDrawnEvent(value));
        });
    }
}

export default DrawCardCall;