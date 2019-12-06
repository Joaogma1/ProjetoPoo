import React from "react";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import App from '../Pages/App/index'

export default function AppRouter() {
return(
    <Router>
      <div>
        <Switch>
          <Route exact path="/" component={App} />
        </Switch>
      </div>
    </Router>
);   
}