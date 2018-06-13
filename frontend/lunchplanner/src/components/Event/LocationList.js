import React from "react"

import {HOST} from "../../Config"
import Event from "./EventLocation";
import List from "@material-ui/core/List";
import {withStyles} from "@material-ui/core/styles/index";
import {Link} from "react-router-dom";
import FloatingActionButton from "../FloatingActionButton";
import {getUsername} from "../authentication/LoginFunctions";
import {getEvents} from "./EventFunctions";
import {needReload} from "./EventContainer";
import Divider from "@material-ui/core/es/Divider/Divider";
import GPSIcon from "@material-ui/icons/GpsFixed";
import ListItem from "@material-ui/core/es/ListItem/ListItem";
import ListItemText from "@material-ui/core/es/ListItemText/ListItemText";
import ListItemSecondaryAction from "@material-ui/core/es/ListItemSecondaryAction/ListItemSecondaryAction";
import ListItemIcon from "@material-ui/core/es/ListItemIcon/ListItemIcon";
import ListSubheader from "@material-ui/core/es/ListSubheader/ListSubheader";

const styles = {
    root: {
        height: '100%',
        fontSize: 16,
    },
    list: {
        padding: 0,
    },
    row: {
        float: "left",
        marginLeft: "auto",
    },
    locationText:{
        fontSize: 16,
        marginTop: 15,
        marginLeft: 24,
        fontFamily: "Work Sans",
    },
    icon:{
        float: "right",
        marginTop:-23,
        marginRight: 24,
        color: "#1EA185",
    },
};

class LocationList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            events: props.events,
            search:props.search,
            locations:[],
        }
    }

    componentWillReceiveProps(newProps) {
        if(newProps.search !== this.state.search){
            this.setState({
                search: newProps.search,
            });
        }
        if(newProps.events !== this.state.events){
            this.setState({
                events: newProps.events,
            });
        }
    }



    render() {
        const { classes } = this.props;
        let events = this.state.events || [];
        let locations = [];
        // Sort events with location name
        events.sort(function(a,b) {return (a.location.toUpperCase() >= b.location.toUpperCase()) ? 1 : ((b.location.toUpperCase() > a.location.toUpperCase()) ? -1 : 0);});
        for(let i = 0; i < events.length; i++){
            locations.push(events[i].location);
        }
        let locationsUnique = [];

        if(locations.length !== 0) {
            //remove doubles
            locationsUnique = locations.filter((item, pos) => {
                return locations.indexOf(item) === pos && !locations.some((person) => person.location === item);
            });
        }
        let isSameLocation = false;
        return (
            <div className={classes.root}>
                <List className={classes.list}>
                    {locationsUnique.map((value) => {
                        let counter = 0;
                        return(
                            <div>
                                <p className={classes.locationText}>{value}</p>
                                <GPSIcon className={classes.icon}/>
                                <ListItem>
                                    {events.map(function(listValue){
                                        if(value === locations[counter]){
                                            isSameLocation = true;
                                        }else{
                                            isSameLocation = false;
                                        }
                                        counter++;
                                        let accepted = false;
                                        let invited = false;
                                        let username = getUsername();
                                        listValue.invitations.forEach((value) => {
                                            if(value.userName === username) {
                                                invited = true;
                                                if(value.answer === 0) {
                                                    accepted = true;
                                                }
                                            }
                                        });

                                        return (
                                            <div>
                                                <div className={classes.row}>
                                                    {isSameLocation ?
                                                        (<div>
                                                            <div className={classes.row}>
                                                                <Event name={listValue.eventName}
                                                                       key={'Event' + listValue.eventId}
                                                                       id={listValue.eventId}
                                                                       description={listValue.eventDescription}
                                                                       location={listValue.location}
                                                                       date={listValue.startDate}
                                                                       accepted={accepted}
                                                                       invited={invited}
                                                                       people={listValue.invitations}
                                                                       token={listValue.shareToken}
                                                                />
                                                            </div>
                                                        </div>)
                                                        :
                                                       ""
                                                    }
                                                </div>
                                            </div>
                                        );
                                    })}

                                </ListItem>
                                <Divider />
                            </div>);
                    })
                    }
                </List>
                <Link to={{pathname:'/app/event/create'}}>
                    <FloatingActionButton />
                </Link>
            </div>);
    }
}

export default withStyles(styles)(LocationList);