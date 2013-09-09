package com.leizhou.wr.DAO;


/**
 * @author Lei Zhou 
 * This object hold a point with value of Lat and Lng
 *	these values in integer format, however the format of double can
 *	be used as initialization and format of an output as well.
 */
public class Points {
	
		private int lat=0;
		private int lng=0;
		
		public Points(){
			this.lat=0;
			this.lng=0;
		}
		/**
		 * @return true is been set, or false when lat=lng=0
		 */
		public boolean isNotEmpty(){
			boolean rs=true;
			if(lat==0&&lng==0){
				rs=false;
			}			
			return rs;
		}
		public Points(int latE6,int lngE6){
			this.lat=latE6;
			this.lng=lngE6;
		}
		public Points(double lat,double lng){
			this.lat=(int) (lat*1E6);
			this.lng=(int)(lng*1E6);
		}
		public Points(Points point){
			this.lat=point.getLatE6();
			this.lng=point.getLngE6();
		}
		
		public void setLat(int lat) {
			this.lat = lat;
		}
		public void setLng(int lng) {
			this.lng = lng;
		}
		/**
		 * @return Lat in 1E6 format, if want a float, should dived by 1E6
		 */
		public int getLatE6() {
			return lat;
		}
		/**
		 * @return Lng in 1E6 format, if want a float, should dived by 1E6
		 */
		public int getLngE6() {
			return lng;
		}
		
		/**
		 * @return Lat in double format
		 */
		public double getLatDouble() {
			return (double)lat/1E6;
		}
		
		/**
		 * @return Lng in double format
		 */
		public double getLngDouble() {
			return (double)lng/1E6;
		}

}
