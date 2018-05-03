// App.js
import React from "react";
import MyLogin from "./components/authentication/Login"
import MyRegistration from "./components/authentication/Registration"
import FirstScreen from "./components/authentication/Authentication"
import LunchPlanner from "./components/LunchPlanner"
import { BrowserRouter as Router, Route, Redirect } from "react-router-dom";
import {isAuthenticated} from "./components/authentication/LoginFunctions"
import { MuiThemeProvider, createMuiTheme } from 'material-ui/styles';

/*
TODO:
- Logik Registration und MyRegistration verbinden
- Logik Login und MyLogin verbinden
- Login/Register Button unten fixieren, Schrift weiß
- Events Button rechte Seite
- Events Login/Register nicht anzeigen
 */


const theme = createMuiTheme({
    palette: {
        primary: {main: "#75a045"},
        secondary: {main: '#f29b26'},
    },

});

function isAuth() {
    return isAuthenticated();
}

const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route{...rest} render={props =>
            isAuth() ? (<Component {...props} />)
                : (<Redirect to={{
                        pathname: "/login",
                        state: { from: props.location }}}/>)}/>
);

class App extends React.Component {

    render() {
        return (
            <MuiThemeProvider theme={theme}>
                <Router>
                    <div>
                        <PrivateRoute exact path="/" component={LunchPlanner} />
                        <Route path="/login" component={FirstScreen} />
                    </div>
                </Router>
            </MuiThemeProvider>
        );
    }
}

export default App;