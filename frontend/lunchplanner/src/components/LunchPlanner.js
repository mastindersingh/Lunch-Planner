import React from "react";
import EventContainer, {needReload} from "./Event/EventContainer";
import BottomNavigationBar from "./BottomNavigationBar";
import {setAuthenticationHeader} from "./authentication/LoginFunctions";

class LunchPlanner extends React.Component {

    constructor(props) {
        super();
        this.state = {
            search: props.location.state,
        };
        setAuthenticationHeader();
    }

    componentWillReceiveProps(newProps) {
        if(newProps.search !== this.state.search){
            this.setState({
                search: newProps.search,
            });
        }
    }

    componentDidMount() {
        this.setState({
            search: this.props.searchValue,
        });
    }

    render() {
        return (
            <EventContainer style={{height: '100%'}} search={this.state.search}/>
        )
    }

}

export default LunchPlanner;
