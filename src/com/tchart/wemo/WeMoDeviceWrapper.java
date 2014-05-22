package com.tchart.wemo;

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import com.belkin.wemo.localsdk.WeMoDevice;

@BA.ShortName("WeMoDevice")

public class WeMoDeviceWrapper extends AbsObjectWrapper<WeMoDevice>
{	
	public WeMoDeviceWrapper()
	{

	}

	public WeMoDeviceWrapper(WeMoDevice pWeMoDevice)
	{
		setObject(pWeMoDevice);
	}

	/**
	* It returns the availability of the device (not in official SDK documentation).
	*/
	public boolean isAvailable()
	{
		return getObject().isAvailable();
	}

	/**
	* It returns the type of device.The possible types are WEMO_TYPE_DEFAULT, WEMO_TYPE_SWITCH, WEMO_TYPE_SENSOR, WEMO_TYPE_LIGHT_SWITCH and WEMO_TYPE_INSIGHT.
	*/
	public String getType()
	{
		return getObject().getType();
	}

	/**
	* It returns the name of device. Different devices may have same friendly names.
	*/
	public String getFriendlyName()
	{
		return getObject().getFriendlyName();
	}

	/**
	* UDN uniquely identifies the device. All notification contains this parameter as argument.
	*/
	public String getUDN()
	{
		return getObject().getUDN();
	}

	/**
	* It returns the serial number of the device (not in official SDK documentation).
	*/
	public String getSerialNumber()
	{
		return getObject().getSerialNumber();
	}

	/**
	* It returns the current state of the device. It may be WEMO_DEVICE_ON, WEMO_DEVICE_OFF and WEMO_DEVICE_UNDEFINED (if a problem has occurred during the state update) for switches and sensors. The state of Insight device may be also WEMO_DEVICE_STAND_BY.
	*/
	public String getState()
	{
		return getObject().getState();
	}

	/**
	* It returns the URL to device logo. The logo may be downloaded by http.
	*/
	public String getLogoURL()
	{
		return getObject().getLogoURL();
	}

	/**
	* It returns the path to logo file in local storage. The return value may be null if local storage is not available. It is recommended to use this method to get the logo.
	*/
	public String getLogo()
	{
		return getObject().getLogo();
	}	
}
