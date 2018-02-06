import React from 'react';
import {render} from 'react-dom';

// import Login from './login_register/Login.jsx';
import Creature from "./card/Creature.jsx";

class App extends React.Component {
    render () {
        return <Creature
            id = "Creature Card"
            name = "Lorem Ipsum Lorem Ipsum Lorem Ipsum"
            image = ""
            type = "Creature - Lorem Ipsum Lorem"
            cost = "99"
            strength = "99"
            health = "99"
        />;
    }
}

render(<App/>, document.getElementById('app'));
