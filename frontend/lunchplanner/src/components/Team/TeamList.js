import React from "react"
import axios from "axios"

import {HOST} from "../../Config"
import Team from "./Team";
import List from "material-ui/List";
import {withStyles} from "material-ui/styles/index";
import {Link} from "react-router-dom";
import FloatingActionButton from "../FloatingActionButton";

const styles = {
    root: {
        height: '100%',
        overflow: 'hidden',
    },
    list: {
        padding: 0,
    },
};

class TeamList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            teams: [],
            search:null,
        }
    }

    componentDidMount() {
        this.setState({
            search: this.props.search,
        });

        let url;
        if(this.props.search)
            url = HOST + "/team/search/" + this.props.search;
        else
            url = HOST + "/team";

        axios.get(url)
            .then((response) => {
                console.log(response.data);
                this.setState({
                    teams: response.data,
                })
            })
    }

    render() {
        const { classes } = this.props;
        let teams = this.state.teams;
        return (
            <div className={classes.root}>
                <List className={classes.list}>
                    {teams.map((listValue)=>{
                        return <Team name={listValue.teamName}
                                      id={listValue.teamId}
                                     member={listValue.invitations}
                        />;
                    })}
                </List>
                <Link to="/team/create"><FloatingActionButton /></Link>
            </div>

        );
    }
}

export default withStyles(styles)(TeamList);