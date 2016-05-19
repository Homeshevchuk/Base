import React from 'react';
import AppBar from 'material-ui/lib/app-bar';

export default class App extends React.Component {
	constructor(props, context){
		super(props, context);

	}


	render(){
		return (
			<div>
				<AppBar title="Walk" showMenuIconButton={false}/>
				<p>123</p>
			</div>
		);
	}
}