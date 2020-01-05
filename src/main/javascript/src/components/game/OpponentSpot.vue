<template>
    <div class="spot">
        <creature v-if="spot.creature" v-bind:creature="spot.creature"/>
        <v-card v-else>
            <v-responsive :aspect-ratio="cardRatio" :width="cardWidth">
                <v-img contain :aspect-ratio="cardImageRatio" src="images/empty-spot.png" class="d-flex">
                </v-img>
            </v-responsive>
        </v-card>
    </div>
</template>

<script>
    import {CARD_IMAGE_RATIO, CARD_RATIO, CARD_WIDTH} from "../../util/Constants";
    import Creature from "./Creature.vue";

    export default {
        name: "opponent-spot",
        components: {
            Creature
        },
        props: {
            spot: Object,
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
        }
    };
</script>
