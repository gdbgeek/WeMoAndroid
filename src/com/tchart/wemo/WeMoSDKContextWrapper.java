package com.tchart.wemo;

import com.belkin.wemo.localsdk.WeMoDevice;
import com.belkin.wemo.localsdk.WeMoSDKContext;
import com.belkin.wemo.localsdk.WeMoSDKContext.NotificationListener;

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.DependsOn;
import anywheresoftware.b4a.BA.Permissions;
import anywheresoftware.b4a.objects.collections.List;

@BA.Author("Trevor Hart")
@BA.ShortName("WeMoSDKContext")
@BA.Version(1.0F)

@DependsOn(values = {"wemosdk_1.0"})
@Permissions(values = {"android.permission.READ_PHONE_STATE","android.permission.ACCESS_WIFI_STATE","android.permission.INTERNET","android.permission.ACCESS_NETWORK_STATE","android.permission.WRITE_EXTERNAL_STORAGE","android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_FINE_LOCATION","android.permission.CHANGE_WIFI_STATE"})

public class WeMoSDKContextWrapper extends AbsObjectWrapper<WeMoSDKContext> 
{
	public static final String WEMO_DEVICE_OFF = "0";
	public static final String WEMO_DEVICE_ON = "1";
	public static final String WEMO_DEVICE_UNDEFINED = "3";
	public static final String WEMO_DEVICE_STAND_BY = "8";
	
	public static final String WEMO_TYPE_DEFAULT = "urn:Belkin:device:socket:1";
	public static final String WEMO_TYPE_SWITCH = "urn:Belkin:device:controllee:1";
	public static final String WEMO_TYPE_SENSOR = "urn:Belkin:device:sensor:1";
	public static final String WEMO_TYPE_LIGHT_SWITCH = "urn:Belkin:device:lightswitch:1";
	public static final String WEMO_TYPE_INSIGHT = "urn:Belkin:device:insight:1";

	public static final String WEMO_NOTIFY_REFRESH_LIST = "refresh";
	public static final String WEMO_NOTIFY_ADD_DEVICE = "add";
	public static final String WEMO_NOTIFY_REMOVE_DEVICE = "remove";
	public static final String WEMO_NOTIFY_UPDATE_DEVICE = "update";
	public static final String WEMO_NOTIFY_CHANGE_STATE = "change_state";
	public static final String WEMO_NOTIFY_SET_STATE = "set_state";
	
	/**
	*WeMo is a series of products from Belkin International, Inc. that allows users to control home electronics. The product suite includes the WeMo Switch, WeMo Motion Sensor and WeMo App. The WeMo Switch can be plugged into any home outlet, which can then be controlled from an iOS or Android smartphone running the WeMo App, via home WiFi or mobile phone network.
	* 
	*See https://www.belkin.com/wemo/ for more detail.
	* 
	*WeMoSDK provides classes and methods to display, search and control WeMo devices and NotificationListener interface to get notifications from them.
	* 
	*VERY IMPORTANT NOTE: The Belkin WeMo SDK currently only works over local WiFi networks, it does NOT allow control over the internet. This is a limitation of the SDK and not the library.
	* 
	*/
	public static void LIBRARY_DOC()
	{
	}	  
	
	public WeMoSDKContextWrapper()
	{
	}

	public WeMoSDKContextWrapper(WeMoSDKContext pWeMo)
	{
		setObject(pWeMo);
	}

	/**
	* Initializes the WeMoSDKContext and creates the listener from the event name.
	*/
	public void Initialize(final BA ba, final String EventName)
	{			
		WeMoSDKContext pWeMo = new WeMoSDKContext(ba.context);
		setObject(pWeMo);
		
		final String eventName = EventName.toLowerCase(BA.cul);
		
		pWeMo.addNotificationListener(new NotificationListener()
		{
			public void onNotify(String event, String udn)
			{
				ba.raiseEventFromUI(null, eventName + "_onnotify", new Object[] { String.valueOf(event), String.valueOf(udn) });
				return;
			}

		});		
	}

	/**
	* This method updates the list of discovered devices and their parameters (the friendly name, the state, the logo). It sends WEMO_NOTIFY_REFRESH_LIST notification on task completion.
	*/
	public void refreshListOfWeMoDevicesOnLAN()
	{
		getObject().refreshListOfWeMoDevicesOnLAN();		
	}	
	
	/**
	* It returns the list with UDNs of discovered devices. This method should be called on WEMO_NOTIFY_REFRESH_LIST notification to get up-to-date information. It doesn&apos;t update the device parameters.
	*/
	public List getListOfWeMoDevicesOnLAN()
	{
		List L = new List();
		L.Initialize();
		for (Object o : getObject().getListOfWeMoDevicesOnLAN()) L.Add(o);
		return L;		
	}
	
	/**
	* This method returns the state of a device with the udn. If there is no such device it returns WEMO_DEVICE_UNDEFINED value. This method is analogue to WeMoDevice.getState().
	*/
	public String getDeviceState(String udn)
	{
		return getObject().getDeviceState(udn);
	}

	/**
	* It changes the state of the device with provided udn to the state passed as 1st parameter and sends notification with WEMO_NOTIFY_SET_STATE event. It is recommended to check the new state of the device by using getState() methods to detect If a problem has occurred during the state update.
	*/
	public void setDeviceState(String state, String udn)
	{
		getObject().setDeviceState(state, udn);
		return;
	}
	
	/**
	* The method returns the WeMoDevice with provided udn if it exists and null otherwise.
	*/
	public WeMoDevice getWeMoDeviceByUDN(String udn)
	{
		return getObject().getWeMoDeviceByUDN(udn);
	}
}
