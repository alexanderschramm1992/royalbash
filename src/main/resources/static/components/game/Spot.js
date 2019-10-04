import Creature from "./Creature.js";
import * as Ajax from "../../util/AjaxHandler.js";

const template = `
<div class="spot">
    <creature v-if="spot.creature" 
              v-bind:creature="spot.creature"/>
    <v-card v-else>
        <v-dialog v-model="place_creature_dialog" scrollable>
            <template v-slot:activator="{ on }">
                <v-btn v-on="on">Place Creature</v-btn>
            </template>
            <v-card>
                <v-card-title>Select Creature from Handcards</v-card-title>
                <v-divider/>
                <v-card-text>
                    <v-list>
                        <v-list-item v-for="handcard in handcards"
                                     :key="handcard.id"
                                     @click="placeCreature(handcard.id)">
                            <v-list-item-title>{{ handcard.name }}</v-list-item-title>
                        </v-list-item>
                    </v-list>
                </v-card-text>
            </v-card>
        </v-dialog>
    </v-card>
</div>
`;

export default Vue.component('spot', {
    components: {
        Creature
    },
    props: {
        gameId: String,
        ownerId: String,
        spot: Object,
        handcards: Array
    },
    data() {
        return {
            place_creature_dialog: false
        }
    },
    template: template,
    methods: {
            placeCreature: function (cardId) {
                Ajax.post(`/game/${this.gameId}/event`,
                    {
                        "event": {
                            "type": "CARD_PLAYED_ON_SPOT",
                            "cardId": cardId,
                            "ownerId": this.ownerId,
                            "targetSpotId": this.spot.id
                        }
                    },
                    (response) => this.$root.$emit("updateState", response),
                    (error) => console.error(`Cannot commit 'placeCreature' to Backend due to ${error}`));

                this.place_creature_dialog = false;
            }
        }
})
