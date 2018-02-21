import * as React from "react";
import * as ReactDOM from "react-dom";

import Creature from "./components/card/Creature";

ReactDOM.render(
    <Creature
        id = "1"
        name = "Lorem Ipsum"
        image = "N/A"
        type = "Creature"
        subType = "Lorem Ipsum"
        text = "N/A"
        lore = "N/A"
        cost = {99}
        strength = {99}
        health = {99}
    />,
    document.getElementById("app")
);