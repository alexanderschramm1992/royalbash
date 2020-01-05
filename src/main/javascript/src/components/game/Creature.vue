<template>
    <v-tooltip :disabled="!hasText" bottom>
        <template v-slot:activator="{ on }">
            <v-card outlined v-on="on">
                <v-responsive :aspect-ratio="cardRatio" :width="cardWidth">
                    <v-img contain :aspect-ratio="cardImageRatio" :src="'images/' + creature.image" class="d-flex">
                        <div class="d-flex justify-space-between card-text text-center"
                             style="flex-direction: column; height: 100%">
                            <div class="d-flex justify-space-between">
                                <div>
                                    <div class="attack pt-1 pb-0 pl-4">
                                        {{ creature.attack }}
                                    </div>
                                    <div class="hitpoints pl-4">
                                        {{ creature.hitpoints }}
                                    </div>
                                </div>
                                <div class="cost pt-4 pr-4">
                                    {{ creature.cost }}
                                </div>
                            </div>
                            <div class="flex-grow-1 d-flex align-end">
                            </div>
                            <div class="label pt-2 pb-3">
                                {{ creature.name }}
                            </div>
                        </div>
                    </v-img>
                </v-responsive>
            </v-card>
        </template>
        <span>{{ creature.text }}</span>
    </v-tooltip>
</template>

<script>
    import {CARD_IMAGE_RATIO, CARD_RATIO, CARD_WIDTH} from "../../util/Constants.js";
    // 11 36 32
    export default {
        name: "creature",
        props: {
            creature: Object
        },
        computed: {
            hasText() {
                return this.creature.text !== "";
            },
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

<style>
    .card-text {
        text-shadow: 0 0 3px black, 0 0 3px black;
        font-family: "Roboto", sans-serif;
        font-weight: 900;
        font-size: 1rem;
        line-height: 1.25rem;
        letter-spacing: 0.0125em;
    }

    .cost {
        color: #FFFFFF;
    }

    .attack {
        color: #90CAF9;
    }

    .hitpoints {
        color: #EF9A9A;
    }

    .label {
        color: #FFFFFF;
        font-size: 0.75rem;
        width: 100%
    }
</style>
