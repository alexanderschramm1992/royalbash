function own(playerId, game) {
    if (game.player1.id === playerId) return game.player1;
    if (game.player2.id === playerId) return game.player2;
    console.error(`Cannot determine Player with Id ${playerId} in Game ${game.id}`)
}

function opponent(playerId, game) {
    if (game.player1.id === playerId) return game.player2;
    if (game.player2.id === playerId) return game.player1;
    console.error(`Cannot determine Opponent of Player with Id ${playerId} in Game ${game.id}`)
}

export {own, opponent}
