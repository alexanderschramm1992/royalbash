<template>
  <v-card class="mx-auto">
    <v-card-title>Game {{ game.id }}</v-card-title>
    <v-card-text>
      <p>Status: {{ game.state }}</p>
      <p>Players: {{ game.player1.name }}, {{ game.player2.name }}</p>
      <p>Player on Turn: {{ nameOfPlayerOnTurn }}</p>
    </v-card-text>
    <v-card-actions>
      <v-btn
        v-on:click="proceedGame(game.id, game.player1.id)"
        text
      >Proceed as {{ game.player1.name }}</v-btn>
      <v-btn
        v-on:click="proceedGame(game.id, game.player2.id)"
        text
      >Proceed as {{ game.player2.name }}</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "game-overview",
  props: {
    game: Object
  },
  computed: {
      nameOfPlayerOnTurn() {
          if (this.game.player1.id === this.game.playerOnTurn) return this.game.player1.name;
          else if (this.game.player2.id === this.game.playerOnTurn) return this.game.player2.name;
          else return "Unknown";
      }
  }
};
</script>
