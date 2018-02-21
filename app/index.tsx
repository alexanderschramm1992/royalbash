import * as React from "react";
import * as ReactDOM from "react-dom";

import Creature from "./components/card/Creature";
import CardContainer from "./components/board/CardContainer";

const cards: Creature[] = new Array<Creature>(
  new Creature({
    id: "1",
    name: "Lorem Ipsum",
    image: "N/A",
    type: "Creature",
    subType: "Lorem Ipsum",
    text: "N/A",
    lore: "N/A",
    cost: 99,
    strength: 99,
    health: 99
  })
);

/*
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
/>
*/

ReactDOM.render(
    <div>
      <CardContainer
        cards = {cards}
      />
    </div>
    ,
    document.getElementById("app")
);
