import React from "react";
import ReactDOM from "react-dom";
import Spot from "./Spot";

describe("Spot component", () => {

    const store = {getState: () => {return {}}};

    it("renders without crashing", () => {
        const div = document.createElement('div');
        ReactDOM.render(<Spot store={store}/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});
