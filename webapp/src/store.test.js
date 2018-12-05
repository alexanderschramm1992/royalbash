// Jest
let { describe, it } = global;
// Chai
import * as chai from "chai";
const expect = chai.expect;
// Internals
import {endTurn, loadGame, loadGameIds} from "./store";

describe("store handling LOAD_GAME events", () => {

    it("should commit LOAD_GAME_REQUESTED", () => {

        // Given
        let store = {};
        let action = {type: "LOAD_GAME_REQUESTED", gameId: "Game Id"};

        // When
        let updatedStore = loadGame(store, action);

        // Then
        expect(updatedStore).deep.equal({
            gameId: "Game Id",
            gameRequested: true,
            gameLoaded: false,
        });
    });

    it("should commit LOAD_GAME_ACCEPTED", () => {

        // Given
        let store = {chosenPlayer: "player1"};
        let game = {player1: {id: "Player Id"}, playerOnTurn: "Player Id"};
        let action = {type: "LOAD_GAME_ACCEPTED", game: game};

        // When
        let updatedStore = loadGame(store, action);

        // Then
        expect(updatedStore).deep.equal({
            ...store,
            game: game,
            gameRequested: false,
            gameLoaded: true,
        });
    });

    it("should commit LOAD_GAME_DECLINED", () => {

        // Given
        let store = {};
        let action = {type: "LOAD_GAME_DECLINED", errorMessage: "Error Message"};

        // When
        let updatedStore = loadGame(store, action);

        // Then
        expect(updatedStore).deep.equal({
            gameRequested: false,
            gameLoaded: false,
            errorMessage: "Error Message"
        });
    });
});

describe("store handling LOAD_GAME_IDS events", () => {

    it("should commit LOAD_GAME_IDS_REQUESTED", () => {

        // Given
        let store = {};
        let action = {type: "LOAD_GAME_IDS_REQUESTED"};

        // When
        let updatedStore = loadGameIds(store, action);

        // Then
        expect(updatedStore).deep.equal({
            gameIdsRequested: true
        });
    });

    it("should commit LOAD_GAME_IDS_ACCEPTED", () => {

        // Given
        let store = {};
        let action = {type: "LOAD_GAME_IDS_ACCEPTED", gameIds: ["Id"]};

        // When
        let updatedStore = loadGameIds(store, action);

        // Then
        expect(updatedStore).deep.equal({
            gameIds: ["Id"],
            gameIdsRequested: false
        });
    });

    it("should commit LOAD_GAME_IDS_DECLINED", () => {

        // Given
        let store = {};
        let action = {type: "LOAD_GAME_IDS_DECLINED", errorMessage: "Error Message"};

        // When
        let updatedStore = loadGameIds(store, action);

        // Then
        expect(updatedStore).deep.equal({
            errorMessage: "Error Message",
            gameIdsRequested: false
        });
    });
});

describe("store handling END_TURN events", () => {

    it("should commit END_TURN_REQUESTED", () => {

        // Given
        let store = {};
        let action = {type: "END_TURN_REQUESTED"};

        // When
        let updatedStore = endTurn(store, action);

        // Then
        expect(updatedStore).deep.equal({
            endTurnRequested: true
        });
    });

    it("should commit END_TURN_ACCEPTED", () => {

        // Given
        let store = {};
        let game = "Some Game";
        let action = {type: "END_TURN_ACCEPTED", game: game};

        // When
        let updatedStore = endTurn(store, action);

        // Then
        expect(updatedStore).deep.equal({
            endTurnRequested: false,
            game: game,
            onTurn: false
        });
    });

    it("should commit END_TURN_DECLINED", () => {

        // Given
        let store = {};
        let action = {type: "END_TURN_DECLINED", errorMessage: "Error Message"};

        // When
        let updatedStore = endTurn(store, action);

        // Then
        expect(updatedStore).deep.equal({
            endTurnRequested: false,
            errorMessage: "Error Message"
        });
    })
});
