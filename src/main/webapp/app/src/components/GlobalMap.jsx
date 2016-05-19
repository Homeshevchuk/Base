import React from 'react';
import {Link} from 'react-router';
import AppBar from 'material-ui/lib/app-bar';

import GMap from './GMap';

import IconButton from 'material-ui/lib/icon-button';
import NavigationClose from 'material-ui/lib/svg-icons/navigation/close';
import IconMenu from 'material-ui/lib/menus/icon-menu';
import MoreVertIcon from 'material-ui/lib/svg-icons/navigation/more-vert';
import MenuItem from 'material-ui/lib/menus/menu-item';


export default class GlobalMap extends React.Component {
	constructor(props, context){
	super(props, context);
		this.styles = {
			map: {
				width: '100%',
				height: 700,
			}
		}
	}

	componentDidMount(){
		window.afterInitMap=GMap.showAllWalks;
		if(window.MAP){
			GMap.init();
		}
		
	}

	render(){
		return (
			<div>
				<AppBar title="Walk"
				 iconElementRight={
			      <IconMenu
			        iconButtonElement={
			          <IconButton><MoreVertIcon /></IconButton>
			        }
			        targetOrigin={{horizontal: 'right', vertical: 'top'}}
			        anchorOrigin={{horizontal: 'right', vertical: 'top'}}
			      >
			        <MenuItem primaryText="Add" onClick={()=>{location.replace('/app/public/index.html/#/create')}}/>
			      </IconMenu>
			    }
				/>
				<div id="map" style={this.styles.map}></div>
			</div>
		);
	}
}