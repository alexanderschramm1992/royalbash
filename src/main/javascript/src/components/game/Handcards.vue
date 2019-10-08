<template>
  <v-row justify="center">
    <v-col cols="2" v-for="handcard of handcards" v-bind:key="handcard.instanceId">
      <card v-bind:card="handcard" />
    </v-col>
  </v-row>
</template>

<script>
import Card from "./Card.vue";

export default {
    name: "handcards",
    props: {
        handcards: Array
    }
}
</script>
