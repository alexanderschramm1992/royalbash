import React from "react";
import ReactDOM from "react-dom";
import Spots from "./Spots";

describe("Spots Component", () => {

    const store = {
        getState: function () {
            return {
                chosenPlayer: "player1"
            }
        }
    };

    it("renders without crashing", () => {
        const div = document.createElement('div');
        ReactDOM.render(<Spots store={store}/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });

    it("gets spots of chosen player", () => {
        // Given
        const store = {
            getState: () => { return {
                game: {
                    player1: {
                        spots: [{id: "foo"}]
                    }
                },
                chosenPlayer: "player1",
                gameLoaded: true
            }}
        };
        const testee = ReactDOM.render(<Spots store={store}/>, document.createElement('div'));

        // When
        const result = testee.getSpots();

        // Then
        expect(result).toContainEqual({id: "foo"});
    });

    it("gets empty array if chosen player spots are not available", () => {
        // Given
        const store = {
            getState: () => { return {}}
        };
        const testee = ReactDOM.render(<Spots store={store}/>, document.createElement('div'));

        // When
        const result = testee.getSpots();

        // Then
        expect(result).toHaveLength(0);
    });
});