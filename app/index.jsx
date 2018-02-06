import React from 'react';
import {render} from 'react-dom';

// import Login from './login_register/Login.jsx';
import Creature from "./card/Creature.jsx";

class App extends React.Component {
    render () {
        return <Creature />;
    }
}

render(<App/>, document.getElementById('app'));
