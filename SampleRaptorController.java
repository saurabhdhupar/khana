package com.ebay.app.raptor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ebay.raptor.kernel.context.IRaptorContext;

@Controller
public class SampleRaptorController {
	
	@Inject IRaptorContext raptorCtx;
	@Inject OpenStreetMapRequestHelper requestHelper;
	@Inject OpenMapProcessor openStreetMapProcessor;
	
 	@RequestMapping(value="index", method=RequestMethod.GET)
 	
	public @ResponseBody List<GeoCodeDO> handleRequest(@RequestParam(value="zip", required=true, defaultValue="0")String zipCode,
			@RequestParam(value="loc", required=true)String location,
			@RequestParam(value="country", required=true)String country,
			@RequestParam(value="state", required=true)String state) {
 		
 		OpenMapRequest request = requestHelper.createRequest(zipCode, location, state, country);
 		openStreetMapProcessor.process(request);
 		List<GeoCodeDO> geoCode_list = new ArrayList<GeoCodeDO>();
		return geoCode_list;
	}
 	
 	
}
