import * as React from "react";
import * as ReactDOM from "react-dom";

import BoardContainer from "./components/game/BoardContainer";

ReactDOM.render(
    <div>
      <BoardContainer
        gameId="6d5864f4-5fb1-4615-bf6a-07a1211ef6d3"
        playerId="8dbc6953-e25e-49f0-a298-7a0ea721de6c"
      />
    </div>
    ,
    document.getElementById("app")
);
