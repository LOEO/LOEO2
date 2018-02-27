import React, { Component } from "react";
import FormatDate from "./common/FormatDate"

class Clock extends Component {
  constructor(props) {
    super(props);
    this.state = { date: new Date() };
  }
  componentDidMount() {
    this.timerId = setInterval(() => this.tick(), 1000);
  }
  componentWillUnmount() {
    clearInterval(this.timerId);
  }
  tick() {
    this.setState({ date: new Date() });
  }
  render() {
    return (
      <div>
        <FormatDate date={this.state.date.toLocaleTimeString()}/>
      </div>
    );
  }
}
export default Clock;
