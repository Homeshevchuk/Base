import React from 'react';
import AppBar from 'material-ui/lib/app-bar';

import GMap from './GMap';
import RaisedButton from 'material-ui/lib/raised-button';

import IconButton from 'material-ui/lib/icon-button';
import NavigationClose from 'material-ui/lib/svg-icons/navigation/close';
import IconMenu from 'material-ui/lib/menus/icon-menu';
import MoreVertIcon from 'material-ui/lib/svg-icons/navigation/more-vert';
import MenuItem from 'material-ui/lib/menus/menu-item';

export default class Walk extends React.Component {
	constructor(props, context){
	super(props, context);
	this.state = {
		data: {}
	};
		this.styles = {
			map: {
				width: '100%',
				height: 700,
			}
		}
	}

	componentDidMount(){
		let walkId = this.props.params.walkId;
		window.afterInitMap= ()=>{};
		if(window.MAP){
			GMap.init();
		}

		var xmlhttp = new XMLHttpRequest(); 
		xmlhttp.open("GET", "http://localhost:1488/Authorized/getWalk?id="+walkId);
		xmlhttp.onreadystatechange = function () {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			console.log(xmlhttp.responseText)
			func(JSON.parse(xmlhttp.responseText))
		}
		}; 
		xmlhttp.send();
		let _this = this;
		function func(json){
			GMap.showWalk(json.walk);
			_this.setState({
				data: json,
			})
		}
	}

	onSubscribe(){
		let walkId = this.props.params.walkId;
		var xmlhttp = new XMLHttpRequest(); 
		xmlhttp.open("GET", "http://localhost:1488/Authorized/addSubscribeRequest?walkId="+walkId);
		xmlhttp.onreadystatechange = function () {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			console.log(xmlhttp.responseText)
			// func(JSON.parse(xmlhttp.responseText))
		}
		}; 
		xmlhttp.send();
	}

	render(){
		if(!this.state.data.walk) return (
			<div>
				<AppBar title=""/>
				<div id="map" style={this.styles.map}></div>
			</div>
		);
		let walk = this.state.data.walk;
		let owner = this.state.data.owner;
		let startDate = new Date(walk.startDate);
		let dateStr = `${startDate.getDate()}/${startDate.getMonth()+1}/${startDate.getFullYear()} ${startDate.getHours()}-${startDate.getMinutes()}`;
		return (
			<div>
				<AppBar title={walk.title}

				iconElementRight={
			      <IconMenu
			        iconButtonElement={
			          <IconButton><MoreVertIcon /></IconButton>
			        }
			        targetOrigin={{horizontal: 'right', vertical: 'top'}}
			        anchorOrigin={{horizontal: 'right', vertical: 'top'}}
			      >

			        <MenuItem primaryText="Add" onClick={()=>{location.replace('/app/public/index.html/#/create')}}/>
			        <MenuItem primaryText="Back" onClick={()=>{location.replace('/app/public/index.html/#/')}}/>
			      </IconMenu>
			    }

				/>
				<div id="map" style={this.styles.map}></div>
				<div className="rightPanel">
					<center>
						<b>{walk.title}</b>
						<br/>
						<img src={`http://localhost:1488${owner.imageUrl}`} />
						<br/>
						<span>{owner.name} {owner.surname}</span>
						<br/>
						<span>{owner.phoneNumber}</span>
						<br/>
						<span>{owner.email}</span>
						<br/>
						<span>Max users: {walk.maxUsers}</span>
						<br/>
						<span>Start: {dateStr}</span>
						<br/>
						<RaisedButton label="Subscribe" secondary={true} onClick={()=>{this.onSubscribe()}} />
						{this.state.data.subscribers.map((el)=>(
							<p>{el.name} - {el.surname}</p>
						))}
					</center>
				</div>
			</div>
		);
	}
}