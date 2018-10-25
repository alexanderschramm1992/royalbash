// React
import React from "react";
// Enzyme
import Enzyme from "enzyme";
import { mount } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
Enzyme.configure({adapter: new Adapter()});
// Jest
let { describe, it } = global;
// Chai
import * as chai from "chai";
import Card from "./Card";
const expect = chai.expect;

describe("card component", () => {

    it("renders without crashing", () => {

        // Given
        let card = {
            name: "name",
            text: "text",
            cost: 0
        };

        // When Then
        mount(<Card key="key" card={card}/>).unmount();
    });
});