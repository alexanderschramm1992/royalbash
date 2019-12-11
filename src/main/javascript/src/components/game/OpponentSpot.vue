<template>
  <div class="spot">
    <creature v-if="spot.creature" v-bind:creature="spot.creature" />
    <v-card v-else>
        <v-responsive :aspect-ratio="cardRatio" :width="cardWidth">
            Empty Spot
        </v-responsive>
    </v-card>
  </div>
</template>

<script>
    import {CARD_RATIO, CARD_WIDTH} from "../../util/Constants";
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
                return CARD_RATIO
            },
            cardWidth() {
                return CARD_WIDTH
            }
        }
};
</script>
