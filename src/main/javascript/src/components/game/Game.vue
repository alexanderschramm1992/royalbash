<template>
<div id="Board">
    <v-row justify="center">
        <v-col cols="10">
            <v-container class="opponent-deck" fluid>
                <deck v-bind:gameId="gameId"
                      v-bind:playerId="opponent.id"
                      v-bind:deck="opponentDeck"/>
            </v-container>
            <v-container class="opponent-handcards" fluid>
                <handcards v-bind:handcards="opponentHandcards"/>
            </v-container>
            <v-container class="opponent-spots" fluid>
                <spots v-bind:gameId="gameId"
                       v-bind:ownerId="opponent.id"
                       v-bind:spots="opponentSpots"
                       v-bind:handcards="opponentHandcards"/>
            </v-container>
            <v-container class="own-spots" fluid>
                <spots v-bind:gameId="gameId"
                       v-bind:ownerId="own.id"
                       v-bind:spots="ownSpots"
                       v-bind:handcards="ownHandcards"/>
            </v-container>
            <v-container class="own-handcards" fluid>
                <handcards v-bind:handcards="ownHandcards"/>
            </v-container>
            <v-container class="own-deck" fluid>
                <deck v-bind:gameId="gameId"
                      v-bind:playerId="own.id"
                      v-bind:deck="ownDeck"/>
            </v-container>
        </v-col>
        <v-col cols="2">
            <v-container class="log">
                <h3>Game Log</h3>
                <v-list three-line dense>
                    <v-listItem v-for="log of logs"
                                v-bind:key="log.id">
                        <v-list-item-content>
                            <v-list-item-title style="white-space: initial;">{{ log.text }}</v-list-item-title>
                            <v-list-item-subtitle>{{ log.timestamp }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-listItem>
                </v-list>
            </v-container>
        </v-col>
    </v-row>
</div>
</template>

<script>
import {get} from "./../util/AjaxHandler.js";
import {opponent, own} from "./../util/GameUtil.js";
import Handcards from "./Handcards.vue";
import Spots from "./Spots.vue";
import Deck from "./Deck.vue";

export default {
    name: "game",
    components: {
        Handcards,
        Spots,
        Deck
    },
    data: function () {
        return {
            logs: [],
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
    created: function() {
        this.fetchSessionContext();
        this.fetchGame();
    },
    mounted: function() {
        this.$root.$on("updateState", this.updateState)
    },
    methods: {
        fetchSessionContext: function () {
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
        fetchGame: function () {
            Ajax.get(`/game/${this.gameId}`,
                    (response) => this.updateState(response),
                    (error) => console.error(`Cannot fetch Game from Backend due to ${error}`));
        },
        updateState: function (game) {
            this.game = game;
            this.logs = this.game.logs;
            this.own = own(this.playerId, game);
            this.ownHandcards = this.own.handcards;
            this.ownDeck = this.own.deck;
            this.ownSpots = this.own.spots;
            this.opponent = opponent(this.playerId, game);
            this.opponentHandcards = this.opponent.handcards;
            this.opponentDeck = this.opponent.deck;
            this.opponentSpots = this.opponent.spots;
        }
    }
}
</script>
