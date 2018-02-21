import * as React from "react";

import "./CardContainer.css";
import {Creature, CreatureModel} from "../card/Creature";

export interface CardContainerModel {

    card_1: CreatureModel;
    card_2: CreatureModel;
    card_3: CreatureModel;
    card_4: CreatureModel;
    card_5: CreatureModel;
}

export class CardContainer extends React.Component<CardContainerModel, {}> {

    render(): any {

        return (
            <div className="card-container">
                <div className="card-placeholder card-placeholder-0">
                    {this.props.card_1 &&
                        <Creature
                            id = {this.props.card_1.id}
                            name = {this.props.card_1.name}
                            image = {this.props.card_1.image}
                            type = {this.props.card_1.type}
                            subType = {this.props.card_1.subType}
                            text = {this.props.card_1.text}
                            lore = {this.props.card_1.lore}
                            cost = {this.props.card_1.cost}
                            strength = {this.props.card_1.strength}
                            health = {this.props.card_1.health}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-1">
                    {this.props.size[1] &&
                        <Creature
                            id = {this.props[1].id}
                            name = {this.props[1].name}
                            image = {this.props[1].image}
                            type = {this.props[1].type}
                            subType = {this.props[1].subType}
                            text = {this.props[1].text}
                            lore = {this.props[1].lore}
                            cost = {this.props[1].cost}
                            strength = {this.props[1].strength}
                            health = {this.props[1].health}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-2">
                    {this.props.size[2] &&
                        <Creature
                            id = {this.props[2].id}
                            name = {this.props[2].name}
                            image = {this.props[2].image}
                            type = {this.props[2].type}
                            subType = {this.props[2].subType}
                            text = {this.props[2].text}
                            lore = {this.props[2].lore}
                            cost = {this.props[2].cost}
                            strength = {this.props[2].strength}
                            health = {this.props[2].health}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-3">
                    {this.props.size[3] &&
                        <Creature
                            id = {this.props[3].id}
                            name = {this.props[3].name}
                            image = {this.props[3].image}
                            type = {this.props[3].type}
                            subType = {this.props[3].subType}
                            text = {this.props[3].text}
                            lore = {this.props[3].lore}
                            cost = {this.props[3].cost}
                            strength = {this.props[3].strength}
                            health = {this.props[3].health}
                        />
                    }
                </div>
                <div className="card-placeholder card-placeholder-4">
                    {this.props.size[4] &&
                        <Creature
                            id = {this.props[4].id}
                            name = {this.props[4].name}
                            image = {this.props[4].image}
                            type = {this.props[4].type}
                            subType = {this.props[4].subType}
                            text = {this.props[4].text}
                            lore = {this.props[4].lore}
                            cost = {this.props[4].cost}
                            strength = {this.props[4].strength}
                            health = {this.props[4].health}
                        />
                    }
                </div>
            </div>
        );
    }
}

export default CardContainer;
