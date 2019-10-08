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
          <v-img aspect-ratio="0.8" v-bind:src="'images/' + card.image" />
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" class="text-center">
          <v-card-text>{{ card.text }}</v-card-text>
        </v-col>
      </v-row>
    </v-responsive>
  </v-card>
</template>

<script>
export default {
    name: "card",
    props: {
        card: Object
    }
}
</script>
