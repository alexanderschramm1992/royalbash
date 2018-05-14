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
    readonly deck: Deck,
    readonly health: number,
    readonly hand: Hand,
    readonly targets: Array<Target>
}

export interface Deck {

    readonly id: string,
    readonly cards: Array<Card>
}

export interface Hand {

    readonly cards: Array<Card>
}

export interface Card {

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
    readonly card: Card,
    readonly currentCost: number,
    readonly currentHealth: number,
    readonly currentStrength: number
}

export default Game;
