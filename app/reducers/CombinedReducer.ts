import { AnyAction, Reducer } from "redux";
import { StateModel } from "../Store";
import handleDrawCard from "./DrawCardHandler";
import handleMouseOnCard from "./MouseOnCardHandler";

const initialState: StateModel =  {
    playerId: "8dbc6953-e25e-49f0-a298-7a0ea721de6c",
    drawCardIssued: false,
    hand: [],

    cardOnPreview: null,

    cardModels: [
        {
            id: "5d10c3a2-78e5-4463-85e0-57e279cac82c",
            name: "Veteran Knight",
            image: "N/A",
            type: "Knight",
            text: "N/A",
            cost: 4,
            strength: 3,
            health: 4
        }
    ]
};

export const combinedReducers: Reducer<StateModel, AnyAction> =
    (state = initialState, action): StateModel => {

        state = handleDrawCard(state, action);
        state = handleMouseOnCard(state, action);
        return state;
    };

export default combinedReducers;
