<template>
  <v-card class="mx-auto" outlined>
    <v-responsive :aspect-ratio="2/3">
      <v-row>
        <v-col cols="9">
          <v-card-title class="subtitle-1">{{ card.name }}</v-card-title>
        </v-col>
        <v-col cols="3">
          <v-card-title class="subtitle-1">{{ card.cost }}</v-card-title>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-img aspect-ratio="0.8" v-bind:src="'images/' + creature.image" />
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">{{ creature.text }}</v-col>
      </v-row>
      <v-row justify="space-between">
        <v-col cols="3">{{ creature.attack }}</v-col>
        <v-col cols="3">{{ creature.hitpoints }}</v-col>
      </v-row>
    </v-responsive>
  </v-card>
</template>

<script>
export default {
  name: "creature",
  props: {
    creature: Object
  }
};
</script>
