import React from 'react';
import AppBar from 'material-ui/lib/app-bar';
import TextField from 'material-ui/lib/text-field';
import Paper from 'material-ui/lib/paper';
import RaisedButton from 'material-ui/lib/raised-button';

export default class Login extends React.Component {
	constructor(props, context){
		super(props, context);
		this.styles = {
			paper: {
				width: 400,
				height: 200,
				margin: '15px auto',
			}
		}
	}
	onLogin(){
		let login = this.refs.login.getValue();
		let pass = this.refs.pass.getValue();
		console.log(`${login}:${pass}`);
		console.log(btoa(`${login}:${pass}`));
		// fetch(`http://localhost:1488/getUser`, {
		// 	headers: {
		// 		Authorization: 'Basic ' + btoa(login+':'+pass),
		// 		credentials: "same-origin"
		// 	}

		// }).then(()=>{
		// 	console.log(1);
		// 	// location.replace('/#/');
		// });


		var xmlhttp = new XMLHttpRequest(); 
		xmlhttp.open("GET", "http://localhost:1488/getUser");
		xmlhttp.setRequestHeader("Authorization", 'Basic ' + btoa(login + ':' + pass)); 
		xmlhttp.onreadystatechange = function () { 
			console.log(xmlhttp.responseText);
			location.replace('/app/public/index.html/#/')
		}; 
		xmlhttp.send();

	}

	render(){
		return (   
			<div>
				<div style={{width:0, height:0}} id="map"></div>
				<AppBar title="Login"/>
				<Paper zDepth={5} style={this.styles.paper}>
				<center>
					 <TextField
					 		ref="login"
					      hintText="Login"
					      floatingLabelText="Login"
					    /><br/>
					    <TextField
					    ref="pass"
					      hintText="Password"
					      floatingLabelText="Password"
					      type="password"
					    /><br/>
					    <RaisedButton label="Login" secondary={true} onClick={()=>{this.onLogin()}}/>
				</center>
				</Paper>
			</div>

		);
	}
}