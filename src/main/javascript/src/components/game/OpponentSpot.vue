<template>
  <div class="spot">
    <creature v-if="spot.creature" v-bind:creature="spot.creature" />
    <v-card v-else>
        <v-responsive :aspect-ratio="3/5" width="225">
            Empty Spot
        </v-responsive>
    </v-card>
  </div>
</template>

<script>
  import {COMMIT_EVENT} from "../../MutationTypes.js";
  import {buildCardPlayedOnSpotEvent} from "../../util/EventBuilder.js"
  import Creature from "./Creature.vue";

  export default {
  name: "opponent-spot",
  components: {
    Creature
  },
  props: {
    spot: Object,
  },
};
</script>
