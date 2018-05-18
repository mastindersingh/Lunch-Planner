import React from 'react';
import {withStyles} from 'material-ui/styles';
import {List} from "material-ui";
import User from "./User";

const styles = {
    list: {
        padding: 0,
        marginTop: '0px !important',
    },
};

class UserList extends React.Component {

    constructor(props) {
        super();

        this.state = {
            selectedUsers: props.selectedUsers || [],
            users: props.users || [],
            selectable: props.selectable || false,
            onSelectionChanged: props.onSelectionChanged,
        };

        console.log("const")
        console.log(props.selectedUsers);
    }

    componentWillReceiveProps(newProps) {
        let selectedUsers, users;

        if(newProps.selectedUsers && newProps.selectedUsers !== this.state.selectedUsers) {
            selectedUsers = newProps.selectedUsers;
        }

        if(newProps.users && newProps.users !== this.state.users) {
            users = newProps.users;
        }

        if(users) {
            this.setState({
                users: users || this.state.users,
                selectedUsers: selectedUsers || this.state.selectedUsers,
            });
        }
    }

    clickHandler = (username, selected) => {
        if(this.state.selectable) {
            let selectedUsers = this.state.selectedUsers;
            if (selected) {
                selectedUsers.push(username);
            } else {
                let index = selectedUsers.indexOf(username);
                selectedUsers.splice(index, 1);
            }

            this.setState({
                selectedUsers: selectedUsers,
            });

            this.state.onSelectionChanged(selectedUsers);
        }
    };

    render() {
        const { classes } = this.props;
        let users = this.state.users;
        let selectedUsers = this.state.selectedUsers;

        return (
            <List
                className={classes.list}
            >
                {users.map((listValue) => {
                    return <User selectable={this.state.selectable} selected={selectedUsers.includes(listValue.userName)} username={listValue.userName} onClick={this.clickHandler}/>;
                })}
            </List>
        );
    }
}

export default withStyles(styles, { withTheme: true })(UserList);