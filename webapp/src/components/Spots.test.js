// React
import React from "react";
// Enzyme
import Enzyme from "enzyme";
import { mount } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
Enzyme.configure({adapter: new Adapter()});
// Internals
import Spots from "./Spots";

describe("spots component", () => {

    const store = {
        getState: function () {
            return {
                chosenPlayer: "player1"
            }
        }
    };

    it("renders without crashing", () => {
        mount(<Spots store={store}/>).unmount();
    });

    it("should get spots of chosen player", () => {
        // Given
        let store = {
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
        let testee = mount(<Spots store={store}/>).instance();

        // When
        let result = testee.getSpots();

        // Then
        expect(result).toContainEqual({id: "foo"});
    });

    it("should get empty array if chosen player spots are not available", () => {
        // Given
        let store = {
            getState: () => { return {}}
        };
        let testee = mount(<Spots store={store}/>).instance();

        // When
        let result = testee.getSpots();

        // Then
        expect(result).toHaveLength(0);
    });
});