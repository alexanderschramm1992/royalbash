import * as Ajax from "../../util/AjaxHandler.js"

const template = `
<div id="game-picker">
    <h1>Games</h1>
    <v-list-item-group>
        <v-list-item v-for="game of games" :key="game.id">
            <v-list-item-content>
                <v-card class="mx-auto">
                    <v-card-title>Game {{ game.id }}</v-card-title>
                    <v-card-text>
                        <p>Status: {{ game.state }}</p>
                        <p>Players: {{ game.player1.name }}, {{ game.player2.name }}</p>
                        <p>Player on Turn: {{ nameOfPlayerOnTurn(game) }}</p>
                    </v-card-text>
                    <v-card-actions>
                        <v-btn v-on:click="proceedGame(game.id, game.player1.id)" 
                               text>Proceed as {{ game.player1.name }}</v-btn>
                        <v-btn v-on:click="proceedGame(game.id, game.player2.id)" 
                               text>Proceed as {{ game.player2.name }}</v-btn>
                    </v-card-actions>
                </v-card>
            </v-list-item-content>
        </v-list-item>
    </v-list-item-group>
</div>
`;

export default Vue.component('game-picker', {
    data() {
        return { games: [] }
    },
    created() {
        this.fetchGames();
    },
    methods: {
        fetchGames: function() {
            Ajax.get("/game",
                (response) => this.games = response,
                (error) => console.error("Cannot fetch Games from Backend due to " + error));
        },
        proceedGame: function (gameId, playerId) {
            sessionStorage.setItem("gameId", gameId);
            sessionStorage.setItem("playerId", playerId);
            window.location.href = "/game.html";
        },
        nameOfPlayerOnTurn: function(game) {
            if (game.player1.id === game.playerOnTurn) return game.player1.name;
            else if (game.player2.id === game.playerOnTurn) return game.player2.name;
            else return "Unknown";
        }
    },
    template: template
});
