import * as Ajax from "../../util/AjaxHandler.js"
import {own, opponent} from "./GameUtil.js";
import Card from "./Card.js";
import Spot from "./Spot.js";

const template = `
<div id="Board">
    <v-container class="opponent-handcards">
    <v-row justify="center">
            <v-col cols="2"
                   max-width="20%"
                   v-for="handcard of opponentHandcards"
                   v-bind:key="handcard.id">
                <card v-bind:card="handcard"/>
            </v-col>
        </v-row>
    </v-container>
    <v-container class="own-spots">
        <v-row justify="center">
            <v-col cols="2"
                   max-width="20%"
                   v-for="spot of ownSpots"
                   v-bind:key="spot.id">
                <spot v-bind:spot="spot"/>
            </v-col>
        </v-row>
    </v-container>
    <v-container class="own-handcards">
        <v-row justify="center">
            <v-col cols="2"
                   max-width="20%"
                   v-for="handcard of ownHandcards"
                   v-bind:key="handcard.id">
                <card v-bind:card="handcard"/>
            </v-col>
        </v-row>
    </v-container>
</div>
`;

export default Vue.component('board', {
    components: {
        Card,
        Spot
    },
    data() {
        return {
            ownHandcards: [],
            ownSpots: [],
            opponentHandcards: []
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
            this.ownSpots = this.own.spots;
            this.opponent = opponent(this.playerId, game);
            this.opponentHandcards = this.opponent.handcards;
        },
    },
    template: template
});
