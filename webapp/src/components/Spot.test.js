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
import Spot from "./Spot";

describe("player component", () => {

    const store = {getState: () => {return {}}};

    it("renders without crashing", () => {
        mount(<Spot store={store}/>).unmount();
    });


});