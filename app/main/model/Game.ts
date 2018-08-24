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
    readonly currentTurnPlayerId: string,
    readonly playerBlueId: string,
    readonly playerRedId: string
}

export interface Player {

    readonly id: string,
    readonly accountId: string,
    readonly resourcePool: ResourcePool,
    readonly summoningDeck: SummoningDeck,
    readonly resourcesDeck: ResourcesDeck,
    readonly graveyard: Graveyard,
    readonly hand: Hand,
    readonly health: number,
    readonly field: Field
}

export interface ResourcePool {

    readonly rations: number,
    readonly material: number,
    readonly blessing: number,
}

export interface SummoningDeck {

    readonly id: string,
    readonly cards: Array<SummoningCard>
}

export interface ResourcesDeck {

    readonly id: string,
    readonly cards: Array<ResourcesCard>
}

export interface Graveyard {

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
    readonly cardType: string
}

export interface SummoningCard extends Card {

    readonly strength: number,
    readonly health: number,
}

export interface ResourcesCard extends Card {

}

export interface Field {

    readonly targets: Array<Target>
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
    readonly currentStrength: number,
    readonly canAttack: boolean
}

export default Game;
