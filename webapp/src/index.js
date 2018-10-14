import "./index.css";
import React from "react";
import ReactDOM from "react-dom";
import App from "./components/App";
import registerAjaxController from "./ajax";
import {Store} from "./store";

registerAjaxController();
ReactDOM.render(<App store={Store}/>, document.getElementById("root"));