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
import Player from "./Player";

describe("player component", () => {

    const store = {
        dispatch: function(action) {this.actions.push(action);},
        subscribe: function(methodToTrigger) {},
        getState: function() {return {gameLoaded: true, chosenPlayer: "player1", game: {player1: {id: "Owner Id"}}}},
        actions: []
    };

    it("renders without crashing", () => {
        mount(<Player store={store} id="Player Id"/>).unmount();
    });

    it("should not trigger PLAY_CARD_ON_PLAYER_REQUESTED action", () => {

        // Given
        let testee = mount(<Player store={store} id="Player Id"/>).instance();
        let event = {
            dataTransfer: {
                getData: () => {return "banana";}
            }
        };

        // When
        testee.handleDrop(event);

        // Then
        expect(store.actions).to.be.empty;
    });

    it("should trigger PLAY_CARD_ON_PLAYER_REQUESTED action", () => {

        // Given
        let testee = mount(<Player store={store} id="Player Id"/>).instance();
        let event = {
            dataTransfer: {
                getData: (key) => {
                    if(key === "type") {return "card";}
                    if(key === "payload") {return {id: "Card Id"}}
                }
            }
        };

        // When
        testee.handleDrop(event);

        // Then
        expect(store.actions).deep.contains({
            type: "PLAY_CARD_ON_PLAYER_REQUESTED",
            cardId: "Card Id",
            ownerId: "Owner Id",
            playerId: "Player Id"
        });
    });
});
