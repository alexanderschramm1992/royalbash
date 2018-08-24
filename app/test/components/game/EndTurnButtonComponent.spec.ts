import EndTurnButtonComponent from "../../../main/components/game/EndTurnButtonComponent";
import {Action} from "redux";
import EndingTurnIssuedAction from "../../../main/actions/endingturn/EndingTurnIssuedAction";
import configureStore from "redux-mock-store";

test('basic', () => {

    // Given
    let props = {
        store: {
            actionCaught: undefined,
            getState: () => {
                return {
                    endingTurnIssued: false,
                    ownTurn: true,
                    gameId: "gameId",
                    playerId: "playerId"
                }
            },
            dispatch: (action: EndingTurnIssuedAction) => {
                this.actionsCaught.push(action)
            }
        }
    };

    let endTurnButtonComponent = new EndTurnButtonComponent(props);

    // When
    endTurnButtonComponent.endTurn();

    // Then
    expect(props.actionsCaught).toBe(0);
});

test('basic again', () => {
    expect(sum(1, 2)).toBe(3);
});