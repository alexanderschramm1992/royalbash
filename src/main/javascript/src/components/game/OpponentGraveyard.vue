<template>
    <v-card>
        <v-responsive :aspect-ratio="cardRatio" :width="cardWidth">
            <v-img contain :aspect-ratio="cardImageRatio" src="images/deck.png" class="d-flex">
                <div class="d-flex justify-center flex-column" style="height: 100%;">
                    <v-dialog v-model="show_graveyard_dialog" scrollable>
                        <template v-slot:activator="{ on }">
                            <v-btn v-on="on" class="ma-3 caption">Show Graveyard</v-btn>
                        </template>
                        <v-card>
                            <v-card-title>Cards in Graveyard</v-card-title>
                            <v-divider/>
                            <v-card-text>
                                <v-list>
                                    <v-list-item
                                            v-for="card in graveyardCards"
                                            :key="card.id"
                                    >
                                        <v-list-item-title>{{ card.name }}</v-list-item-title>
                                    </v-list-item>
                                </v-list>
                            </v-card-text>
                        </v-card>
                    </v-dialog>
                </div>
            </v-img>
        </v-responsive>
    </v-card>
</template>

<script>
    import {CARD_IMAGE_RATIO, CARD_RATIO, CARD_WIDTH} from "../../util/Constants";

    export default {
    name: "opponent-graveyard",
    props: {
        playerId: String,
        graveyardCards: Array
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
            show_graveyard_dialog: false
        };
    },
}
</script>
