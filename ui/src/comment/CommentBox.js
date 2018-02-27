import React, { Component } from 'react';
import CommentForm from './CommentForm';
import CommentList from './CommentList';
class CommentBox extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [
        { author: 'Pete Hunt', text: 'This is one comment' },
        { author: 'Jordan Walke', text: 'This is *another* comment' }
      ]
    };
  }
  render() {
    return (
      <div className="commentBox">
        <h1>Comments</h1>
        <CommentList data={this.state.data} />
        <CommentForm />
      </div>
    );
  }
  componentDidMount() {
    setTimeout(() => this.change(), 1000);
  }
  change() {
    this.setState({
      data: [
        { author: 'Pete Hunt2', text: 'This is one comment23' },
        { author: 'Jordan Walke3', text: 'This is *another* comm32ent' }
      ]
    });
  }
}
export default CommentBox;
