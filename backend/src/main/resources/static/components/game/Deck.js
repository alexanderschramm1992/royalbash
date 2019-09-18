import * as Ajax from "../../util/AjaxHandler.js";

const template = `
<v-card class="mx-auto" outlined>
    <v-btn v-on:click="drawCard">Draw Card</v-btn>
</v-card>
`;

export default Vue.component('deck', {
    props: {
        gameId: String,
        playerId: String,
        deck: Array
    },
    template: template,
    methods: {
        drawCard: function () {
            Ajax.post(`/game/${this.gameId}/event`,
                {
                    "event": {
                        "type": "CARD_DRAWN",
                        "playerId": this.playerId,
                        "amountOfCards": 1
                    }
                },
                (response) => this.$emit("updateState", response),
                (error) => console.error(`Cannot commit 'drawCard' to Backend due to ${error}`));
        }
    }
})
