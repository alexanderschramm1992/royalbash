import React from "react";
import ReactDOM from "react-dom";
import Board from "./Board";

describe("Board", () => {

    const store = {
        dispatch: function(action) {
            this.actions.push(action);
        },
        subscribe: function(methodToTrigger) {

        },
        getState: function() {
            return {
                chosenPlayer: "player1"
            }
        },
        actions: []
    };

    it("renders without crashing", () => {
        const div = document.createElement('div');
        ReactDOM.render(<Board store={store}/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });

    it("triggers END_TURN_REQUESTED action", () => {

        // Given
        const testee = ReactDOM.render(<Board store={store}/>, document.createElement('div'));

        // When
        testee.handleEndTurnButton();

        // Then
        expect(store.actions).toContainEqual({type: "END_TURN_REQUESTED", player: "player1"});
    });

    it("triggers DRAW_CARD_REQUESTED action", () => {

        // Given
        const testee = ReactDOM.render(<Board store={store}/>, document.createElement('div'));

        // When
        testee.handleDrawCardButton();

        // Then
        expect(store.actions).toContainEqual({type: "DRAW_CARD_REQUESTED", player: "player1"});
    });
});