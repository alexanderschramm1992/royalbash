import * as Ajax from "../../util/AjaxHandler.js"

export default {
    name: "GamePicker",
    data() {
        return {
            games: []
        }
    },
    mounted() {
        Ajax.get("/game",
            (response) => this.games = response.map(game => { return {
                "name": "Game 2",
                "id": game.id,
                "status": game.state,
                "player1": game.player1.name,
                "player2": game.player2.name
            }}),
            (error) => console.log("Cannot fetch Game Ids from backend due to " + error));
    },
    methods: {
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
    template: `
        <div id="game-picker">
            <h1>Games</h1>
            <v-list-item-group v-model="games" color="primary">
                <v-list-item v-for="game in games" :key="game.id">
                    <v-list-item-content>
                        <v-card class="mx-auto">
                            <v-card-title>{{ game.id }}</v-card-title>
                            <v-card-text>
                                <p>Status: {{ game.status }}</p>
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
    `,

}
