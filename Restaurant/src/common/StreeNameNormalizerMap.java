package common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StreeNameNormalizerMap {
	
	private static Map<String,List<String>> streetNormalizationMap = new HashMap<String,List<String>>();
	
	static{
		put("ALLEY","ALY");
		put("ANNEX","ANX");
		put("ARCADE","ARC");
		put("AVENUE","AVE");
		put("BAYOU","BYU");
		put("BEACH","BCH");
		put("BEND","BND");
		put("BLUFF","BLF");
		put("BLUFFS","BLFS");
		put("BOTTOM","BTM");
		put("BOULEVARD","BLVD");
		put("BRANCH","BR");
		put("BRIDGE","BRG");
		put("BROOK","BRK");
		put("BROOKS","BRKS");
		put("BURG","BG");
		put("BURG","BURGS");
		put("BURG","BGS");
		put("BYPASS","BYP");
		put("CAMP","CP");
		put("CANYON","CYN");
		put("CAPE","CPE");
		put("CAUSEWAY","CSWY");
		put("CENTER","CTR");
		put("CENTERS","CTRS");
		put("CIRCLE","CIR");
		put("CIRCLES","CIRS");
		put("CLIFF","CLF");
		put("CLIFFS","CLFS");
		put("CLUB","CLB");
		put("COMMON","CMN");
		put("COMMONS","CMNS");
		put("CONCOURSE","CONC");
		put("CORNER","COR");
		put("CORNERS","CORS");
		put("COURSE","CRSE");
		put("COURT","CT");
		put("COURTS","CTS");
		put("COVE","CV");
		put("COVES","CVS");
		put("CREEK","CRK");
		put("CRESCENT","CRES");
		put("CREST","CRST");
		put("CROSSING","XING");
		put("CROSSROAD","XRD");
		put("CROSSROADS","XRDS");
		put("CURVE","CURV");
		put("DALE","DL");
		put("DAM","DM");
		put("DIVIDE","DV");
		put("DRIVE","DR");
		put("DRIVES","DRS");
		put("ESTATE","EST");
		put("ESTATES","ESTS");
		put("EXPRESSWAY","EXPY");
		put("EXTENSION","EXT");
		put("EXTENSIONS","EXTS");
		put("FALL","FALL");
		put("FALLS","FL");
		put("FERRY","FRY");
		put("FIELD","FLD");
		put("FIELDS","FLDS");
		put("FLAT","FLT");
		put("FLATS","FLTS");
		put("FORD","FRD");
		put("FORDS","FRDS");
		put("FOREST","FRST");
		put("FORGE","FRG");
		put("FORGES","FRGS");
		put("FORK","FRK");
		put("FORKS","FRKS");
		put("FORT","FT");
		put("FREEWAY","FWY");
		put("GARDEN","GDN");
		put("GARDENS","GDNS");
		put("GATEWAY","GTWY");
		put("GLEN","GLN");
		put("GLENS","GLNS");
		put("GREEN","GRN");
		put("GREENS","GRNS");
		put("GROVE","GRV");
		put("GROVES","GRVS");
		put("HARBOR","HBR");
		put("HARBORS","HBRS");
		put("HAVEN","HVN");
		put("HEIGHTS","HTS");
		put("HIGHWAY","HWY");
		put("HILL","HL");
		put("HILLS","HLS");
		put("HOLLOW","HOLW");
		put("INLET","INLT");
		put("INLET","ISLAND");
		put("INLET","IS");
		put("ISLANDS","ISS");
		put("ISLE","ISLE");
		put("JUNCTION","JCT");
		put("JUNCTIONS","JCTS");
		put("KEY","KY");
		put("KEYS","KYS");
		put("KNOLL","KNL");
		put("KNOLLS","KNLS");
		put("LAKE","LK");
		put("LAKES","LKS");
		put("LAND","LAND");
		put("LANDING","LNDG");
		put("LANE","LN");
		put("LIGHT","LGT");
		put("LIGHTS","LGTS");
		put("LOAF","LF");
		put("LOCK","LCK");
		put("LOCKS","LCKS");
		put("LODGE","LDG");
		put("LOOP","LOOP");
		put("MALL","MALL");
		put("MANOR","MNR");
		put("MANORS","MNRS");
		put("MEADOW","MDW");
		put("MEADOWS","MDWS");
		put("MEWS","MEWS");
		put("MILL","ML");
		put("MILLS","MLS");
		put("MISSION","MSN");
		put("MOTORWAY","MTWY");
		put("MOUNT","MT");
		put("MOUNTAIN","MTN");
		put("MOUNTAINS","MTNS");
		put("NECK","NCK");
		put("ORCHARD","ORCH");
		put("OVAL","OVAL");
		put("OVAL","OVERPASS");
		put("OVAL","OPAS");
		put("PARK","PARK");
		put("PARKS","PARK");
		put("PARKWAY","PKWY");
		put("PARKWAYS","PKWY");
		put("PASS","PASS");
		put("PASSAGE","PSGE");
		put("PATH","PATH");
		put("PIKE","PIKE");
		put("PINE","PNE");
		put("PINES","PNES");
		put("PLACE","PL");
		put("PLAIN","PLN");
		put("PLAINS","PLNS");
		put("PLAZA","PLZ");
		put("POINT","PT");
		put("POINTS","PTS");
		put("PORT","PRT");
		put("PORTS","PRTS");
		put("PRAIRIE","PR");
		put("RADIAL","RADL");
		put("RAMP","RAMP");
		put("RANCH","RNCH");
		put("RAPID","RPD");
		put("RAPIDS","RPDS");
		put("REST","RST");
		put("RIDGE","RDG");
		put("RIDGES","RDGS");
		put("RIVER","RIV");
		put("ROAD","RD");
		put("ROADS","RDS");
		put("ROUTE","RTE");
		put("ROW","ROW");
		put("RUE","RUE");
		put("RUN","RUN");
		put("SHOAL","SHL");
		put("SHOALS","SHLS");
		put("SHOALS","SHORE");
		put("SHOALS","SHR");
		put("SHORES","SHRS");
		put("SKYWAY","SKWY");
		put("SPRING","SPG");
		put("SPRINGS","SPGS");
		put("SPUR","SPUR");
		put("SPURS","SPUR");
		put("SQUARE","SQ");
		put("SQUARES","SQS");
		put("STATION","STA");
		put("STRAVENUE","STRA");
		put("STREAM","STRM");
		put("STREET","ST");
		put("STREETS","STS");
		put("SUMMIT","SMT");
		put("TERRACE","TER");
		put("THROUGHWAY","TRWY");
		put("TRACE","TRCE");
		put("TRACK","TRAK");
		put("TRAFFICWAY","TRFY");
		put("TRAIL","TRL");
		put("TRAILER","TRLR");
		put("TUNNEL","TUNL");
		put("TURNPIKE","TPKE");
		put("UNDERPASS","UPAS");
		put("UNION","UN");
		put("UNIONS","UNS");
		put("VALLEY","VLY");
		put("VALLEYS","VLYS");
		put("VIADUCT","VIA");
		put("VIEW","VW");
		put("VIEWS","VWS");
		put("VILLAGE","VILL");
		put("VILLAGE","VLG");
		put("VILLAGES","VLGS");
		put("VILLE","VL");
		put("VISTA","VIS");
		put("WALK","WALK");
		put("WALK","WALKS");
		put("WALL","WALL");
		put("WAY","WAY");
		put("WAYS","WAYS");
		put("WELL","WL");
		put("WELLS","WLS");
	}

	private static void put(String key, String value) {
		List<String> values = null;
		if(streetNormalizationMap.containsKey(key.toLowerCase())) {
			values = streetNormalizationMap.get(key.toLowerCase());
			
		}
		else{
			values = new ArrayList<String>();
		}
		values.add(value.toLowerCase());
		streetNormalizationMap.put(key.toLowerCase(), values);
	}
	
	public static String checkValue(String value) {
		Set<String> keyValueList = streetNormalizationMap.keySet();
		Iterator<String> itr_key = keyValueList.iterator();
		while(itr_key.hasNext()) {
			String key = itr_key.next();
			List<String> values = streetNormalizationMap.get(key);
			for (String element : values) {
				if(element.equals(value)) {
					return key;
				}
			}
		}
		return null;
	}
}
