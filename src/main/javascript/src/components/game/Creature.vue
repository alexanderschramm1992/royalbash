<template>
  <v-tooltip :disabled="!hasText" bottom>
    <template v-slot:activator="{ on }">
      <v-card outlined v-on="on">
        <v-responsive :aspect-ratio="cardRatio" width="225">
          <v-img contain :aspect-ratio="cardImageRatio" :src="'images/' + creature.image" class="d-flex">
            <v-card-title class="d-flex justify-space-between">
              <span class="subtitle-1">{{ creature.name }}</span>
              <span class="subtitle-1">{{ creature.cost }}</span>
            </v-card-title>
            <v-card-text class="align-self-end">
              <span class="d-flex justify-space-between">
                <span>{{ creature.attack }}</span>
                <span>{{ creature.hitpoints }}</span>
              </span>
            </v-card-text>
          </v-img>
        </v-responsive>
      </v-card>
    </template>
    <span>{{ creature.text }}</span>
  </v-tooltip>
</template>

<script>
import { CARD_RATIO, CARD_IMAGE_RATIO } from "../../util/Constants.js";
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
    }
  }
};
</script>
