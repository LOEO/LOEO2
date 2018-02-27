import React, { Component } from 'react';
import './SearchItem.css';

class SearchItem extends Component {
  render() {
    return (
      <div className="search-item-container">
        <h3 className="search-item-title">React</h3>
        <div className="search-item-content">
          React 中文的论坛, 浙ICP备14043687号-3... 齐天大圣,react开源项目,踩我。。 ( 2 3 4 5 6 7 8 9
          ) [作品] (163) React入门与进阶项目--帮助新手快...
        </div>
        <div className="search-item-footer">react-china.org/ - 百度快照</div>
      </div>
    );
  }
}

export default SearchItem;
