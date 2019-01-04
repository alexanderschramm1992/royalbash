export const getChosenPlayer = function(store) {
    return store.getState().gameLoaded ? store.getState().game[store.getState().chosenPlayer] : undefined;
};

export const getHandcards = function(store) {
    return store.getState().gameLoaded ? getChosenPlayer(store).handcards : [];
};
