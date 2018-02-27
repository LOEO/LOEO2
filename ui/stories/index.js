import React from "react";

import { storiesOf } from "@storybook/react";
import { action } from "@storybook/addon-actions";
import { linkTo } from "@storybook/addon-links";

import { Button, Welcome } from "@storybook/react/demo";
import Clock from "../src/Clock";

storiesOf("Welcome", module).add("to Storybook", () =>
  <Welcome showApp={linkTo("Button")} />
);

storiesOf("Button", module)
  .add("with text", () =>
    <Button onClick={action("clicked")}>Hell3o1 Button</Button>
  )
  .add("with some emoji", () =>
    <Button onClick={action("clicked")}>😀 😎 👍 💯</Button>
  );

storiesOf("Clock", module).add("clock", () => <Clock />);
