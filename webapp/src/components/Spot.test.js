// React
import React from "react";
// Enzyme
import Enzyme from "enzyme";
import { mount } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
Enzyme.configure({adapter: new Adapter()});
// Internals
import Spot from "./Spot";

describe("player component", () => {

    const store = {getState: () => {return {}}};

    it("renders without crashing", () => {
        mount(<Spot store={store}/>).unmount();
    });


});