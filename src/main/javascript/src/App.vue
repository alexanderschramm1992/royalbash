<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title class="headline">
        <span>RoyalBash</span>
      </v-toolbar-title>
    </v-app-bar>

    <v-content>
      <game-picker/>
    </v-content>
  </v-app>
</template>

<script>
import GamePicker from "./components/gamepicker/GamePicker.vue"

export default {
  name: 'App',
  components: {
    GamePicker,
  },
  data: () => ({}),
};
</script>
