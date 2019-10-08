<template>
  <v-row justify="center">
    <v-col cols="2" v-for="spot of spots" v-bind:key="spot.id">
      <spot
        v-bind:gameId="gameId"
        v-bind:ownerId="ownerId"
        v-bind:spot="spot"
        v-bind:handcards="handcards"
      />
    </v-col>
  </v-row>
</template>

<script>
import Spot from "./Spot.vue";

export default {
    name: "spots",
    components: {
        Spot
    },
    props: {
        gameId: String,
        ownerId: String,
        spots: Array,
        handcards: Array
    }
}
</script>