// React
import React from "react";
// Enzyme
import Enzyme from "enzyme";
import { mount } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
Enzyme.configure({adapter: new Adapter()});
// Internals
import Card from "./Card";

describe("card component", () => {

    const card = {
        name: "name",
        text: "text",
        cost: 0
    };

    const store = {getState: () => {return {onTurn: true}}};

    it("renders without crashing", () => {
        mount(<Card key="key" card={card} store={store}/>).unmount();
    });

    it("creates a drag event with respective payload and type", () => {

        // Given
        let testee = mount(<Card key="key" card={card} store={store}/>).instance();
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