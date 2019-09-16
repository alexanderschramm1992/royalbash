const Board = () => import('./Board.js');

export default {
    name: 'App',
    components: {
        Board
    },
    template: `
    <div id="app">
      <board/>
    </div>
  `
};
