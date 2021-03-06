import React from "react";
import {doLogin} from "./LoginFunctions";
import {Redirect} from "react-router-dom";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import {Input, InputLabel, InputAdornment} from '@material-ui/core';
import { FormControl, } from '@material-ui/core';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import Button from '@material-ui/core/Button';
import LoginIcon from '@material-ui/icons/ExitToApp';
const styles = theme => ({
    root: {
        display: 'flex',
        flexDirection: 'column',
        height: '100%',
        maxWidth: '600px',
        marginLeft: 'auto',
        marginRight: 'auto',
        flex: '0 1 auto',
    },
    description: {
        fontSize: '16px',
        lineHeight: '24px',
        textAlign: 'center',
        margin: '20px',
    },
    content: {
        justifyContent: 'space-between',
        flexGrow: 1,
        padding: '0 5px',
    },
    button: {
        height: '56px',
        width: '100%',
        color: 'white',
        fontSize: '15px',
    },
    margin: {
        margin: '10px 0px',
    },
    withoutLabel: {
        marginTop: theme.spacing.unit * 3,
    },
    textField: {
        flexBasis: 200,
    },
});

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            redirectToReferrer: false,
            error: "",
        };

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const name = target.id;

        this.setState({
            [name]: target.value
        });
    }

    handleSubmit(event) {
        doLogin(this.state.username, this.state.password, message => {

            if(message.type === "LOGIN_EMPTY") {
                this.setState({
                    error: message.payload.message,
                });
            } else if(message.type === "LOGIN_SUCCESS") {
                this.setState({
                    error: "",
                    redirectToReferrer: true,
                });
            } else if(message.type === "LOGIN_FAILED") {
                this.setState({
                    error: "Wrong username or password"
                });
            }
        });

        if(event)
            event.preventDefault();
    }

    handleChange = prop => event => {
        this.setState({ [prop]: event.target.value });
    };

    handleMouseDownPassword = event => {
        event.preventDefault();
    };

    handleClickShowPassword = () => {
        this.setState({ showPassword: !this.state.showPassword });
    };

    keyPress = (e) => {
        if(e.keyCode === 13){
            this.handleSubmit();
        }
    };

    render() {
        const { classes } = this.props;
        const { from } = /*this.props.location.state || */{ from: { pathname: "/" } };
        const { redirectToReferrer } = this.state;
        const { error } = this.state;

        if (redirectToReferrer) {
            return <Redirect to={from} />;
        }

        let disabled = true;
        if(this.state.username && this.state.password)
            disabled = false;

        return (
            <div className={classes.root}>
                <div className={classes.content}>
                    {(error
                            ? <div>{error}</div>
                            : ""
                    )}
                    <p className={classes.description}>
                        Welcome back. <br/> Please type in your account info!
                    </p>
                    <FormControl
                        fullWidth
                        className={classes.margin}
                        aria-describedby="weight-helper-text"
                    >
                        <Input
                            id="username"
                            placeholder="Username"
                            value={this.state.username}
                            onChange={this.handleInputChange}
                            inputProps={{
                                'aria-label': 'Username',
                            }}

                        />
                    </FormControl>
                    <FormControl
                        fullWidth
                        className={classes.margin}
                    >
                        <InputLabel htmlFor="adornment-password">Password</InputLabel>
                        <Input
                            id="password"
                            type={this.state.showPassword ? 'text' : 'password'}
                            value={this.state.password}
                            onChange={this.handleInputChange}
                            onKeyDown={this.keyPress}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="Toggle password visibility"
                                        onClick={this.handleClickShowPassword}
                                        onMouseDown={this.handleMouseDownPassword}
                                    >
                                        {this.state.showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>
                                </InputAdornment>
                            }
                        />
                    </FormControl>
                </div>
                <Button disabled={disabled} fullWidth variant="raised" color="secondary" className={classes.button} onClick={this.handleSubmit}>
                    <LoginIcon color={"#FFFFF"}/>LOGIN
                </Button>
            </div>
        );
    }
}

Login.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles, { withTheme: true })(Login);
