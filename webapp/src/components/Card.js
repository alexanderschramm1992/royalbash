import React from "react";

class Card extends React.Component {

    constructor(props) {
        super(props);
        this.handleDragStart = this.handleDragStart.bind(this);
    }

    handleDragStart(event) {
        event.dataTransfer.setData("type", "card");
        event.dataTransfer.setData("payload", this.props.card);
        console.log(this.props.card.name + " is being dragged");
    }

    static contentView(card, cardWidth, cardHeight) {
        return (
            <div className="card-content-wrapper" style={{
                position: "relative"
            }}>
                <img src="images/tcgcarddesign/gray_wood/card_title/frame_grey_wood.png"
                     alt="card-content-background"
                     style={{
                         zIndex: "2",
                         width: cardWidth + "px",
                         height: cardHeight + "px",
                         position: "absolute",
                         left: "0",
                         top: "0"
                     }}/>
                <div className="name" style={{
                        WebkitTextStroke: "1px black",
                        textAlign: "center",
                        lineHeight: "15px",
                        fontSize: "20px",
                        fontFamily: "'Luckiest Guy', cursive",
                        zIndex: "5",
                        width: cardWidth - 20 + "px",
                        position: "absolute",
                        left: "10px",
                        top: "210px"
                    }}>{card.name}</div>
                <div className="card-content" style={{
                    width: cardWidth - 10 + "px",
                    height: cardHeight - 10 + "px",
                    position: "absolute",
                    top: "5px",
                    left: "5px"
                }}>
                    {Card.costView(card, cardWidth)}
                    {Card.imageView(card, cardWidth)}
                    {Card.statsView(card, cardWidth)}
                </div>
            </div>
        )
    }

    static costView(card, cardWidth) {
        const paddingLeft = "-5px";
        const paddingTop = "-5px";
        return (
            <div className="cost-wrapper" style={{
                height: "0",
                position: "relative"
            }}>
                <img src="images/tcgcarddesign/gray_wood/card_title/slot_grey_wood.png"
                     alt="cost-frame"
                     style={{
                         zIndex: "1",
                         width: "40px",
                         height: "40px",
                         position: "absolute",
                         left: paddingLeft,
                         top: paddingTop
                     }}/>
                <div className="cost" style={{
                    color: "white",
                    WebkitTextStroke: "1px black",
                    textAlign: "center",
                    lineHeight: "40px",
                    fontSize: "20px",
                    fontFamily: "'Luckiest Guy', cursive",
                    zIndex: "5",
                    width: "40px",
                    position: "absolute",
                    left: paddingLeft,
                    top: paddingTop
                }}>{card.cost}</div>
            </div>
        )
    }

    static imageView(card, cardWidth) {
        return (
            <div className="image-wrapper" style={{
                position: "relative",
                height: "0"
            }}>
                <img src={"images/cards/fantasycharacters/" + card.image}
                     alt={card.name}
                     style={{
                         width: cardWidth - 10 + "px",
                         height: "220px"
                     }}
                />
            </div>
        )
    }

    static statsView(card, cardWidth) {

        const attackLeftPadding = "5px";
        const healthLeftPadding = "145px";
        const topPadding = "245px";
        const zIndex = "3";
        const width = "40px";
        const height = "40px";
        return (
            <div className="stats-wrapper" style={{
                position: "relative",
                height: "0"
            }}>
                <img src="images/tcgcarddesign/gray_wood/card_title/slot_grey_wood.png"
                     alt="attack-frame"
                     style={{
                         zIndex: zIndex,
                         width: width,
                         height: height,
                         position: "absolute",
                         left: attackLeftPadding,
                         top: topPadding
                     }}/>
                <div className="attack" style={{
                    color: "white",
                    WebkitTextStroke: "1px black",
                    textAlign: "center",
                    lineHeight: height,
                    fontSize: "20px",
                    fontFamily: "'Luckiest Guy', cursive",
                    width: width,
                    position: "absolute",
                    left: attackLeftPadding,
                    top: topPadding
                }}>{card.attack}</div>
                <img src="images/tcgcarddesign/gray_wood/card_title/slot_grey_wood.png"
                     alt="health-frame"
                     style={{
                         zIndex: zIndex,
                         width: width,
                         height: height,
                         position: "absolute",
                         left: healthLeftPadding,
                         top: topPadding
                     }}/>
                <div className="attack" style={{
                    color: "white",
                    WebkitTextStroke: "1px black",
                    textAlign: "center",
                    lineHeight: height,
                    fontSize: "20px",
                    fontFamily: "'Luckiest Guy', cursive",
                    width: width,
                    position: "absolute",
                    left: healthLeftPadding,
                    top: topPadding
                }}>{card.health}</div>
            </div>
        )
    }

    render() {

        const card = this.props.card;

        const cardWidth = 200;
        const cardHeight = 300;

        return (
            <div className="Card"
                 draggable={this.props.store.getState().onTurn}
                 style={{
                     width: cardWidth + "px",
                     height: cardHeight + "px"
                 }}
                 onDragStart={this.handleDragStart}>
                {Card.contentView(card, cardWidth, cardHeight)}
            </div>
        );
    }
}

export default Card;