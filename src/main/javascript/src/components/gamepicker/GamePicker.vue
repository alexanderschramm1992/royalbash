<template>
  <v-card>
    <v-card-title>Games</v-card-title>
    <v-card-text>
      <v-list-item-group>
        <v-list-item v-for="game of games" :key="game.id">
          <v-list-item-content>
            <game-overview :game="game" />
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-card-text>
  </v-card>
</template>

<script>
import { FETCH_GAMES } from "./../../MutationTypes.js";
import GameOverview from "./GameOverview.vue";

export default {
  name: "game-picker",
  components: {
    GameOverview
  },
  computed: {
    games() {
      return this.$store.state.games ? this.$store.state.games : [];
    }
  },
  created() {
    this.$store.commit(FETCH_GAMES);
  },
  methods: {
    proceedGame(gameId, playerId) {
      sessionStorage.setItem("gameId", gameId);
      sessionStorage.setItem("playerId", playerId);
      window.location.href = "/game.html";
    }
  }
};
</script>
