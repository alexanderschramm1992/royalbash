import * as Ajax from "../../util/AjaxHandler.js"

export default {
    name: "GamePicker",
    data() {
        return {
            board: []
        }
    },
    mounted() {
        Ajax.get("/game/",
            (response) => this.games = response.map(game => { return {
                "name": "Game 2",
                "id": game.id,
                "status": game.state,
                "player1": game.player1.name,
                "player2": game.player2.name
            }}),
            (error) => console.log("Cannot fetch Game Ids from backend due to " + error));
    },
    template: `
        <div id="game-picker">
            <h1>Games</h1>
            <v-list-item-group v-model="game" color="primary">
                <v-list-item v-for="game in games" :key="game.id">
                    <v-list-item-content>
                        <v-card class="mx-auto">
                            <v-card-title>{{ game.name }}</v-card-title>
                            <v-card-text>
                                <p>Status: {{ game.status }}</p>
                                <p>Players: {{ game.player1 }}, {{ game.player2 }}</p>
                            </v-card-text>
                            <v-card-actions>
                                <v-btn :href="'/game.html?id=' + game.id" text>Proceed</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-list-item-content>
                </v-list-item>
            </v-list-item-group>
        </div>
    `,

}
