import { Component, OnInit, ViewChild, ElementRef } from "@angular/core";
import { Map } from "src/app/model/map";
declare var H: any;
@Component({
  selector: "app-here-map",
  templateUrl: "./here-map.component.html",
  styleUrls: ["./here-map.component.scss"]
})
export class HereMapComponent implements OnInit {
  private platform: any;

  @ViewChild("map")
  public mapElement: ElementRef;

  public constructor() {
    this.platform = new H.service.Platform({
      app_id: "dmdRFi5x5pT0zuy09gle",
      app_code: "KLtdq3MAUJruxhiJ2GyAFQ"
    });
  }

  public ngOnInit() {}

  public ngAfterViewInit() {
    let defaultLayers = this.platform.createDefaultLayers();

    let map = new H.Map(
      this.mapElement.nativeElement,
      defaultLayers.normal.map,
      {
        zoom: this.place.zoom,
        center: { lat: this.place.lat, lng: this.place.lng }
      }
    );
    var svgMarkup =
      "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/PjxzdmcgaGVpZ2h0PSIyNCIgdmVyc2lvbj0iMS4xIiB3aWR0aD0iMjQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6Y2M9Imh0dHA6Ly9jcmVhdGl2ZWNvbW1vbnMub3JnL25zIyIgeG1sbnM6ZGM9Imh0dHA6Ly9wdXJsLm9yZy9kYy9lbGVtZW50cy8xLjEvIiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPjxnIHRyYW5zZm9ybT0idHJhbnNsYXRlKDAgLTEwMjguNCkiPjxwYXRoIGQ9Im0xMiAwYy00LjQxODMgMi4zNjg1ZS0xNSAtOCAzLjU4MTctOCA4IDAgMS40MjEgMC4zODE2IDIuNzUgMS4wMzEyIDMuOTA2IDAuMTA3OSAwLjE5MiAwLjIyMSAwLjM4MSAwLjM0MzggMC41NjNsNi42MjUgMTEuNTMxIDYuNjI1LTExLjUzMWMwLjEwMi0wLjE1MSAwLjE5LTAuMzExIDAuMjgxLTAuNDY5bDAuMDYzLTAuMDk0YzAuNjQ5LTEuMTU2IDEuMDMxLTIuNDg1IDEuMDMxLTMuOTA2IDAtNC40MTgzLTMuNTgyLTgtOC04em0wIDRjMi4yMDkgMCA0IDEuNzkwOSA0IDQgMCAyLjIwOS0xLjc5MSA0LTQgNC0yLjIwOTEgMC00LTEuNzkxLTQtNCAwLTIuMjA5MSAxLjc5MDktNCA0LTR6IiBmaWxsPSIjZTc0YzNjIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSgwIDEwMjguNCkiLz48cGF0aCBkPSJtMTIgM2MtMi43NjE0IDAtNSAyLjIzODYtNSA1IDAgMi43NjEgMi4yMzg2IDUgNSA1IDIuNzYxIDAgNS0yLjIzOSA1LTUgMC0yLjc2MTQtMi4yMzktNS01LTV6bTAgMmMxLjY1NyAwIDMgMS4zNDMxIDMgM3MtMS4zNDMgMy0zIDMtMy0xLjM0MzEtMy0zIDEuMzQzLTMgMy0zeiIgZmlsbD0iI2MwMzkyYiIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoMCAxMDI4LjQpIi8+PC9nPjwvc3ZnPg==";

    // Create an icon, an object holding the latitude and longitude, and a marker:
    var icon = new H.map.Icon(svgMarkup),
      coords = this.place,
      marker = new H.map.Marker(coords, { icon: icon });

    // Add the marker to the map and center the map at the location of the marker:
    map.addObject(marker);
    map.setCenter(coords);
    var circle = new H.map.Circle(
      this.place,
      150
    );

    // Add the circle to the map:
    map.addObject(circle);
    // var ui :any;
    // ui = H.ui.UI.createDefault(map, defaultLayers);
    var routingParameters = {
      // The routing mode:
      'mode': 'fastest;car',
      // The start point of the route:
      'waypoint0': 'geo!10.8822532,106.779346',
      // The end point of the route:
      'waypoint1': 'geo!10.8701157,106.8013646',
      // To retrieve the shape of the route we choose the route
      // representation mode 'display'
      'representation': 'display'
    };
    var onResult = function(result) {
      var route,
        routeShape,
        startPoint,
        endPoint,
        linestring;
      if(result.response.route) {
      // Pick the first route from the response:
      route = result.response.route[0];
      // Pick the route's shape:
      routeShape = route.shape;
    
      // Create a linestring to use as a point source for the route line
      linestring = new H.geo.LineString();
    
      // Push all the points in the shape into the linestring:
      routeShape.forEach(function(point) {
        var parts = point.split(',');
        linestring.pushLatLngAlt(parts[0], parts[1]);
      });
    
      // Retrieve the mapped positions of the requested waypoints:
      startPoint = route.waypoint[0].mappedPosition;
      endPoint = route.waypoint[1].mappedPosition;
    
      // Create a polyline to display the route:
      var routeLine = new H.map.Polyline(linestring, {
        style: { strokeColor: 'blue', lineWidth: 10 }
      });
    
      // Create a marker for the start point:
      var startMarker = new H.map.Marker({
        lat: startPoint.latitude,
        lng: startPoint.longitude
      });
    
      // Create a marker for the end point:
      var endMarker = new H.map.Marker({
        lat: endPoint.latitude,
        lng: endPoint.longitude
      });
    
      // Add the route polyline and the two markers to the map:
      map.addObjects([routeLine, startMarker, endMarker]);
    
      // Set the map's viewport to make the whole route visible:
      map.setViewBounds(routeLine.getBounds());
      }
    };
    
    // Get an instance of the routing service:
    var router = this.platform.getRoutingService();
    
    // Call calculateRoute() with the routing parameters,
    // the callback and an error callback function (called if a
    // communication error occurs):
    router.calculateRoute(routingParameters, onResult,
      function(error) {
        alert(error.message);
      });
    map.ui.ScaleBar.setVisibility(true);

    this.enableTrafficInfo(map,this.place,defaultLayers);
  }
  enableTrafficInfo (map,place,defaultLayers) {
    // Center map on New York
    map.setCenter(place);
    map.setZoom(11);
  
    // Show traffic tiles
    map.setBaseLayer(defaultLayers.normal.traffic);
  
    // Enable traffic incidents layer
    map.addLayer(defaultLayers.incidents);
  }
  public place: Map = {
    lat: 10.8698354,
    lng: 106.8027391,
    zoom: 17
  };
}
