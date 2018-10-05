// React
import React from "react";
// Enzyme
import Enzyme from "enzyme";
import { mount } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
Enzyme.configure({ adapter: new Adapter() });
// Jest
let { describe, it } = global;
// Chai
import * as chai from "chai";
const expect = chai.expect;
// Internals
import GamePicker from "./GamePicker";

describe("game picker component", () => {

    const store = {
        dispatch: function(action) {
            this.actions.push(action);
        },
        subscribe: function(methodToTrigger) {

        },
        actions: []
    };

    it("renders without crashing", () => {
        mount(<GamePicker store={store}/>).unmount();
    });

    it("should set gameId", () => {

        // Given
        let testee = mount(<GamePicker store={store}/>).instance();

        // When
        testee.setGameId({target: {value: "123qwe"}});

        // Then
        expect(testee.state.gameId).to.equal("123qwe");
    });

    it("should trigger load game requested action", () => {

        // Given
        let testee = mount(<GamePicker store={store}/>).instance();

        // When
        testee.handleClick();

        // Then
        console.log(store.actions);
        expect(store.actions).deep.contains({type: "LOAD_GAME_REQUESTED", gameId: ""});
    });
});
