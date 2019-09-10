import App from './components/gamepicker/App.js';

new Vue({
    render: h => h(App),
    el: '#app',
    vuetify: new Vuetify(),
    created: () => this.vuetify.theme.dark = true,
}).$mount(`#app`);
