import React from "react";

import "./CardContainer.css";
import Creature from "../card/Creature.jsx";

class CardContainer extends React.Component {

    constructor(props) {

        super(props);
        this.state = {
            cardModels: []
        };

        this.state.cardModels = props.cardModels ? props.cardModels : [];
    }

    render() {

        return (
            <div className="card-container">
                <div className="card-placeholder card-placeholder-0">
                    {this.state.cardModels[0] &&
                        <Creature
                            id = {this.state.cardModels[0].id}
                            name = {this.state.cardModels[0].name}
                            image = {this.state.cardModels[0].image}
                            type = {this.state.cardModels[0].type}
                            subType = {this.state.cardModels[0].subType}
                            text = {this.state.cardModels[0].text}
                            lore = {this.state.cardModels[0].lore}
                            cost = {this.state.cardModels[0].cost}
                            strength = {this.state.cardModels[0].strength}
                            health = {this.state.cardModels[0].health}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-1">
                    {this.state.cardModels.size[1] &&
                        <Creature
                            id = {this.state.cardModels[1].id}
                            name = {this.state.cardModels[1].name}
                            image = {this.state.cardModels[1].image}
                            type = {this.state.cardModels[1].type}
                            subType = {this.state.cardModels[1].subType}
                            text = {this.state.cardModels[1].text}
                            lore = {this.state.cardModels[1].lore}
                            cost = {this.state.cardModels[1].cost}
                            strength = {this.state.cardModels[1].strength}
                            health = {this.state.cardModels[1].health}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-2">
                    {this.state.cardModels.size[2] &&
                        <Creature
                            id = {this.state.cardModels[2].id}
                            name = {this.state.cardModels[2].name}
                            image = {this.state.cardModels[2].image}
                            type = {this.state.cardModels[2].type}
                            subType = {this.state.cardModels[2].subType}
                            text = {this.state.cardModels[2].text}
                            lore = {this.state.cardModels[2].lore}
                            cost = {this.state.cardModels[2].cost}
                            strength = {this.state.cardModels[2].strength}
                            health = {this.state.cardModels[2].health}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-3">
                    {this.state.cardModels.size[3] &&
                        <Creature
                            id = {this.state.cardModels[3].id}
                            name = {this.state.cardModels[3].name}
                            image = {this.state.cardModels[3].image}
                            type = {this.state.cardModels[3].type}
                            subType = {this.state.cardModels[3].subType}
                            text = {this.state.cardModels[3].text}
                            lore = {this.state.cardModels[3].lore}
                            cost = {this.state.cardModels[3].cost}
                            strength = {this.state.cardModels[3].strength}
                            health = {this.state.cardModels[3].health}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-4">
                    {this.state.cardModels.size[4] &&
                        <Creature
                            id = {this.state.cardModels[4].id}
                            name = {this.state.cardModels[4].name}
                            image = {this.state.cardModels[4].image}
                            type = {this.state.cardModels[4].type}
                            subType = {this.state.cardModels[4].subType}
                            text = {this.state.cardModels[4].text}
                            lore = {this.state.cardModels[4].lore}
                            cost = {this.state.cardModels[4].cost}
                            strength = {this.state.cardModels[4].strength}
                            health = {this.state.cardModels[4].health}
                        />
                    }
                </div>
            </div>
        );
    }
}

export default CardContainer;
