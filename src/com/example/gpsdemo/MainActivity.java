package com.example.gpsdemo;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.获取LocationManager
        String serviceString = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) getSystemService(serviceString);
        //2.获取当前位置信息
        String provider = LocationManager.GPS_PROVIDER;
        Location location = locationManager.getLastKnownLocation(provider);
        
        getLocationInfo(location);
        //3.位置监视方法 
        //第一个参数是定位方式：网络定位或者GPS定位
        //第二个参数是产生位置改变事件的时间间隔，单位毫秒
        //第三个参数是距离条件
        //第四个参数是回调函数
        locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
    }
    //提取位置信息
    private void getLocationInfo(Location location){
    	String latLongInfo;
    	TextView locationText = (TextView) findViewById(R.id.label);
    	
    	if(location != null){
    		double lat = location.getLatitude();
    		double lng = location.getLongitude();
    		
    		latLongInfo = "Lat:"+lat+"\nLng:"+lng;
    	}else{
    		latLongInfo = "No location found";
    	}
    	locationText.setText("当前的位置：\n"+latLongInfo);
    }
    
    private final LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			getLocationInfo(null);
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			getLocationInfo(null);
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			getLocationInfo(location);
		}
	};
}
