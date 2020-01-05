<template>
    <div class="spot">
        <creature v-if="spot.creature" v-bind:creature="spot.creature"/>
        <v-card v-else>
            <v-responsive :aspect-ratio="cardRatio" :width="cardWidth">
                <v-img contain :aspect-ratio="cardImageRatio" src="images/empty-spot.png" class="d-flex">
                    <div class="d-flex justify-center flex-column" style="height: 100%;">
                        <v-dialog v-model="place_creature_dialog" scrollable>
                            <template v-slot:activator="{ on }">
                                <v-btn v-on="on">Place Creature</v-btn>
                            </template>
                            <v-card>
                                <v-card-title>Select Creature from Handcards</v-card-title>
                                <v-divider/>
                                <v-card-text>
                                    <v-list>
                                        <v-list-item
                                                v-for="handcard in handcards"
                                                :key="handcard.id"
                                                @click="placeCreature(handcard.instanceId)"
                                        >
                                            <v-list-item-title>{{ handcard.name }}</v-list-item-title>
                                        </v-list-item>
                                    </v-list>
                                </v-card-text>
                            </v-card>
                        </v-dialog>
                    </div>
                </v-img>
            </v-responsive>
        </v-card>
    </div>
</template>

<script>
    import {COMMIT_EVENT} from "../../MutationTypes.js";
    import {buildCardPlayedOnSpotEvent} from "../../util/EventBuilder.js"
    import Creature from "./Creature.vue";
    import {CARD_IMAGE_RATIO, CARD_RATIO, CARD_WIDTH} from "../../util/Constants";

    export default {
        name: "spot",
        components: {
            Creature
        },
        props: {
            gameId: String,
            ownerId: String,
            spot: Object,
            handcards: Array
        },
        computed: {
            cardRatio() {
                return CARD_RATIO;
            },
            cardImageRatio() {
                return CARD_IMAGE_RATIO;
            },
            cardWidth() {
                return CARD_WIDTH
            }
        },
        data: function () {
            return {
                place_creature_dialog: false
            };
        },
        methods: {
            placeCreature(cardInstanceId) {
                this.$store.commit(COMMIT_EVENT, buildCardPlayedOnSpotEvent(cardInstanceId, this.ownerId, this.spot.id));
                this.place_creature_dialog = false;
            }
        }
    };
</script>
