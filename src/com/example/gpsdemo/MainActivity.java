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
        //1.��ȡLocationManager
        String serviceString = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) getSystemService(serviceString);
        //2.��ȡ��ǰλ����Ϣ
        String provider = LocationManager.GPS_PROVIDER;
        Location location = locationManager.getLastKnownLocation(provider);
        
        getLocationInfo(location);
        //3.λ�ü��ӷ��� 
        //��һ�������Ƕ�λ��ʽ�����綨λ����GPS��λ
        //�ڶ��������ǲ���λ�øı��¼���ʱ��������λ����
        //�����������Ǿ�������
        //���ĸ������ǻص�����
        locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
    }
    //��ȡλ����Ϣ
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
    	locationText.setText("��ǰ��λ�ã�\n"+latLongInfo);
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
