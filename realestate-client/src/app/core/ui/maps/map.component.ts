import {
  AfterViewInit,
  Component,
  ElementRef,
  Inject,
  Input,
  HostListener,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';

import { getCurrentOffset } from './libs/map-libs';
import { EventHandler } from './interfaces/event-handler';
import { INIT_COORDS  } from './token';

import * as esri from 'esri-leaflet';
import * as L from 'leaflet';
@Component({
  selector: 'app-map',

  templateUrl: './map.component.html',

  styleUrls: ['./map.component.scss'],
})
export class MapComponent implements OnInit, AfterViewInit, OnDestroy {

  public mcText: string;                         // mouse coords text (innerHTML)

  @Input()
  public markers: {lat: number, long: number}[]; // Markers to overlay on Map

  public currentWidth: number;                   // current map width based on window width
  public currentHeight: number;                  // current map height based on window height

  protected baseLayer: any;                      // Map Base layer
  protected map: any;                            // Map reference (currently leaflet)
  protected mapLoaded = false;                   // True if the map has been loaded

  protected polyGon : L.Polygon;
  protected circle: any;
  protected popup: any;
  protected marker0: L.Marker;
  // The primary Map
  @ViewChild('primaryMap', {static: true}) protected mapDivRef: ElementRef;
  protected mapDiv: HTMLDivElement;

  // Leaflet Map Event Handlers (used for removal on destroy)
  protected onClickHandler: EventHandler;
  protected onMouseMoveHandler: EventHandler;

  constructor( @Inject(INIT_COORDS) protected _initCoords: {lat: number, long: number} )
  {
    this.baseLayer = null;

    // Leaflet Map Event Handlers
    this.onClickHandler     = (evt: any) => this.__onMapClick(evt);
    this.onMouseMoveHandler = (evt: any) => this.__onMapMouseMove(evt);

    // Initial mouse-coords text
    this.mcText = '';

    // some simple default values
    this.currentWidth  = 600;
    this.currentHeight = 400;
  }

  public ngOnInit(): void
  {
    // Reference to DIV containing map is used in Leaflet initialization
    this.mapDiv = this.mapDivRef.nativeElement;

    this.__initializeMap();
    this.__renderMap();
    this.__showMarkers();
    console.log(this.currentWidth,this.currentHeight);
  }

  public ngAfterViewInit(): void
  {
    this.map.invalidateSize();

    this.__initMapHandlers();
  }

  public ngOnDestroy(): void
  {
    this.map.off('click'    , this.onClickHandler );
    this.map.off('mousemove', this.onMouseMoveHandler);
  }

  /**
   * Basic map initialization
   */
  protected __initializeMap(): void
  {
    if (this.mapLoaded) {
      return;
    }

    this.mapLoaded = true;

    this.__updateMapSize();
  }

  /**
   * Render the map (establish center and base layer)
   */
  protected __renderMap(): void
  {
    // Create Leaflet Map in fixed DIV - zoom level is hardcoded for simplicity
    this.map = L.map(this.mapDiv, {
      zoomControl: false,
      zoomAnimation: false,
      trackResize: true,
      boxZoom: true,
    }).setView([this._initCoords.lat, this._initCoords.long], 14);
    this.baseLayer = esri.basemapLayer('Gray');
    this.map.addLayer(this.baseLayer);
    this.renderPolygon();
    this.renderCircle();
    this.renderPopup();
  }
  renderPopup() {
    this.popup = L.popup().setLatLng([this.markers[0].lat,this.markers[0].long]).setContent('<p>Hello world!<br />This is a nice popup.</p> <img src="http://t2.gstatic.com/images?q=tbn:ANd9GcSAu921v6txWhsNSD-ySv8V9Ma0SMpK3hKSiXY5L_LeQS1-rnLc27O3AkCc_jjcESmCTOmcSm0tAnM4i4Zwz-I">')
    .openOn(this.map);
  }
  renderCircle() {
   this.circle = new L.Circle([this.markers[0].lat,this.markers[0].long],{radius:4000});
   this.circle.addTo(this.map);
  }
  renderPolygon() {
   this.polyGon = new L.Polygon(this.markers.map(marker => [marker.lat,marker.long]),{color:'blue',weight:5});
   this.polyGon.addTo(this.map);
  }

  /**
   * Show markers if they are defined
   */
  protected __showMarkers(): void
  {
    if (this.markers !== undefined && this.markers != null && this.markers.length > 0)
    {
      // Add markers
      const icon = L.icon({
        iconUrl: '../../../assets/map/marker-icon.png',
        iconSize: [25, 42],
        iconAnchor: [0, 42],
        shadowUrl: '../../../assets/map/marker-shadow.png',
        shadowSize: [41, 41],
        shadowAnchor: [0, 41],
      });

      const n: number = this.markers.length;
      let i: number;
      let m: L.Marker;

      let x: number;
      let y: number;

      for (i = 0; i < n; ++i) {

        x = this.markers[i].lat;
        y = this.markers[i].long;

        if (x !== undefined && !isNaN(x) && y !== undefined && !isNaN(y))
        {
          // okay to add the icon
          m = L.marker([x, y], {icon: icon}).addTo(this.map);
        }
        else
        {
          // implement your own error handling
          console.log('MARKER ERROR, Marker number: ', (i+1), 'x: ', x, ' y: ', y);
        }
      }
    }
  }

  @HostListener('window:resize', ['$event'])
  protected __onResize(event: any): void
  {
    this.__updateMapSize();

    this.map.invalidateSize();
  }

  /**
   * Update the current width/height occupied by the map
   */
  protected __updateMapSize(): void
  {
    // update width/height settings as you see fit
    this.currentWidth  = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
    this.currentHeight = (window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) - 200;
  }

  /**
   * Initialize Leaflet Map handlers
   */
  protected __initMapHandlers(): void
  {
    this.map.on('mousemove', this.onMouseMoveHandler);
    this.map.on('click'    , this.onClickHandler );
  }

  /**
   * Execute on Leaflet Map click
   */
  protected __onMapClick(evt: any): void {

    const target: any = evt.originalEvent.target;

    console.log('Map click on: ', target);

  }

  /**
   * Execute on mouse move over Leaflet map
   *
   * @param evt Leaflet-supplied information regarding current mouse point, mainly geo coords.
   */
  protected __onMapMouseMove(evt: any): void
  {
    const offset: {x: number, y: number} = getCurrentOffset(this.map);

    // uncomment to study offset
    // console.log('offset computation:', offset);

    // Lat and Long are embedded in the event object
    const lat: string  = evt.latlng.lat.toFixed(3);
    const long: string = evt.latlng.lng.toFixed(3);
    this.mcText        = `Latitude: ${lat} &nbsp; &nbsp; Longitude: ${long}`;
  }

  protected __showPolyLine():void {
    
  }
}