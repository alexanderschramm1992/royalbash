import * as React from "react";

import "./Board.css";

class Board extends React.Component<{}, {}> {

    constructor(props: any) {

        super(props);
        this.state = {};
    }

    render(): any {

        return (
            <div className="board">
                <div className="board-side board-side-top">
                    <div className="avatar-area">
                    </div>
                    <div className="hand-area">
                        <div className="card-placeholder card-placeholder-0">
                        </div>
                        <div className="card-placeholder card-placeholder-1">
                        </div>
                        <div className="card-placeholder card-placeholder-2">
                        </div>
                        <div className="card-placeholder card-placeholder-3">
                        </div>
                        <div className="card-placeholder card-placeholder-4">
                        </div>
                    </div>
                    <div className="deck-area">
                    </div>
                    <div className="play-area">
                        <div className="upper-row">
                            <div className="card-placeholder card-placeholder-0">
                            </div>
                            <div className="card-placeholder card-placeholder-1">
                            </div>
                            <div className="card-placeholder card-placeholder-2">
                            </div>
                            <div className="card-placeholder card-placeholder-3">
                            </div>
                            <div className="card-placeholder card-placeholder-4">
                            </div>
                        </div>
                        <div className="lower-row">
                            <div className="card-placeholder card-placeholder-5">
                            </div>
                            <div className="card-placeholder card-placeholder-6">
                            </div>
                            <div className="card-placeholder card-placeholder-7">
                            </div>
                            <div className="card-placeholder card-placeholder-8">
                            </div>
                            <div className="card-placeholder card-placeholder-9">
                            </div>
                        </div>
                    </div>
                    <div className="graveyard-area">
                    </div>
                </div>
                <div className="board-side board-side-bottom">
                    <div className="avatar-area">
                    </div>
                    <div className="hand-area">
                        <div className="card-placeholder card-placeholder-0">
                        </div>
                        <div className="card-placeholder card-placeholder-1">
                        </div>
                        <div className="card-placeholder card-placeholder-2">
                        </div>
                        <div className="card-placeholder card-placeholder-3">
                        </div>
                        <div className="card-placeholder card-placeholder-4">
                        </div>
                    </div>
                    <div className="deck-area">
                    </div>
                    <div className="play-area">
                    </div>
                    <div className="graveyard-area">
                    </div>
                </div>
            </div>
        );
    }
}

export default Board;
