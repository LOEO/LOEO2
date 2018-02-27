import React, { Component } from 'react';
import './SearchBox.css';
import { Switch, Link, Route } from 'react-router-dom';
import Clock from './Clock';
import SearchResult from './component/search/SearchResult';
class SearchBox extends Component {
  constructor(props) {
    super(props);
    this.state = {
      inputFocusClassName: ''
    };
    this.inputFocus = this.inputFocus.bind(this);
    this.inputBlur = this.inputBlur.bind(this);
    this.change = this.change.bind(this);
  }

  render() {
    return (
      <div className="searchBox">
        <span
          className={this.state.inputFocusClassName + ' searchInputWrapper'}
        >
          <input
            placeholder="搜索"
            type="text"
            size="large"
            className="searchInput"
            onFocus={this.inputFocus}
            onBlur={this.inputBlur}
            onChange={this.change}
          />
        </span>
        <span className="searchButtonWrapper">
          <Link className="searchButton" to="/searchResult">
            搜索
          </Link>
        </span>
      </div>
    );
  }

  inputFocus() {
    this.setState({
      inputFocusClassName: 'iptfocus'
    });
  }

  inputBlur() {
    this.setState({
      inputFocusClassName: ''
    });
  }
  change() {
    console.log(this.props.parentState);
    console.log(111);
    this.props.parent.setState({
      headerCls: 'App-header-search',
      contentCls: 'App-content-search',
      resultCls: 'App-result-search'
    });
  }
}

export default SearchBox;
