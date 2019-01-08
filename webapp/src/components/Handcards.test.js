import React from "react";
import ReactDOM from "react-dom";
import Handcards from "./Handcards";

describe("Handcards Component", () => {

    const store = {
        dispatch: function(action) {this.actions.push(action);},
        subscribe: function(methodToTrigger) {},
        getState: function() {return {}},
        actions: []
    };

    it("renders without crashing", () => {
        const div = document.createElement('div');
        ReactDOM.render(<Handcards store={store}/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});