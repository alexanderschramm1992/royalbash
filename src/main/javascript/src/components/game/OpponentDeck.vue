<template>
    <v-card>
        <v-responsive :aspect-ratio="cardRatio" :width="cardWidth">
            <v-img contain :aspect-ratio="cardImageRatio" src="images/deck.png" class="d-flex">
                <div class="d-flex justify-center flex-column">
                </div>
            </v-img>
        </v-responsive>
    </v-card>
</template>

<script>
    import {CARD_IMAGE_RATIO, CARD_RATIO, CARD_WIDTH} from "../../util/Constants";

    export default {
    name: "opponent-deck",
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
}
</script>
