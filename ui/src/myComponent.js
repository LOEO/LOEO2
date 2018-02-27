import React from "react";

class MyComponent extends React.Component {
  render() {
    return <div>MyComponent</div>;
  }
}
function test(props) {
  return (
    <div>
      Hello {props.name}1111
    </div>
  );
}
export { MyComponent, test as Test };
