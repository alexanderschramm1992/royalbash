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
                <img src="images/guimegapack/Adventure_UI/background/background_01.png"
                     alt="card-content-background"
                     style={{
                         width: cardWidth + "px",
                         height: cardHeight + "px",
                         position: "absolute",
                         left: "0",
                         top: "0"
                     }}/>
                <div className="card-content" style={{
                    width: cardWidth - 20 + "px",
                    height: cardHeight - 30 + "px",
                    position: "absolute",
                    top: "15px",
                    left: "10px"
                }}>
                    {Card.nameView(card, cardWidth)}
                    {Card.costView(card, cardWidth)}
                    {Card.imageView(card, cardWidth)}
                </div>
            </div>
        )
    }

    static nameView(card, cardWidth) {
        return (
            <div className="name-wrapper" style={{
                height: "30px",
                position: "relative"
            }}>
                <img src="images/guimegapack/Adventure_UI/frame/die_03.png"
                     alt="name-frame"
                     style={{
                         zIndex: "4",
                         width: cardWidth - 40 + "px",
                         height: "30px",
                         position: "absolute",
                         left: "10px",
                         top: "0"
                     }}/>
                <div className="name" style={{
                    WebkitTextStroke: "1px black",
                    textAlign: "center",
                    lineHeight: "15px",
                    fontSize: "20px",
                    fontFamily: "'Luckiest Guy', cursive",
                    zIndex: "5",
                    width: cardWidth - 40 + "px",
                    position: "absolute",
                    left: "10px",
                    top: "7px"
                }}>{card.name}</div>
            </div>
        )
    }

    static costView(card, cardWidth) {
        return (
            <div className="cost-wrapper" style={{
                height: "25px",
                position: "relative"
            }}>
                <img src="images/guimegapack/Adventure_UI/background/banner_01.png"
                     alt="cost-frame"
                     style={{
                         zIndex: "3",
                         width: cardWidth - 80 + "px",
                         height: "60px",
                         position: "absolute",
                         left: "30px",
                         top: "0"
                     }}/>
                <img src="images/guimegapack/FantasyButtons/fantasy_buttons_png/ring_bg/frame/ring_frame.PNG"
                     alt="cost-glass-button"
                     style={{
                         zIndex: "4",
                         width: "40px",
                         height: "40px",
                         position: "absolute",
                         left: "80px",
                         top: "10px"
                     }}/>
                <img src="images/guimegapack/FantasyButtons/fantasy_buttons_png/ring_bg/bg/r_bg2.PNG"
                     alt="cost-glass-button"
                     style={{
                         zIndex: "3",
                         width: "40px",
                         height: "40px",
                         position: "absolute",
                         left: "80px",
                         top: "10px"
                     }}/>
                <div className="cost" style={{
                    color: "white",
                    WebkitTextStroke: "1px black",
                    textAlign: "center",
                    lineHeight: "40px",
                    fontSize: "20px",
                    fontFamily: "'Luckiest Guy', cursive",
                    zIndex: "5",
                    width: "20px",
                    position: "absolute",
                    left: "90px",
                    top: "10px"
                }}>{card.cost}</div>
            </div>
        )
    }

    static imageView(card, cardWidth) {
        return (
            <div className="image-wrapper" style={{
                position: "relative"
            }}>
                <img src={"images/cards/fantasycharacters/" + card.image}
                     alt={card.name}
                     style={{
                         marginTop: "5px",
                         width: cardWidth - 40 + "px",
                         height: "220px"
                     }}
                />
                <img src="images/guimegapack/Adventure_UI/frame/frame.png"
                     alt="image-frame"
                     style={{
                         width: cardWidth - 30 + "px",
                         height: "230px",
                         position: "absolute",
                         left: "5px",
                         top: "0"
                     }}/>
            </div>
        )
    }

    render() {

        const card = this.props.card;

        const cardWidth = 220;
        const cardHeight = 330;

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