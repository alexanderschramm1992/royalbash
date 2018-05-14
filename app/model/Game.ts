export interface Game {

    readonly id: string,
    readonly accountRed: string,
    readonly accountBlue: string,
    readonly board: Board
}

export interface Board {

    readonly id: string,
        readonly turn: Turn,
        readonly playerBlue: Player,
        readonly playerRed: Player
}

export interface Turn {

    readonly counter: number,
    readonly playerId: string
}

export interface Player {

    readonly id: string,
    readonly accountId: string,
    readonly summoningDeck: Deck,
    readonly resourcesDeck: Deck,
    readonly graveyard: Graveyard,
    readonly hand: Hand,
    readonly health: number,
    readonly targets: Array<Target>
}

export interface Deck {

    readonly id: string,
    readonly cards: Array<SummoningCard>
}

export interface Graveyard {

    readonly cards: Array<SummoningCard>
}

export interface Hand {

    readonly summoningCards: Array<SummoningCard>
}

export interface SummoningCard {

    readonly id: string,
    readonly name: string,
    readonly image: string,
    readonly type: string,
    readonly subType: string,
    readonly text: string,
    readonly lore: string,
    readonly costRations: number,
    readonly costMaterial: number,
    readonly costBlessing: number,
    readonly strength: number,
    readonly health: number
}

export interface Target {
    
    readonly id: string,
    readonly summoning: Summoning
}

export interface Summoning {

    readonly id: string,
    readonly summoningCard: SummoningCard,
    readonly currentCost: number,
    readonly currentHealth: number,
    readonly currentStrength: number
}

export default Game;
