import React from 'react';
import { Router, Route, Link, hashHistory, browserHistory } from 'react-router';

import GlobalMap from './GlobalMap';
import User from './User';
import Walk from './Walk';
import Login from './Login';
import CreateDirection from './CreateDirection';

import GMap from './GMap';

export default class Routes extends React.Component {
	constructor(props, context){
		super(props, context);
	}

	componentWillMount(){
		window.initMap=GMap.init;
	}

	render(){
		return (
		  <Router history={hashHistory}>
		    <Route path="/" component={GlobalMap}/>
		    <Route path="/login" component={Login}/>
		    <Route path="/create" component={CreateDirection}/>
		    <Route path="/walk/:walkId" component={Walk}/>
		  </Router>

		);
	}
}