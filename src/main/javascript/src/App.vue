<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title class="headline">
        <span>RoyalBash</span>
      </v-toolbar-title>
    </v-app-bar>

    <v-content v-if="gamePicked">
      <game/>
    </v-content>
    <v-content v-else>
      <game-picker/>
    </v-content>
  </v-app>
</template>

<script>
import Game from "./components/game/Game.vue";
import GamePicker from "./components/gamepicker/GamePicker.vue"

export default {
  name: 'App',
  components: {
    Game,
    GamePicker,
  },
  data: () => ({}),
  computed: {
    gamePicked() { return this.$store.state.game }
  }
};
</script>
