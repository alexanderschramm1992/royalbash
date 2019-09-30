import Board from "./Board.js";

export default {
    name: 'App',
    components: {
        Board
    },
    template: `
    <div id="app">
        <v-app>
            <board/>
        </v-app>
    </div>
  `
};
