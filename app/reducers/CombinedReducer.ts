import { AnyAction, Reducer } from "redux";
import { StateModel } from "../Store";
import handleDrawCard from "./DrawCardHandler";
import handleMouseOnCard from "./MouseOnCardHandler";
import handleMouseOnSummoning from "./MouseOnSummoningHandler";

const initialState: StateModel =  {
    playerId: "8dbc6953-e25e-49f0-a298-7a0ea721de6c",
    drawCardIssued: false,
    hand: [],

    cardOnPreview: null,
    summoningOnPreview: null,

    cardModels: [
        {
            id: "5d10c3a2-78e5-4463-85e0-57e279cac82c",
            name: "Veteran Knight",
            image: "/img/veteran_knight.jpg",
            type: "Knight",
            text: "N/A",
            cost: 4,
            strength: 3,
            health: 4
        }
    ],
    summoningModels: [
        {
            id: "49b20e63-9b09-4c3c-b1b2-b3337e72d1c4",
            card: {
                id: "5d10c3a2-78e5-4463-85e0-57e279cac82c",
                name: "Veteran Knight",
                image: "/img/veteran_knight.jpg",
                type: "Knight",
                text: "N/A",
                cost: 4,
                strength: 3,
                health: 4
            },
            currentStrength: 3,
            currentHealth: 4 
        }
    ]
};

export const combinedReducers: Reducer<StateModel, AnyAction> =
    (state = initialState, action): StateModel => {

        state = handleDrawCard(state, action);
        state = handleMouseOnCard(state, action);
        state = handleMouseOnSummoning(state, action);
        return state;
    };

export default combinedReducers;
