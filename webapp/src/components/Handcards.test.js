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
const expect = chai.expect;
// Internals
import Handcards from "./Handcards";

describe("Handcards", () => {

    const store = {
        dispatch: function(action) {this.actions.push(action);},
        subscribe: function(methodToTrigger) {},
        getState: function() {return {}},
        actions: []
    };

    it("renders without crashing", () => {
        mount(<Handcards store={store}/>).unmount();
    });
});