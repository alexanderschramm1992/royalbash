<template>
  <v-tooltip v-if="!hidden" :disabled="!hasText" bottom>
    <template v-slot:activator="{ on }">
      <v-card outlined v-on="on">
        <v-responsive :aspect-ratio="cardRatio" width="225">
          <v-card-title class="d-flex justify-space-between mb-n12">
            <span class="subtitle-1">{{ card.name }}</span>
            <span class="subtitle-1">{{ card.cost }}</span>
          </v-card-title>
          <v-img
            contain
            :aspect-ratio="cardImageRatio"
            :src="'images/' + card.image"
          />
          <v-card-text v-if="card.attack && card.hitpoints" class="mt-n12">
            <span class="d-flex justify-space-between">
              <span>{{ card.attack }}</span>
              <span>{{ card.hitpoints }}</span>
            </span>
          </v-card-text>
        </v-responsive>
      </v-card>
    </template>
    <span>{{ card.text }}</span>
  </v-tooltip>
  <v-card v-else outlined>
    <v-responsive :aspect-ratio="cardRatio" width="225">
      <v-img :aspect-ratio="cardRatio" src="images/cardback.png" />
    </v-responsive>
  </v-card>
</template>

<script>
import { CARD_RATIO, CARD_IMAGE_RATIO } from "../../util/Constants.js";

export default {
  name: "card",
  props: {
    card: Object,
    hidden: Boolean
  },
  computed: {
    hasText() {
      return this.card.text !== "";
    },
    cardRatio() {
      return CARD_RATIO;
    },
    cardImageRatio() {
      return CARD_IMAGE_RATIO;
    }
  }
};
</script>
