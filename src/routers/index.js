import React, {Component} from 'react';
import {createAppContainer, createSwitchNavigator} from 'react-navigation';
// import {createStackNavigator} from 'react-navigation-stack';
import Splash from '../scenes/Splash';
import HomeScreen from '../scenes/Home';

// const InitialNavigator = createStackNavigator({
const InitialNavigator = createSwitchNavigator({
  Splash: Splash,
  Home: {
    screen: HomeScreen,
  },
  initialRouterName: 'Splash',
});

const AppContainer = createAppContainer(InitialNavigator);

export default class App extends Component {
  render() {
    return <AppContainer />;
  }
}
