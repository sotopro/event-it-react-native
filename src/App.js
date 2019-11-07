/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import Router from './routers/index';

class App extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return <Router />;
  }
}

export default App;
