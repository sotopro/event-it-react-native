import React, {Component} from 'react';
import {StyleSheet, View, Text} from 'react-native';

class SplashScreen extends Component {
  constructor(props) {
    super(props);
  }
  async componentDidMount() {
    const data = await this.performTimeConsumingTask();
    if (data !== null) {
      this.props.navigation.navigate('Home');
    }
  }
  performTimeConsumingTask = async () => {
    return new Promise(resolve =>
      setTimeout(() => {
        resolve('result');
      }, 2000),
    );
  };
  render() {
    const viewStyles = {
      backgroundColor: 'orange',
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center',
    };
    const textStyles = {
      color: 'white',
      fontSize: 40,
      fontWeight: 'bold',
      justifyContent: 'center',
      alignItems: 'center',
    };
    return (
      <View style={viewStyles}>
        <Text style={textStyles}>Splash Screen</Text>
      </View>
    );
  }
}

export default SplashScreen;
