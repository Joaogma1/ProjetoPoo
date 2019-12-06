import React from "react";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import App from '../Pages/App/index'
import Detail from '../Pages/Details/index'

export default function AppRouter() {
return(
    <Router>
      <div>
        <Switch>
          <Route exact path="/" component={App} />
          <Route path="/detalhes/:id" component={Detail} />
        </Switch>
      </div>
    </Router>
);   
}