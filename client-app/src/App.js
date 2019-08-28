import React, { Fragment, useState } from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import Login from "./features/login";
import DashBoard from "./features/dashboard";

function App() {
  const [token, setToken] = useState("");

  function handleTokenChange(token) {
    setToken(token);
  }

  return (
    <Fragment>
      <CssBaseline />
      {token ? <DashBoard /> : <Login onTokenUpdate={handleTokenChange} />}
    </Fragment>
  );
}

export default App;
