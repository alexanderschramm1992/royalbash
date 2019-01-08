import React from "react";
import ReactDOM from "react-dom";
import Card from "./Card";

describe("Card Component", () => {

    const card = {
        name: "name",
        text: "text",
        cost: 0
    };

    const store = {getState: () => {return {onTurn: true}}};

    it("renders without crashing", () => {
        const div = document.createElement('div');
        ReactDOM.render(<Card key="key" card={card} store={store}/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });

    it("creates a drag event with respective payload and type", () => {

        // Given
        let testee = ReactDOM.render(<Card key="key" card={card} store={store}/>, document.createElement('div'));
        let event = {dataTransfer: {
            setData: function(key, data) {this[key] = data}
        }};

        // When
        testee.handleDragStart(event);

        // Then
        expect(event.dataTransfer.type).toBe("card");
        expect(event.dataTransfer.payload).toBe(card);
    })
});