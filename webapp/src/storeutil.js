const gameDefinedAndPlayerChosen = function(store) {
    return store.getState().game && store.getState().chosenPlayer;
};

export const getChosenPlayer = function(store) {
    return gameDefinedAndPlayerChosen(store) ? store.getState().game[store.getState().chosenPlayer] : undefined;
};

export const getHandcards = function(store) {

    if(gameDefinedAndPlayerChosen(store)) {
        return getChosenPlayer(store).handcards;
    }
    return [];
};

export const getSpots = function(store) {

    if(gameDefinedAndPlayerChosen(store)) {
        return getChosenPlayer(store).spots;
    }
    return [];
};
