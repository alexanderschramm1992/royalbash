const GamePicker = () => import('./GamePicker.js');

export default {
  name: 'App',
  components: {
    GamePicker
  },
  template: `
    <div id="app">
      <game-picker/>
    </div>
  `
};
