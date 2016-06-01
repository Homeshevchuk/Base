import {browserHistory} from 'react-router';

export default class GMap {

	static init() {        
		window.MAP = new google.maps.Map(document.getElementById('map'), {
		    center: {lat: 50.450428, lng: 30.523590},
		    zoom: 13,
		    disableDefaultUI: true,

		    zoomControl: true,

		});

		if(window.afterInitMap) window.afterInitMap();
		
	}
	static showAllWalks(){

		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET", "http://localhost:1488/Authorized/getAllWalks");
		xmlhttp.onreadystatechange = function () {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				console.log(xmlhttp.responseText)
				func(JSON.parse(xmlhttp.responseText))
			}
		};
		xmlhttp.send();


		// fetch(`http://localhost:1488/Authorized/getAllWalks`)
		// .then(data => data.json())
		function func(json) {

		let allMarkers = [];

		json.forEach((startPoint)=>{
			let marker = new google.maps.Marker({
			    position: {
			    	lat: startPoint.start.x,
			    	lng: startPoint.start.y,
			    },
			    animation: google.maps.Animation.DROP,
			});
			let startDate = new Date(startPoint.startDate);
			let dateStr = `${startDate.getDate()}/${startDate.getMonth()+1}/${startDate.getFullYear()} ${startDate.getHours()}-${startDate.getMinutes()}`;
			let infowindow = new google.maps.InfoWindow({
		    	content: `<div class='infoWindow'><center><p>${startPoint.title}</p><img src=http://localhost:1488${startPoint.url} /><br/><span>${startPoint.name} ${startPoint.surname}</span><br/><span>People left: ${startPoint.peopleLeft}</span><br/><span>Start: ${dateStr}</span></center></div>`
		  	});

		  	marker.addListener('mouseover', ()=>{
		  		infowindow.open(MAP, marker);
		  	});

		  	marker.addListener('click', ()=>{
		  		allMarkers.forEach((m)=>{m.setMap(null)});
		  		allMarkers = [];
		  		location.replace(`/app/public/index.html/#/walk/${startPoint.id}/`);
		  		GMap.showWalk(startPoint.id);
		  	});
		  	
		  	setTimeout(()=>{
		  		marker.setMap(MAP);
		  	}, (Math.floor(Math.random() * 1500) + 300));

		  	marker.addListener('mouseout', ()=>{
		  		infowindow.close();
		  	});

		  	allMarkers.push(marker);
		});

		 MAP.setCenter({lat: 50.450428, lng: 30.523590});
		 MAP.setZoom(13);

		}
		
	}

	static showWalk(walk) {
		if(walk.path.length<2) return;
		if(GMap.nowDir) GMap.nowDir.setMap(null);
		var directionsService = new google.maps.DirectionsService();
		var directionsDisplay = new google.maps.DirectionsRenderer();
		GMap.nowDir = directionsDisplay;
		directionsDisplay.preserveViewport = true;
		directionsDisplay.setMap(MAP);
		let waypoints = [];
		for(let i = 1; i<walk.path.length; i++){
			waypoints.push({
				location: {
					lat: walk.path[i].x,
					lng: walk.path[i].y
				},
				stopover: false,
			})
		}

		var request = {
			origin: {
				lat: walk.path[0].x,
				lng: walk.path[0].y,
			},
			destination: {
				lat: walk.path[walk.path.length-1].x,
				lng: walk.path[walk.path.length-1].y,
			},
			travelMode: google.maps.TravelMode.WALKING,
			unitSystem: google.maps.UnitSystem.METRIC,
			waypoints: waypoints,
		}

		directionsService.route(request, function(result, status) {
		    if (status == google.maps.DirectionsStatus.OK) {
		      directionsDisplay.setDirections(result);
		    }
		})
	}
}