import React from "react"
import {Done, HelpOutline} from "@material-ui/icons";
import {withStyles} from "@material-ui/core";

const styles = theme => ({
    root: {
        float: 'right',
    },
    button: {
        border: '2px solid',
        borderRadius: '50%',
        height: '32px',
        width: '32px',
        padding: '3px',
        marginLeft: 'auto',
        marginRight: 'auto',
        display: 'block',
        float: 'right',
    },
    buttonInvited: {
        border: 'none',
        borderRadius: '50%',
        height: '32px',
        width: '32px',
        padding: '3px',
        marginLeft: 'auto',
        marginRight: 'auto',
        display: 'block',
        float: 'right',
        color: '#e2e2e2',
    },
    text: {
        fontFamily: "Work Sans",
        fontSize: '11px',
        fontWeight: '500',
        lineHeight: '16px',
        color: theme.palette.primary.main,
        marginLeft: 'auto',
        marginRight: '11px',
        float: 'left',
    },
});

class AcceptedButton extends React.Component {

    constructor(props) {
        super();

        this.state = {
            invited: props.invited || false,
        }
    }

    render() {
        const {classes} = this.props;
        const {text} = this.props;

        const invited = this.state.invited;

        let styleButton = {};
        if(text)
            styleButton = {height: '12px', width:'12px', padding: '1px', borderWidth: '1px', marginRight: '16px', marginTop: '3px', marginBottom: '11px'};

        return (
            <div className={classes.root}>
                {(text
                        ? <span className={classes.text} >{text}</span>
                        : ""
                )}
                {(invited)
                    ? <HelpOutline style={styleButton} color="primary" className={classes.buttonInvited} />
                    : <Done style={styleButton} color="primary" className={classes.button} />
                }
            </div>
        )
    }
}
export default withStyles(styles)(AcceptedButton);