// React
import React from "react";
// Enzyme
import Enzyme from "enzyme";
import { mount } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
Enzyme.configure({adapter: new Adapter()});
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

    it("should set chosenPlayer", () => {

        // Given
        let testee = mount(<GamePicker store={store}/>).instance();

        // When
        testee.setPlayer({target: {value: "player2"}});

        // Then
        expect(testee.state.chosenPlayer).to.equal("player2");
    });

    it("should trigger LOAD_GAME_REQUESTED action", () => {

        // Given
        let testee = mount(<GamePicker store={store}/>).instance();

        // When
        testee.handleClick();

        // Then
        expect(store.actions).deep.contains({type: "LOAD_GAME_REQUESTED", gameId: ""});
        expect(store.actions).deep.contains({type: "PLAYER_CHOSEN", player: "player1"})
    });
});
