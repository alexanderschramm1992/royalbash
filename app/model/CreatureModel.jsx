class CreatureModel {

    constructor(
        id,
        name,
        image,
        type,
        subType,
        text,
        lore,
        cost,
        strength,
        health,
    ) {

        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.subType = subType;
        this.text = text;
        this.lore = lore;
        this.cost = cost;
        this.strength = strength;
        this.health = health;
    }
}

export default CreatureModel