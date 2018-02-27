import React from "react";
class Button extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isOn: true
    };
    this.handleClick = this.handleClick.bind(this);
  }
  handleClick(e) {
    this.setState((pre, cur) => (
      {
        isOn: !this.state.isOn
      }
    ));
  }
  render() {
    return (
      <button onClick={this.handleClick}>
        {this.state.isOn ? "开" : "关"}
      </button>
    );
  }
}
export default Button;
