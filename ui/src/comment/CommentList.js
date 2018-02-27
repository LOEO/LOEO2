import React, { Component } from 'react';
import Comment from './Comment';
class CommentList extends Component {
  render() {
    console.log(this.props.data);
    debugger;
    var commentNodes = this.props.data.map(comment => {
      return <Comment author={comment.author} text={comment.text} />;
    });
    return (
      <div className="commentList">
        {commentNodes}
      </div>
    );
  }
}

export default CommentList;
