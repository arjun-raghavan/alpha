import React, { Fragment } from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import { BrowserRouter, Route } from "react-router-dom";
import Login from "./features/login";
import DashBoard from "./features/dashboard";

function App() {
  return (
    <BrowserRouter>
      <Fragment>
        <CssBaseline />
        <Route path="/" exact component={Login} />
        <Route path="/login" exact component={Login} />
        <Route path="/register" exact component={Login} />
        <Route path="/dashboard" exact component={DashBoard} />
      </Fragment>
    </BrowserRouter>
  );
}

export default App;
