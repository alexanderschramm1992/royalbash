import React from 'react';
import {render} from 'react-dom';

// import Login from './login_register/Login.jsx';
import Creature from "./components/card/Creature.jsx";
import CardContainer from "./components/board/CardContainer.jsx";

class App extends React.Component {
    render () {
        return <CardContainer
            cardModels ={
                [
                    {
                        id: "Creature Card",
                        name: "Lorem Ipsum Lorem Ipsum Lorem Ipsum",
                        image: "",
                        type: "Creature",
                        subType: "Lorem Ipsum",
                        text: "N/A",
                        lore: "N/A",
                        cost: 99,
                        strength: 99,
                        health: 99
                    }
                ]
            }
        />
    }
}

render(<App/>, document.getElementById('app'));
