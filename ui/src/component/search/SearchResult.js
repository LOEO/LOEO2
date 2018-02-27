import React, { Component } from 'react';
import SearchItem from './SearchItem';
class SearchResult extends Component {
  render() {
    let listItems = [1, 2, 3, 4].map(number => <SearchItem key={number} />);
    console.log(listItems);
    return (
      <div className="search-result-container">
        {listItems}
      </div>
    );
  }
}
export default SearchResult;
