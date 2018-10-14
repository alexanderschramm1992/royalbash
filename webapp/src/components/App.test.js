import React from 'react';
let { describe, it } = global;
import ReactDOM from 'react-dom';
import App from './App';

describe("the application", () => {

    const store = {
        dispatch: function(action) {
            this.actions.push(action);
        },
        subscribe: function(methodToTrigger) {

        },
        actions: []
    };

    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<App store={store}/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});
