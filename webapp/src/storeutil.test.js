// Jest
let { describe, it } = global;
// Chai
import * as chai from "chai";
const expect = chai.expect;
// Internals
import {getChosenPlayer, getHandcards} from "./storeutil";

describe("store util", () => {

    it("should get chosen player", () => {
        // Given
        let store = {
            getState: () => { return {
                game: {player1: "foo"},
                chosenPlayer: "player1",
                gameLoaded: true
            }}
        };

        // When
        let result = getChosenPlayer(store);

        // Then
        expect(result).to.equal("foo");
    });

    it("should get undefined if chosen player not defined", () => {
        // Given
        let store = {
            getState: () => { return {}}
        };

        // When
        let result = getChosenPlayer(store);

        // Then
        expect(result).to.be.undefined;
    });

    it("should get handcards of chosen player", () => {
        // Given
        let store = {
            getState: () => { return {
                game: {
                    player1: {
                        handcards: "bar"
                    }
                },
                chosenPlayer: "player1",
                gameLoaded: true
            }}
        };

        // When
        let result = getHandcards(store);

        // Then
        expect(result).to.equal("bar");
    });

    it("should get empty array if chosen player handcards are not available", () => {
        // Given
        let store = {
            getState: () => { return {}}
        };

        // When
        let result = getHandcards(store);

        // Then
        expect(result).to.be.empty;
    });
});