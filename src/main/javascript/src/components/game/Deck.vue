<template>
  <v-card class="mx-auto" outlined>
    <v-btn v-on:click="drawCard">Draw Card</v-btn>
  </v-card>
</template>

<script>
import {post} from "./../util/AjaxHandler.js";

export default {
    name: "deck",
    props: {
        gameId: String,
        playerId: String,
        deck: Array
    },
    methods: {
        drawCard: function () {
            post(`/game/${this.gameId}/event`,
                {
                    "event": {
                        "type": "CARD_DRAWN",
                        "playerId": this.playerId,
                        "amountOfCards": 1
                    }
                },
                (response) => this.$root.$emit("updateState", response),
                (error) => console.error(`Cannot commit 'drawCard' to Backend due to ${error}`));
        }
    }
}
</script>