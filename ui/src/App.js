import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { MyComponent, Test } from './myComponent';
import { DatePicker } from 'antd';
import 'antd/dist/antd.css';
import Button from './component/Button';
import SearchBox from '../src/SearchBox';
import CommentBox from './comment/CommentBox';
import { BrowserRouter, Route } from 'react-router-dom';
import SearchResult from './component/search/SearchResult';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      headerCls: 'App-header',
      contentCls: 'App-content',
      resultCls: 'App-result'
    };
  }
  render() {
    return (
      <div className="App">
        <div className={this.state.headerCls}>
          <h1>ID_SNIFFER</h1>
        </div>
        <div className={this.state.contentCls}>
          <SearchBox parent={this} />
        </div>
        <div className={this.state.resultCls}>
          <Route path="/searchResult" component={SearchResult} />
        </div>
      </div>
    );
  }
}
export default App;
