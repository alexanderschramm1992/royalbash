<template>
    <v-card>
        <v-responsive :aspect-ratio="cardRatio" :width="cardWidth">
            <v-img contain :aspect-ratio="cardImageRatio" src="images/deck.png" class="d-flex">
                <div class="d-flex justify-center flex-column" style="height: 100%;">
                    <v-btn v-on:click="drawCard" class="ma-3 caption">Draw Card</v-btn>
                </div>
            </v-img>
        </v-responsive>
    </v-card>
</template>

<script>
    import {buildCardDrawnEvent} from "../../util/EventBuilder.js";
    import {COMMIT_EVENT} from "../../MutationTypes.js";
    import {CARD_IMAGE_RATIO, CARD_RATIO, CARD_WIDTH} from "../../util/Constants";

    export default {
    name: "player-deck",
    props: {
        playerId: String,
        deck: Array
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
    methods: {
        drawCard() {
            this.$store.commit(COMMIT_EVENT, buildCardDrawnEvent(this.playerId));
        }
    }
}
</script>
