import GamePicker from "./GamePicker.js";

export default {
  name: 'App',
  components: {
    GamePicker
  },
  template: `
    <div id="app">
        <v-app>
            <game-picker/>
        </v-app>
    </div>
  `
};
