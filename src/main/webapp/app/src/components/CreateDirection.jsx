import React from 'react';
import {Link} from 'react-router';
import AppBar from 'material-ui/lib/app-bar';
import TextField from 'material-ui/lib/text-field';
import RaisedButton from 'material-ui/lib/raised-button';
import TimePicker from 'material-ui/lib/time-picker';
import DatePicker from 'material-ui/lib/date-picker/date-picker';
import Slider from 'material-ui/lib/slider';

import GMap from './GMap';


export default class GlobalMap extends React.Component {
	constructor(props, context){
	super(props, context);
	this.state = {
		usersCount: 2,
	}
		this.styles = {
			map: {
				width: '100%',
				height: 700,
			}
		}

		this.walk = {};
		this.walk.path = [];
	}

	componentDidMount(){
		// window.afterInitMap=GMap.showAllWalks;
		if(window.MAP){
			GMap.init();
		}

	}

	onAddPoint(){
		this.walk.path.push({
			x: MAP.getCenter().lat(),
			y: MAP.getCenter().lng(),
			pointId: this.walk.path.length,
		});
		GMap.showWalk(this.walk);
	}

	onUsersCount(){
		this.setState({
			usersCount: this.refs.usersCount.getValue(),
		})
	}

	onAddDirection(){
		this.walk.title = this.refs.title.getValue();
		// this.walk.id = 0;
		this.walk.completed = false;
		this.walk.description = this.refs.desc.getValue();
		this.walk.maxUsers = this.refs.usersCount.getValue();
		let date = this.refs.date.getDate();
		let time = this.refs.time.getTime();
		let dirDate = new Date(date.getFullYear(), date.getMonth(), date.getDate(), time.getHours(), time.getMinutes());
		this.walk.startDate = +dirDate;
		console.log(this.walk);
		// fetch(`http://10.55.33.100:1488/addWalk?userId=1`,{
		// 	headers: {
		// 		'Content-Type':'application/json'
		// 	},
		// 	method: 'post',
		// 	body: JSON.stringify(this.walk),
		// })
		let _this = this;
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST", "http://localhost:1488/Authorized/addWalk?userId=88");
		xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		xmlhttp.onreadystatechange = function () {
        	if(xmlhttp.readyState === XMLHttpRequest.DONE && xmlhttp.status === 200) location.replace('/app/public/index.html/#/');
    	};
		xmlhttp.send(JSON.stringify(_this.walk));
	}
	render(){
		return (
			<div>
				<AppBar title="Create Direction"/>
				<div id="map" style={this.styles.map}></div>
				<div className="centerMarker"></div>
				<div className="rightPanel">
					<TextField
					  ref="title"
				      hintText="Title"
				      floatingLabelText="Title"
				    />
				    <TextField
					  ref="desc"
				      hintText="Description"
				      floatingLabelText="Description"
				      multiLine={true}
				    />
				    <DatePicker ref="date" hintText="Date" />
				    <TimePicker
				   	  ref="time"
				      format="24hr"
				      hintText="Time"
				    />
				    <p>Max users: {this.state.usersCount}</p>
				    <Slider ref="usersCount" step={1} value={this.state.usersCount} min={2} max={10} onChange={()=>{this.onUsersCount()}}/>
				    <RaisedButton label="Add point" secondary={true} onClick={()=>{this.onAddPoint()}}/>
				    <br/>
				    <br/>
				    <RaisedButton label="Add direction" primary={true} onClick={()=>{this.onAddDirection()}}/>
				</div>
			</div>
		);
	}
}