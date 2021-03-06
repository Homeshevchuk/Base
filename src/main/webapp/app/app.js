import React from 'react';
import ReactDOM from 'react-dom';
import Routes from './src/components/Routes';
import injectTapEventPlugin from 'react-tap-event-plugin';

injectTapEventPlugin();

ReactDOM.render(<Routes/>, document.getElementById('react-root'));