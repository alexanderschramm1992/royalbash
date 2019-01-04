// React
import React from "react";
// Enzyme
import Enzyme from "enzyme";
import { mount } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
Enzyme.configure({adapter: new Adapter()});
// Internals
import Board from "./Board";

describe("board component", () => {

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
        mount(<Board store={store}/>).unmount();
    });

    it("should trigger END_TURN_REQUESTED action", () => {

        // Given
        let testee = mount(<Board store={store}/>).instance();

        // When
        testee.handleEndTurnButton();

        // Then
        expect(store.actions).toContainEqual({type: "END_TURN_REQUESTED", player: "player1"});
    });

    it("should trigger DRAW_CARD_REQUESTED action", () => {

        // Given
        let testee = mount(<Board store={store}/>).instance();

        // When
        testee.handleDrawCardButton();

        // Then
        expect(store.actions).toContainEqual({type: "DRAW_CARD_REQUESTED", player: "player1"});
    });
});