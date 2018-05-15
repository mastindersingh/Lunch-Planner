import React from 'react';
import PropTypes from 'prop-types';
import {withStyles} from 'material-ui/styles';
import SwipeableViews from 'react-swipeable-views';
import AppBar from 'material-ui/AppBar';
import Tabs, {Tab} from 'material-ui/Tabs';
import Typography from 'material-ui/Typography';
import Appbar from "./Appbar";
import Teamlist from "./Team/TeamList";
import BottomNavigationBar from "./BottomNavigationBar";
import {setAuthenticationHeader} from "./authentication/Authentication";


function TabContainer({ children, dir }) {
    return (
        <Typography component="div" dir={dir} style={{ padding: 0 }}>
            {children}
        </Typography>
    );
}

TabContainer.propTypes = {
    children: PropTypes.node.isRequired,
    dir: PropTypes.string.isRequired,
};

const styles = theme => ({
    root: {
        backgroundColor: theme.palette.background.paper,
        //width: 1500,,
        position: 'relative',
        marginTop: '56px',
        marginBottom: '56px',
    },
    fab: {
        position: 'absolute',
        bottom: theme.spacing.unit * 2,
        right: theme.spacing.unit * 2,
    },
    whiteSymbol: {
        color: theme.palette.common.white
    },
    tab: {
        fontFamily: "Work Sans",
        fontWeight: '600',
        letterSpacing: '0.65px',
        fontSize: '13px',
    },
});

class SocialScreen extends React.Component {

    constructor(props) {
        super();
        this.state = {
            value: 0,
        };

        setAuthenticationHeader();
    }


    handleChange = (event, value) => {
        this.setState({ value });
    };

    handleChangeIndex = index => {
        this.setState({ value: index });
    };

    render() {
        const { classes, theme } = this.props;

        return (
            <div className={classes.root}>
                <Appbar currentScreen="Social"/>
                <AppBar position="static" color="default">
                    <Tabs
                        value={this.state.value}
                        onChange={this.handleChange}
                        indicatorColor="secondary"
                        textColor="secondary"
                        centered
                        fullWidth
                    >
                        <Tab className={classes.tab} label="PERSON" />
                        <Tab className={classes.tab} label="TEAMS" />
                    </Tabs>
                </AppBar>
                <SwipeableViews
                    axis={theme.direction === 'rtl' ? 'x-reverse' : 'x'}
                    index={this.state.value}
                    onChangeIndex={this.handleChangeIndex}
                >

                    <TabContainer dir={theme.direction}></TabContainer>
                    <TabContainer dir={theme.direction}>
                        <Teamlist/>
                    </TabContainer>
                </SwipeableViews>
                <BottomNavigationBar />
            </div>
        );
    }
}

SocialScreen.propTypes = {
    classes: PropTypes.object.isRequired,
    theme: PropTypes.object.isRequired,
};

export default withStyles(styles, { withTheme: true })(SocialScreen);