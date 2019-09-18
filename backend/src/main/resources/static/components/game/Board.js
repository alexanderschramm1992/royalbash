import * as Ajax from "../../util/AjaxHandler.js"
import {opponent, own} from "./GameUtil.js";
import Handcards from "./Handcards.js";
import Spots from "./Spots.js";
import Deck from "./Deck.js";

const template = `
<div id="Board">
    <v-container class="opponent-deck" fluid>
        <deck v-bind:gameId="gameId"
              v-bind:playerId="opponent.id"
              v-bind:deck="opponentDeck" 
              v-on:updateState="updateState($event)"/>
    </v-container>
    <v-container class="opponent-handcards" fluid>
        <handcards v-bind:handcards="opponentHandcards"/>
    </v-container>
    <v-container class="opponent-spots" fluid>
        <handcards v-bind:spots="opponentSpots"/>
    </v-container>
    <v-container class="own-spots" fluid>
        <spots v-bind:spots="ownSpots"/>
    </v-container>
    <v-container class="own-handcards" fluid>
        <handcards v-bind:handcards="ownHandcards"/>
    </v-container>
    <v-container class="own-deck" fluid>
        <deck v-bind:gameId="gameId"
              v-bind:playerId="own.id"
              v-bind:deck="ownDeck" 
              v-on:updateState="updateState($event)"/>
    </v-container>
</div>
`;

export default Vue.component('board', {
    components: {
        Handcards,
        Spots,
        Deck
    },
    data() {
        return {
            own: {},
            ownHandcards: [],
            ownDeck: [],
            ownSpots: [],
            opponent: {},
            opponentHandcards: [],
            opponentDeck: [],
            opponentSpots: []
        }
    },
    created() {
        this.fetchSessionContext();
        this.fetchGame();
    },
    methods: {
        fetchSessionContext: function() {
            this.gameId = sessionStorage.getItem("gameId");
            if (this.gameId === undefined || this.gameId === "") {
                console.error("Missing Game Id");
                window.location.href = "/"
            }

            this.playerId = sessionStorage.getItem("playerId");
            if (this.playerId === undefined || this.playerId === "") {
                console.error("Player Id");
                window.location.href = "/"
            }
        },
        fetchGame: function() {
            Ajax.get(`/game/${this.gameId}`,
                    (response) => this.updateState(response),
                    (error) => console.error(`Cannot fetch Game from Backend due to ${error}`));
        },
        updateState: function(game) {
            this.game = game;
            this.own = own(this.playerId, game);
            this.ownHandcards = this.own.handcards;
            this.ownDeck = this.own.deck;
            this.ownSpots = this.own.spots;
            this.opponent = opponent(this.playerId, game);
            this.opponentHandcards = this.opponent.handcards;
            this.opponentDeck = this.opponent.deck;
            this.opponentSpots = this.opponent.spots;
        }
    },
    template: template
});
