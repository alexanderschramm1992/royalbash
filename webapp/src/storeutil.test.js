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
        expect(result).toBe("foo");
    });

    it("should get undefined if chosen player not defined", () => {
        // Given
        let store = {
            getState: () => { return {}}
        };

        // When
        let result = getChosenPlayer(store);

        // Then
        expect(result).toBeFalsy();
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
        expect(result).toBe("bar");
    });

    it("should get empty array if chosen player handcards are not available", () => {
        // Given
        let store = {
            getState: () => { return {}}
        };

        // When
        let result = getHandcards(store);

        // Then
        expect(result).toHaveLength(0);
    });
});